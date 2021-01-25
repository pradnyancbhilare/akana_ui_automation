package com.perforce.akana.qa.envision.dashboards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class EV_NewDashboard extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement titleNewDashboard;
	private final WebElement btnNewDashboard;
	
	public EV_NewDashboard() {
		wd = Connection.getWebDriver();
		logger.info("Checking New Dashboard page");
		Sleeper.seconds(3, "Waiting for home to appear");
		String newDashboardTitleXPath = "//h1[@class='page-header']";
		titleNewDashboard = wd.findElement(By.xpath(newDashboardTitleXPath));
		String newDashboardBtnXPath = "//button[@id='soa-control-am-dashboard-new']";
		btnNewDashboard = wd.findElement(By.xpath(newDashboardBtnXPath));
	}
	
	public void AddNewDashboard() {
		logger.info("Adding a new dashboard ...");
		btnNewDashboard.click();
		new NewDashboardDialog().cancelNewDashboardAction();
		Sleeper.seconds(3, "Waiting for home to appear");
	}
	
	
	public class NewDashboardDialog {
		
		private final WebElement titleNewDashboard;
		private final WebElement textNameOfDashboard;
		private final WebElement textDescOfDashboard;
		private final WebElement btnCancel;
		private final WebElement btnSave;
		
		public NewDashboardDialog() {
			Sleeper.seconds(3, "Waiting for home to appear");
			String newDashboardTitleXPath = "//span[@class='ui-dialog-title']";
			titleNewDashboard = wd.findElement(By.xpath(newDashboardTitleXPath));
			String nameTextXPath = "//input[@name='name']";
			textNameOfDashboard = wd.findElement(By.xpath(nameTextXPath));
			String descTextXPath = "//textarea[@name='description']";
			textDescOfDashboard = wd.findElement(By.xpath(descTextXPath));
			String cancelBtnXPath = "//button[@id='soa-control-am-cancel']";
			btnCancel = wd.findElement(By.xpath(cancelBtnXPath));
			String saveBtnXPath = "//button[@id='soa-control-am-finish']";
			btnSave = wd.findElement(By.xpath(saveBtnXPath));
		}
		
		public void cancelNewDashboardAction() {
			logger.info("Canceling a new dashboard ...");
			textNameOfDashboard.sendKeys("New Dashboard");
			textDescOfDashboard.sendKeys("New Dashboard");
			Sleeper.seconds(1, "Waiting for some time");
			btnCancel.click();
			Sleeper.seconds(1, "Waiting for some time");
		}
		
	}

}
