package com.perforce.akana.qa.adminconsole.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class AC_Login extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement textUsername;
	private final WebElement textPassword;
	private final WebElement btnLogin;
	
	public AC_Login() {
		super();
		textUsername = null;
		textPassword = null;
		btnLogin = null;
	}
	
	public AC_Login(String url) {
		super(url);
		wd = Connection.getWebDriver();
		logger.info("Waiting Admin Console Login");
		Sleeper.seconds(3, "Waiting for Admin Console Login to appear");
		String userNameTextFieldXpath = "//input[@name='username']";
		textUsername = wd.findElement(By.xpath(userNameTextFieldXpath));
		String passwordTextFieldXPath = "//input[@name='password']";
		textPassword = wd.findElement(By.xpath(passwordTextFieldXPath));
		String loginButtonXPath = "//button[@id='login-button']";
		btnLogin = wd.findElement(By.xpath(loginButtonXPath));
	}
	
	public void loginToAdminConsole(String userEmail, String password) {
		logger.info("Logging to Admin Console ...");
		textUsername.sendKeys(userEmail);
		textPassword.sendKeys(password);
		btnLogin.click();
		Sleeper.seconds(5, "Waiting for login process to complete");
	}

}
