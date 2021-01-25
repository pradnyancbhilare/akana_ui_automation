package com.perforce.akana.qa.adminconsole.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class AC_Home extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement linkLogout;
	private final WebElement linkAvailableFeatures;
	private final WebElement linkInstalledFeatures;
	private final WebElement linkConfiguration;
	private final WebElement linkRepository;
	private final WebElement linkHealth;
	private final WebElement linkSystem;
	
	public AC_Home() {
		wd = Connection.getWebDriver();
		logger.info("Checking Admin Console Home after Login");
		Sleeper.seconds(3, "Waiting for home to appear");
		String avFeaturesLinkXPath = "//a[@href='#tab-Available-Features']";
		linkAvailableFeatures = wd.findElement(By.xpath(avFeaturesLinkXPath));
		String inFeaturesLinkXPath = "//a[@href='#tab-Installed-Features']";
		linkInstalledFeatures = wd.findElement(By.xpath(inFeaturesLinkXPath));
		String configLinkXPath = "//a[@href='#tab-Configuration']";
		linkConfiguration = wd.findElement(By.xpath(configLinkXPath));
		String repoLinkXPath = "//a[@href='#tab-Repository']";
		linkRepository = wd.findElement(By.xpath(repoLinkXPath));
		String healthLinkXPath = "//a[@href='#tab-Health']";
		linkHealth = wd.findElement(By.xpath(healthLinkXPath));
		String systemLinkXPath = "//a[@href='#tab-System']";
		linkSystem = wd.findElement(By.xpath(systemLinkXPath));
		String logoutLinkXPath = "//a[@class='logout-button']";
		linkLogout = wd.findElement(By.xpath(logoutLinkXPath));
	}
	
	public void navigateToAvailableFeatures() {
		logger.info("Navigate to Admin Console Available Features ...");
		linkAvailableFeatures.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public void navigateToInstalledFeatures() {
		logger.info("Navigate to Admin Console Installed Features ...");
		linkInstalledFeatures.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public void navigateToConfiguration() {
		logger.info("Navigate to Admin Console Configuration ...");
		linkConfiguration.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public void navigateToRepository() {
		logger.info("Navigate to Admin Console Repository ...");
		linkRepository.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public void navigateToHealth() {
		logger.info("Navigate to Admin Console Health ...");
		linkHealth.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public void navigateToSystem() {
		logger.info("Navigate to Admin Console System ...");
		linkSystem.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public AC_Login logoutOfAdminConsole() {
		logger.info("Logging out of Admin Console ...");
		linkLogout.click();
		Sleeper.seconds(3, "Waiting for logging out");
		return new AC_Login();
	}

}
