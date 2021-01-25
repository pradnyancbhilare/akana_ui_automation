package com.perforce.akana.qa.cm.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class CM_Login extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement textUsername;
	private final WebElement textPassword;
	private final WebElement btnLogin;
	private final WebElement lnkForgotPassword;
	private final WebElement lnkCreateAccount;
	
	public CM_Login() {	
		wd = Connection.getWebDriver();
		logger.info("On CM Login Page");
		Sleeper.seconds(3, "Waiting for login page to appear");
		String userNameTextFieldXpath = "//input[contains(@class, 'soa-control-cm-textbox-Email')]";
		textUsername = wd.findElement(By.xpath(userNameTextFieldXpath));
		String passwordTextFieldXPath = "//input[contains(@class, 'soa-control-cm-password-Password')]";
		textPassword = wd.findElement(By.xpath(passwordTextFieldXPath));
		String loginButtonXPath = "//button[contains(@class, 'soa-control-cm-login')]";
		btnLogin = wd.findElement(By.xpath(loginButtonXPath));
		String forgotPasswordLinkXPath = "//a[contains(@href, 'forgotpassword')]";
		lnkForgotPassword = wd.findElement(By.xpath(forgotPasswordLinkXPath));
		String createAccountLinkXPath = "//a[contains(@href, 'signup')]";
		lnkCreateAccount = wd.findElement(By.xpath(createAccountLinkXPath));		
	}
	
	public void loginToCM(String userEmail, String password) {
		logger.info("Logging to CM ...");
		textUsername.sendKeys(userEmail);
		textPassword.sendKeys(password);
		btnLogin.click();
		Sleeper.seconds(5, "Waiting for login process to complete");
	}
	
	public void clickForgotPasswordLink() {
		logger.info("Clicking forgot password link ...");
		lnkForgotPassword.click();
	}
	
	public void clickCreateAccountLink() {
		logger.info("Clicking create account link");
		lnkCreateAccount.click();
	}
}
