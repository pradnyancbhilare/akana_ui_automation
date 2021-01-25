package com.perforce.akana.qa.testng.test;

import java.lang.reflect.Method;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.testng.PropertyUtil;
import com.perforce.akana.qa.testng.datamodel.jax.User;
import com.perforce.akana.qa.testng.datamodel.jax.Users;
import com.perforce.akana.qa.testng.jax.UserParser;

public class AkanaUITest {

	public static final String USER1 = "user1";
	public static final String USER2 = "user2";
	public static final String USER3 = "user3";
	public static final String USER4 = "user4";
	public static final String USER5 = "user5";
	public static final String ALL_USERS = "USERS";

	protected static boolean closeBrowserAfterClass = true;

	protected static final String ANNOTATION_LOG_STATEMENT_PATTERN = "**** %s ****";
	protected final Logger logger = LogManager.getLogger(getClass());

	@BeforeSuite(alwaysRun = true)
	public void beforeAkanaUITestSuite(ITestContext context) {
		logger.trace(String.format(ANNOTATION_LOG_STATEMENT_PATTERN, "Before Suite"));
		loadTestFiles(context);
	}

	@BeforeClass(alwaysRun = true)
	public void beforeAkanaUITestClass() {
		logger.trace(String.format(ANNOTATION_LOG_STATEMENT_PATTERN, "Before Class"));
		// Pre-initialize the Browser
		Connection.getWebDriver();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeAkanaUITestMethod(Method method) {
		logger.trace(String.format(ANNOTATION_LOG_STATEMENT_PATTERN, "Before Method"));
		logger.info(String.format("**** Test: %s ****", method.getName()));
	}

	@AfterMethod()
	public void afterAkanaUITestMethod(ITestResult result, Method method) {
		logger.trace(String.format(ANNOTATION_LOG_STATEMENT_PATTERN, "After Method"));
		endTest(result, method);
		closeSecondaryWindows();
	}

	@AfterClass()
	public static void afterAkanaUITestClass() {
		if (closeBrowserAfterClass) {
			Connection.closeConnection();
		}
	}

	public void loadTestFiles(ITestContext context) {
		Users users = UserParser.getUsers("users.xml");
		context.setAttribute(ALL_USERS, users);

		PropertyUtil.addSystemProperty(context, USER1);
		PropertyUtil.addOptionalSystemProperty(context, USER2);
		PropertyUtil.addOptionalSystemProperty(context, USER3);
		PropertyUtil.addOptionalSystemProperty(context, USER4);
		PropertyUtil.addOptionalSystemProperty(context, USER5);
		PropertyUtil.outputProperties();
	}

	//@Override
	public User getPrimaryUser(ITestContext context) {
		logger.debug("Getting Primary User");
		String userKey = (String) context.getAttribute(USER1);
		logger.debug(String.format("User key: '%s'", userKey));
		Users users = (Users) context.getAttribute(ALL_USERS);
		User user = users.getUser(userKey);
		logger.debug(user);
		return user;
	}
	
	public User getSecondaryUser(ITestContext context) {
		logger.debug("Getting Secondary User");
		String userKey = (String) context.getAttribute(USER2);
		logger.debug(String.format("User key: '%s'", userKey));
		Users users = (Users) context.getAttribute(ALL_USERS);
		User user = users.getUser(userKey);
		logger.debug(user);
		return user;
	}
	
	public User getTertiaryUser(ITestContext context) {
		logger.debug("Getting Secondary User");
		String userKey = (String) context.getAttribute(USER3);
		logger.debug(String.format("User key: '%s'", userKey));
		Users users = (Users) context.getAttribute(ALL_USERS);
		User user = users.getUser(userKey);
		logger.debug(user);
		return user;
	}
	
	public User getQuaternaryUser(ITestContext context) {
		logger.debug("Getting Secondary User");
		String userKey = (String) context.getAttribute(USER4);
		logger.debug(String.format("User key: '%s'", userKey));
		Users users = (Users) context.getAttribute(ALL_USERS);
		User user = users.getUser(userKey);
		logger.debug(user);
		return user;
	}
	
	public User getQuinaryUser(ITestContext context) {
		logger.debug("Getting Secondary User");
		String userKey = (String) context.getAttribute(USER5);
		logger.debug(String.format("User key: '%s'", userKey));
		Users users = (Users) context.getAttribute(ALL_USERS);
		User user = users.getUser(userKey);
		logger.debug(user);
		return user;
	}

	protected void endTest(ITestResult result, Method method) {
		if (result.getStatus() == ITestResult.FAILURE) {
			errorSnapshot(method);
			logger.info(String.format("**** Failed Test: %s ****", method.getName()));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.info(String.format("**** Passed Test: %s ****", method.getName()));
		}
	}

	protected void closeSecondaryWindows() {
		logger.info("Closing secondary windows");
		// caller
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement element : stackTrace) {
			logger.trace(element.getClassName() + " " + element.getMethodName());
		}

		// Close secondary windows
		WebDriver wd = Connection.getWebDriver();
		Set<String> handles = wd.getWindowHandles();
		if (handles.size() > 1) {
			String[] listHandles = handles.toArray(new String[handles.size()]);
			for (int i = 1; i < handles.size(); i++) {
				wd.switchTo().window(listHandles[i]);
				wd.close();
			}

			wd.switchTo().window(listHandles[0]);
			// Screenshot can be taken
		}
	}

	/**
	 * This method is used to close third window only and switch back to 2nd window
	 */
	protected void closeThirdWindow() {
		logger.info("Closing third window");
		// caller
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement element : stackTrace) {
			logger.trace(element.getClassName() + " " + element.getMethodName());
		}
		WebDriver wd = Connection.getWebDriver();
		Set<String> handles = wd.getWindowHandles();
		String[] listHandles = handles.toArray(new String[handles.size()]);
		if (listHandles.length >= 3) {
			wd.switchTo().window(listHandles[2]);
			wd.close();
			wd.switchTo().window(listHandles[1]);
			// Screenshot can be taken
		} else {
			logger.info("there is no 3rd window, so nothing to do...");
		}
	}

	protected void errorSnapshot(Method method) {
		logger.info(String.format("Capturing failure snapshot of \"%s\"", method.getName()));
		// Screenshot can be taken
	}

}
