package com.perforce.akana.qa.lm.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class LM_Login extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement textPassword;
	private final WebElement btnLogin;
	
	public LM_Login() {
		super();
		textPassword = null;
		btnLogin = null;
	}
	
	public LM_Login(String url) {
		super(url);
		wd = Connection.getWebDriver();
		logger.info("Waiting Lifecycle Manager Login");
		Sleeper.seconds(3, "Waiting for Lifecycle Manager Login to appear");
		String passwordTextFieldXPath = "//input[@name='password']";
		textPassword = wd.findElement(By.xpath(passwordTextFieldXPath));
		String loginButtonXPath = "//input[@id='Login']";
		btnLogin = wd.findElement(By.xpath(loginButtonXPath));
	}
	
	public void loginToLifeCycleManager(String userEmail, String password) {
		logger.info("Logging to Lifecycle Manager ...");
		textPassword.sendKeys(password);
		btnLogin.click();
		Sleeper.seconds(5, "Waiting for login process to complete");
	}

}
