package com.perforce.akana.qa;

import java.awt.Toolkit;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perforce.akana.qa.exception.TestCodeException;

public class Connection implements Closeable {

	private Connection() {
	}

	private enum Browser {
		FIREFOX, CHROME
	}

	private static WebDriver driver;

	private static final String DRIVER_VERSION_PROPERTY = "webdriver.version";
	private static final String FIREFOX_DEFAULT_DRIVER = "geckodriver_v0_28_0";
	private static final String CHROME_DEFAULT_DRIVER = "chromedriver_87";

	public static final Logger logger = LogManager.getLogger(Connection.class);

	public static WebDriver getWebDriver() {
		logger.trace("Getting WebDriver");

		if (driver == null || driver.toString().contains("null")) {
			Browser browser = null;
			String config = System.getProperty("browser");
			try {
				browser = Browser.valueOf(config);
			} catch (IllegalArgumentException e) {
				logger.info("Could not find browser: " + config);
			} catch (NullPointerException e) {
				logger.info("No browser specified");
			} finally {
				if (browser == null) {
					logger.info("Defaulting to firefox driver");
					browser = Browser.FIREFOX;
				}
			}

			try {
				driver = getWebDriver(browser);
			} catch (Exception e) {
				throw new TestCodeException(e);
			}
		}

		return driver;
	}

	private static WebDriver getWebDriver(Browser browser) {
		logger.debug("Initializing webdriver for " + browser);
		WebDriver webDriverObject = null;

		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dimension = new Dimension(toolKit.getScreenSize().width, toolKit.getScreenSize().height);

		switch (browser) {
		case CHROME:
			logger.debug("Starting to initialize Chrome WebDriver.");
			webDriverObject = new ChromeDriver(createChromeOptions());
			break;
		case FIREFOX:
			logger.debug("Starting to initialize Firefox WebDriver.");
			webDriverObject = new FirefoxDriver(createFireFoxOptions());
			break;
		default:
			throw new TestCodeException(String.format("Unknown Browser %s", browser));
		}
		webDriverObject.manage().window().setSize(dimension);
		logger.debug("Temp files will be written to: " + System.getProperty("java.io.tmpdir"));
		Capabilities caps = ((RemoteWebDriver) webDriverObject).getCapabilities();
		logger.info("Browser version : " + caps.getBrowserName() + " " + caps.getVersion());
		return webDriverObject;
	}

