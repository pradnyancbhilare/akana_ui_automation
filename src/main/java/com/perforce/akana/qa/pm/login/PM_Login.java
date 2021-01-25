package com.perforce.akana.qa.pm.login;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.utils.Sleeper;

public class PM_Login extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement textUsername;
	private final WebElement textPassword;
	private final WebElement btnLogin;
//	private static String jsessionid;
//	private static String JSESSIONID_eap;
	
	public PM_Login() {
		super();
		textUsername = null;
		textPassword = null;
		btnLogin = null;
	}
	
	public PM_Login(String url) {
		super(url);
		wd = Connection.getWebDriver();
		logger.info("Waiting PM Login");
		Sleeper.seconds(5, "Waiting for PM Login to appear");
		String userNameTextFieldXpath = "//input[@name='username']";
		textUsername = wd.findElement(By.xpath(userNameTextFieldXpath));
		String passwordTextFieldXPath = "//input[@name='password']";
		textPassword = wd.findElement(By.xpath(passwordTextFieldXPath));
		String loginButtonXPath = "//input[@name='soa-ui-cm-login-button']";
		btnLogin = wd.findElement(By.xpath(loginButtonXPath));
	}
	
	public void loginToPM(String userEmail, String password) {
		logger.info("Logging to PM ...");
		textUsername.sendKeys(userEmail);
		textPassword.sendKeys(password);
		btnLogin.click();
//		if(jsessionid == null || JSESSIONID_eap == null) {
//			try {
//				jsessionid = getValueOfCookieNamed("jsessionid");
//			}catch(Exception e) {
//				
//			}
//			try {
//				JSESSIONID_eap = getValueOfCookieNamed("JSESSIONID_eap");
//			}catch(Exception e) {
//				
//			}
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			Date date = null;
//			try {
//				date = dateFormat.parse("01/01/1970");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//			addCookie("jsessionid", jsessionid, "akana-qa-8.aws.akana.roguewave.com:7900", "/", date);
//			addCookie("JSESSIONID_eap", JSESSIONID_eap, "akana-qa-8.aws.akana.roguewave.com:7900", "/", date);
//		}
		Sleeper.seconds(3, "Waiting for login process to complete");
	}
	
	public String getValueOfCookieNamed(String name){

        return wd.manage().getCookieNamed(name).getValue();
    }
	
	public void addCookie(String name, String value, String domain, String path, Date expiry){
		
		try {
			wd.manage().addCookie(new Cookie(name, value, domain, path, expiry));
		}catch(Exception e) {
			
		}
	}

}
