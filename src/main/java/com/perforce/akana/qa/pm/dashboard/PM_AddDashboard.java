package com.perforce.akana.qa.pm.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class PM_AddDashboard extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement textPageTitle;
	private final WebElement textDescription;
	private final WebElement checkDefaultPage;
	private final WebElement btnCancel;
	private final WebElement btnApply;
	
	public PM_AddDashboard() {
		wd = Connection.getWebDriver();
		logger.info("Checking Add Dashboard window ");
		Sleeper.seconds(3, "Waiting for Add Dashboard to appear");
		String pageTitleTextXPath = "//input[@id='title']";
		textPageTitle = wd.findElement(By.xpath(pageTitleTextXPath));
		String descTextXPath = "//input[@id='description']";
		textDescription = wd.findElement(By.xpath(descTextXPath));
		String defaultCheckXPath = "//input[@id='defaultFlag']";
		checkDefaultPage = wd.findElement(By.xpath(defaultCheckXPath));
		String cancelBtnXPath = "//input[contains(@id,'cancel')]";
		btnCancel = wd.findElement(By.xpath(cancelBtnXPath));
		String applyBtnXPath = "//input[contains(@id,'apply')]";
		btnApply = wd.findElement(By.xpath(applyBtnXPath));		
	}
	
	public void cancelAddDashboardAction() {
		logger.info("Cancel Add Dashboard action");
		textPageTitle.sendKeys("Something");
		textDescription.sendKeys("Something");
		Sleeper.seconds(1, "Waiting for some time");
		btnCancel.click();
		Sleeper.seconds(3, "Waiting for Add Dashboard to disappear");
	}

}
