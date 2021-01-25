package com.perforce.akana.qa.pm.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class PM_Dashboard extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement linkAddDashboard;
	
	public PM_Dashboard() {
		wd = Connection.getWebDriver();
		logger.info("Checking Dashboard");
		Sleeper.seconds(3, "Waiting for dashboard view to appear");
		wd.switchTo().frame("content");
		wd.switchTo().frame("treePanel");
		String addDashboardLinkXPath = "//a[contains(@href, 'addDashboardPage')]";
		linkAddDashboard = wd.findElement(By.xpath(addDashboardLinkXPath));
		wd.switchTo().defaultContent();
		wd.switchTo().defaultContent();
	}
	
	public void clickAddDashboard() {
		logger.info("Clicking Add Dashboard link");
		wd.switchTo().frame("content");
		logger.debug("The name of the tag is " + wd.findElement(By.xpath("//*[@id='frms']")).getTagName());
		wd.switchTo().frame("treePanel");		
		linkAddDashboard.click();
		wd.switchTo().defaultContent();
		wd.switchTo().defaultContent();
	}

}