	private static String getDriverPath(String driverName) {
		String driverFolder;
		if (SystemUtils.IS_OS_MAC) {
			driverFolder = "drivers" + File.separator + "macos";
		} else if (SystemUtils.IS_OS_LINUX) {
			driverFolder = "drivers" + File.separator + "linux";
		} else if (SystemUtils.IS_OS_WINDOWS) {
			driverFolder = "drivers" + "/" + "windows";
			driverName = driverName + ".exe";
		} else {
			throw new TestCodeException("Unsupported OS");
		}

		ClassLoader classLoader = Connection.class.getClassLoader();
		String filePath = driverFolder + "/" + driverName;
		logger.trace("File Path:" + filePath);
		URL url = classLoader.getResource(filePath);
		URL home = classLoader.getResource(".");
		logger.trace("Home URL:" + home);
		logger.trace("Driver URL:" + url);
		File file = new File(url.getFile());
		try {
			if (!SystemUtils.IS_OS_WINDOWS) {
				Set<PosixFilePermission> perms = new HashSet<>();
				List<PosixFilePermission> driverPerms = Arrays.asList(PosixFilePermission.GROUP_EXECUTE,
						PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_EXECUTE,
						PosixFilePermission.OTHERS_READ, PosixFilePermission.OWNER_EXECUTE,
						PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
				perms.addAll(driverPerms);
				Files.setPosixFilePermissions(Paths.get(url.getFile()), perms);
			}
		} catch (UnsupportedOperationException e) {
			logger.warn("Driver could not be set as an executable");
		} catch (IOException e) {
			throw new TestCodeException(e);
		}
		logger.info("Using driver at:" + file.toString());
		return file.toString();
	}

	public static void closeConnection() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	@Override
	public void close() throws IOException {
		closeConnection();
	}

	private static FirefoxProfile createFirefoxProfile() {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
//		firefoxProfile.setPreference("browser.download.folderList", 2);
//		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
//		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
//		// Disable auto update
//		firefoxProfile.setPreference("app.update.enabled", false);
//		firefoxProfile.setPreference("app.update.auto", false);
//		firefoxProfile.setPreference("app.update.silent", false);
//
//		if (Boolean.parseBoolean(System.getProperty("privateBrowsingEnabled"))) {
//			logger.info("Setting up private browsing");
//			firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
//		}
//
//		// Enable Do Not Tracking?
//		if (Boolean.parseBoolean(System.getProperty("enableDNT"))) {
//			logger.info("Enabling do not track feature");
//			firefoxProfile.setPreference("privacy.donottrackheader.enabled", true);
//		}
//
//		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//				"text/plain, application/vnd.ms-excel, text/csv, text/comma-separated-values, application/octet-stream");
		return firefoxProfile;
	}

	private static ChromeOptions createChromeOptions() {
		logger.trace("Creating Chrome Options");
		String driverVersion = getWebDriverVersionProperty(Browser.CHROME);
		logger.debug("The Chrome driver version is " + driverVersion);

		System.setProperty("webdriver.chrome.driver", getDriverPath("chromedriver"));
		logger.trace(System.getProperty("webdriver.chrome.driver"));

		ChromeOptions chromeOptions = new ChromeOptions();

		// Set Preferences
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
//
//		// Enable Do Not Tracking?
//		if (Boolean.parseBoolean(System.getProperty("enableDNT"))) {
//			logger.info("Enabling do not track feature");
//			prefs.put("enable_do_not_track", true);
//		}
//
		chromeOptions.setExperimentalOption("prefs", prefs);
//		chromeOptions.addArguments("--enable-notifications");
//
//		// Incognito?
//		if (Boolean.parseBoolean(System.getProperty("privateBrowsingEnabled"))) {
//			logger.info("Setting up private browsing");
//			chromeOptions.addArguments("--incognito");
//		}
//
//		chromeOptions.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		chromeOptions.addArguments("--disable-save-password-bubble");
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
//		chromeOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		chromeOptions.addArguments("--disable-web-security","--ignore-certificate-errors", "--allow-running-insecure-content");
		chromeOptions.setAcceptInsecureCerts(true);
//		chromeOptions.setCapability("marionette", false);

		return chromeOptions;
	}

	private static FirefoxOptions createFireFoxOptions() {
		logger.trace("Creating Firefox Options");
		String driverVersion = getWebDriverVersionProperty(Browser.FIREFOX);
		logger.debug("The Firefox driver version is " + driverVersion);

		System.setProperty("webdriver.gecko.driver", getDriverPath("geckodriver"));
		logger.trace(System.getProperty("webdriver.gecko.driver"));

		// Disable firefox background logging
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

		FirefoxOptions firefoxOptions = new FirefoxOptions();

		firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
		firefoxOptions.setCapability(FirefoxDriver.PROFILE, createFirefoxProfile());
		firefoxOptions.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		firefoxOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		/*
		 * Fix to avoid error for as Element <input type="file"> is not reachable by
		 * keyboard in Firefox In Firefox For input[type=file], sendKeys doesn't work
		 * when visibility is hidden https://github.com/mozilla/geckodriver/issues/1173
		 * Added the below line to support file import
		 * 
		 */
		firefoxOptions.setCapability("moz:webdriverClick", false);

		return firefoxOptions;
	}

	private static String getWebDriverVersionProperty(Browser browser) {
		String version = System.getProperty(DRIVER_VERSION_PROPERTY);
		if (version != null) {
			logger.debug(String.format("Detected value '%s' for %s", DRIVER_VERSION_PROPERTY, version));
		} else {
			// Determine De Fault driver for browser
			String sanAndreas = null;
			switch (browser) {
			case CHROME:
				sanAndreas = CHROME_DEFAULT_DRIVER;
				break;
			case FIREFOX:
				sanAndreas = FIREFOX_DEFAULT_DRIVER;
				break;
			default:
				// Do nothing, should error out before this
				break;
			}

			logger.trace(String.format("Defaulting to driver '%s'", sanAndreas));
			version = sanAndreas;
		}

		return version;
	}

}
