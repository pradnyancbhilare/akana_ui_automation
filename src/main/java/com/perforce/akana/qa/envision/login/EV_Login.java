package com.perforce.akana.qa.envision.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class EV_Login extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement textUsername;
	private final WebElement textPassword;
	private final WebElement btnLogin;
	
	public EV_Login() {
		super();
		textUsername = null;
		textPassword = null;
		btnLogin = null;
	}
	
	public EV_Login(String url) {
		super(url);
		wd = Connection.getWebDriver();
		logger.info("Waiting Envision Login");
		Sleeper.seconds(20, "Waiting for Envision Login to appear");
		String userNameTextFieldXpath = "//input[@name='userName']";
		textUsername = wd.findElement(By.xpath(userNameTextFieldXpath));
		String passwordTextFieldXPath = "//input[@name='password']";
		textPassword = wd.findElement(By.xpath(passwordTextFieldXPath));
		String loginButtonXPath = "//button[@id='soa-control-am-button-login']";
		btnLogin = wd.findElement(By.xpath(loginButtonXPath));
	}
	
	public void loginToEnvision(String userEmail, String password) {
		logger.info("Logging to Envision ...");
		textUsername.sendKeys(userEmail);
		textPassword.sendKeys(password);
		btnLogin.click();
		Sleeper.seconds(5, "Waiting for login process to complete");
	}

}
