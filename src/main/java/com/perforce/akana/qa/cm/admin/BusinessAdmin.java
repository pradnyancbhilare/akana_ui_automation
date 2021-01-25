package com.perforce.akana.qa.cm.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class BusinessAdmin extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement lblBusinessAdminTitle;
	
	public BusinessAdmin() {
		wd = Connection.getWebDriver();
		logger.info("On CM Profile Page");
		Sleeper.seconds(3, "Waiting for profile details page to appear");
		String businessAdminTitleLabelXPath = "//h1[contains(@class, 'soa-ui-cm-box-header')]";
		lblBusinessAdminTitle = wd.findElement(By.xpath(businessAdminTitleLabelXPath));
	}

}
