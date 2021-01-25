package com.perforce.akana.qa.envision.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.envision.profile.EV_Settings;
import com.perforce.akana.qa.utils.Sleeper;

public class EV_Header extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement linkProfile;
	private final WebElement linkDashboard;
	private final WebElement linkCharts;
	private final WebElement linkDatasets;
	
	public EV_Header() {
		wd = Connection.getWebDriver();
		logger.info("Checking Envision Header after Login");
		Sleeper.seconds(3, "Waiting for header to appear");
		String profileLinkXPath = "//span[@id='soa-control-am-profile-link']";
		linkProfile = wd.findElement(By.xpath(profileLinkXPath));
		String dashboardLinkXPath = "//a[@id='soa-control-am-nav-item-link-dashboards']";
		linkDashboard = wd.findElement(By.xpath(dashboardLinkXPath));
		String chartsLinkXPath = "//a[@id='soa-control-am-nav-item-link-charts']";
		linkCharts = wd.findElement(By.xpath(chartsLinkXPath));
		String datasetsLinkXPath = "//a[@id='soa-control-am-nav-item-link-datasets']";
		linkDatasets = wd.findElement(By.xpath(datasetsLinkXPath));
	}
	
	public EV_Settings navigateToSettings() {
		logger.info("Navigate to Envision Settings ...");
		linkProfile.click();
		String settingsLinkXPath = "//a[@id='soa-control-am-settings-link']";
		WebElement linkSettings = wd.findElement(By.xpath(settingsLinkXPath));
		linkSettings.click();
		return new EV_Settings();
	}
	
	public EV_Login logoutOfEnvision() {
		logger.info("Logging out of Envision ...");
		linkProfile.click();
		String logoutLinkXPath = "//a[@id='soa-control-am-logout-link']";
		WebElement linkLogout = wd.findElement(By.xpath(logoutLinkXPath));
		linkLogout.click();
		return new EV_Login();
	}
	
	public EV_Header navigateToCharts() {
		logger.info("Navigate to Envision Charts ...");
		linkCharts.click();		
		return this;
	}
	
	public EV_Header navigateToDatasets() {
		logger.info("Navigate to Envision Datasets ...");
		linkDatasets.click();		
		return this;
	}
	
	public EV_Header navigateToDashboard() {
		logger.info("Navigate to Envision Dashboards ...");
		linkDashboard.click();		
		return this;
	}

}
