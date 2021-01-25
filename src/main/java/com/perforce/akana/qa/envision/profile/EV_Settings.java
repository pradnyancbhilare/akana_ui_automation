package com.perforce.akana.qa.envision.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class EV_Settings extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement txtSettingsTitle;
	
	public EV_Settings() {
		wd = Connection.getWebDriver();
		logger.info("Checking Envision Settings");
		Sleeper.seconds(3, "Waiting for Envision Settings to appear");
		String settingsLableXPath = "//h1[@class='page-header']";
		txtSettingsTitle = wd.findElement(By.xpath(settingsLableXPath));
	}

}
