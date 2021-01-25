package com.perforce.akana.qa.cm.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class CM_Landing extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement linkLogin;
	
	public CM_Landing() {
		super();
		linkLogin = null;
	}
	
	public CM_Landing(String url) {
		super(url);
		wd = Connection.getWebDriver();
		logger.info("On CM Landing Page");
		Sleeper.seconds(10, "Waiting for landing page to load");
		String loginLinkXpath = "//a[contains(@class, 'soa-control-cm-signin soa-ui-cm-login-link')]";
		linkLogin = wd.findElement(By.xpath(loginLinkXpath));
	}
	
	public CM_Landing clickLoginLink() {
		logger.info("Clicking the login link");
		linkLogin.click();		
		return this;
	}

}
