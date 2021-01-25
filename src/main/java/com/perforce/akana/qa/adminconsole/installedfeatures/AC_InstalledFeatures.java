package com.perforce.akana.qa.adminconsole.installedfeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class AC_InstalledFeatures extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement btnSearchForUpdates;
	
	
	public AC_InstalledFeatures() {
		wd = Connection.getWebDriver();
		logger.info("Checking Admin Console Home after Login");
		Sleeper.seconds(3, "Waiting for home to appear");
		wd.switchTo().frame("installed_features_content");
		String searchForUpdatesBtnXPath = "//*[text()='Search for Updates']";
		btnSearchForUpdates = wd.findElement(By.xpath(searchForUpdatesBtnXPath));
		wd.switchTo().defaultContent();
	}
	
	public AC_InstalledFeatures openSearchForUpdates() {
		logger.info("Opening search for update dialog ...");
		wd.switchTo().frame("installed_features_content");
		btnSearchForUpdates.click();
		new SearchForUpdateDialog().cancelSearchForUpdateDialog();
		wd.switchTo().defaultContent();
		Sleeper.seconds(3, "Waiting for page to appear");
		return this;
	}
	
	public class SearchForUpdateDialog {
		
		private final WebElement btnCancel;
		
		public SearchForUpdateDialog() {
			Sleeper.seconds(3, "Waiting for dialog to appear");
			String cancelBtnXPath = "//button[text()='Cancel']";
			btnCancel = wd.findElement(By.xpath(cancelBtnXPath));
		}
		
		public void cancelSearchForUpdateDialog() {
			logger.info("Cancel search for update dialog ...");
			btnCancel.click();
		}
		
	}
}
