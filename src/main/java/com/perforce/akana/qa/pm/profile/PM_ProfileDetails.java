package com.perforce.akana.qa.pm.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class PM_ProfileDetails extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement btnModifyUserProfile;
	
	public PM_ProfileDetails() {
		wd = Connection.getWebDriver();
		logger.info("Checking PM Profile Details");
		Sleeper.seconds(3, "Waiting for Profile Details to appear");
		wd.switchTo().frame("content");
		String modifyUserProfileButtonXPath = "//input[contains(@value, 'Modify User Profile')]";
		btnModifyUserProfile = wd.findElement(By.xpath(modifyUserProfileButtonXPath));
		wd.switchTo().defaultContent();
	}

}
