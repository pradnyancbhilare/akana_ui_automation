package com.perforce.akana.qa.cm.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class CM_ProfileDetails extends APageObject {

	private WebDriver wd = null;
	private final WebElement lblDetailsTitle;
	
	public CM_ProfileDetails() {
		wd = Connection.getWebDriver();
		logger.info("On CM Profile Page");
		Sleeper.seconds(3, "Waiting for profile details page to appear");
		String detailsTitleLabelXPath = "//h1[contains(@class, 'soa-ui-cm-box-header')]";
		lblDetailsTitle = wd.findElement(By.xpath(detailsTitleLabelXPath));
	}
}
