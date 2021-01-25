package com.perforce.akana.qa.envision.dashboards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class EV_Dashboard extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement titleDashboard;
	private final WebElement btnConfigure;
	
	public EV_Dashboard() {
		wd = Connection.getWebDriver();
		logger.info("Checking Dashboard page");
		Sleeper.seconds(3, "Waiting for home to appear");
		String dashboardTitleXPath = "//label[@for='soa-control-am-selectbox-dashboards']";
		titleDashboard = wd.findElement(By.xpath(dashboardTitleXPath));
		String configureBtnXPath = "//button[@id='soa-control-am-configure-dashboard']";
		btnConfigure = wd.findElement(By.xpath(configureBtnXPath));
	}
	
	public void configureDashboard() {
		logger.info("Configuring dashboard ...");
		btnConfigure.click();
	}

}
