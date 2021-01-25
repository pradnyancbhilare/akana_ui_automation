package com.perforce.akana.qa.pm.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.pm.profile.PM_ProfileDetails;
import com.perforce.akana.qa.utils.Sleeper;

public class PM_Header extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement linkProfile;
	private final WebElement linkLogout;
	private final WebElement linkWorkbench;
	private final WebElement linkDashboard;
	private final WebElement linkAlerts;
	private final WebElement linkSecurity;
	private final WebElement linkMonitoring;
	private final WebElement linkConfigure;
	
	public PM_Header() {
		wd = Connection.getWebDriver();
		logger.info("Checking PM Header after Login");
		Sleeper.seconds(3, "Waiting for header to appear");
		wd.switchTo().frame("header");
		String profileLinkXPath = "//a[contains(@href, '/pm/getMyInfo.do')]";
		linkProfile = wd.findElement(By.xpath(profileLinkXPath));
		String logoutLinkXPath = "//a[contains(@href, '/ms/logout.do')]";
		linkLogout = wd.findElement(By.xpath(logoutLinkXPath));
		String workbenchLinkXPath = "//a[contains(@href, 'workbench')]";
		linkWorkbench = wd.findElement(By.xpath(workbenchLinkXPath));
		String dashboardLinkXPath = "//a[contains(@href, 'dashboard')]";
		linkDashboard = wd.findElement(By.xpath(dashboardLinkXPath));
		String alertsLinkXPath = "//a[contains(@href, 'alerts')]";
		linkAlerts = wd.findElement(By.xpath(alertsLinkXPath));
		String securityLinkXPath = "//a[contains(@href, 'security')]";
		linkSecurity = wd.findElement(By.xpath(securityLinkXPath));
		String monitoringLinkXPath = "//a[contains(@href, 'monitoring')]";
		linkMonitoring = wd.findElement(By.xpath(monitoringLinkXPath));
		String configureLinkXPath = "//a[contains(@href, 'configure')]";
		linkConfigure = wd.findElement(By.xpath(configureLinkXPath));
		wd.switchTo().defaultContent();
	}
	
	public PM_ProfileDetails navigateToProfile() {
		logger.info("Navigate to PM Profile ...");
		wd.switchTo().frame("header");
		linkProfile.click();
		wd.switchTo().defaultContent();
		
		return new PM_ProfileDetails();
	}
	
	public PM_Login logoutOfPM() {
		logger.info("Logging out of PM ...");
		wd.switchTo().frame("header");
		linkLogout.click();
		wd.switchTo().defaultContent();
		
		return new PM_Login();
	}
	
	public PM_Header navigateToWorkbench() {
		logger.info("Navigate to PM Workbench ...");
		wd.switchTo().frame("header");
		linkWorkbench.click();
		wd.switchTo().defaultContent();
		
		return this;
	}
	
	public PM_Header navigateToDashboard() {
		logger.info("Navigate to PM Dashboard ...");
		wd.switchTo().frame("header");
		linkDashboard.click();
		wd.switchTo().defaultContent();
		
		return this;
	}
	
	public PM_Header navigateToAlerts() {
		logger.info("Navigate to PM Alerts ...");
		wd.switchTo().frame("header");
		linkAlerts.click();
		wd.switchTo().defaultContent();
		
		return this;
	}
	
	public PM_Header navigateToSecurity() {
		logger.info("Navigate to PM Security ...");
		wd.switchTo().frame("header");
		linkSecurity.click();
		wd.switchTo().defaultContent();
		
		return this;
	}
	
	public PM_Header navigateToMonitoring() {
		logger.info("Navigate to PM Monitoring ...");
		wd.switchTo().frame("header");
		linkMonitoring.click();
		wd.switchTo().defaultContent();
		
		return this;
	}
	
	public PM_Header navigateToConfigure() {
		logger.info("Navigate to PM Configure ...");
		wd.switchTo().frame("header");
		linkConfigure.click();
		wd.switchTo().defaultContent();
		
		return this;
	}

}
