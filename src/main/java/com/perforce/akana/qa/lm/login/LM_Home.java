package com.perforce.akana.qa.lm.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class LM_Home extends APageObject {
	
	private WebDriver wd = null;
	
	public LM_Home() {
		wd = Connection.getWebDriver();
		logger.info("Checking Lifecycle Manager Home after Login");
		Sleeper.seconds(3, "Waiting for home to appear");
	}
	
	public void navigateToCreateNewLibrary() {
		logger.info("Navigate to Lifecycle Manager New Library ...");
		String newLibraryLinkXPath = "//a[@href='showCreateCompany.do']";
		WebElement linkCreateNewLibrary = wd.findElement(By.xpath(newLibraryLinkXPath));
		linkCreateNewLibrary.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public void navigateToSettings() {
		logger.info("Navigate to Life Cycle Manager Settings ...");
		String settingsLinkXPath = "//a[@href='settings.do']";
		WebElement linkSettings = wd.findElement(By.xpath(settingsLinkXPath));
		linkSettings.click();
		Sleeper.seconds(3, "Waiting for page to appear");
	}
	
	public LM_Login logoutOfLM() {
		logger.info("Logging out of Life Cycle Manager ...");
		String logoutLinkXPath = "//a[contains(@href, 'logoff.do')]";
		WebElement linkLogout = wd.findElement(By.xpath(logoutLinkXPath));
		linkLogout.click();
		Sleeper.seconds(3, "Waiting for logging out");
		return new LM_Login();
	}
}
