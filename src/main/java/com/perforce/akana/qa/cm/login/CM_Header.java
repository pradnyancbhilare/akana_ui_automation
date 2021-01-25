package com.perforce.akana.qa.cm.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.perforce.akana.qa.APageObject;
import com.perforce.akana.qa.Connection;
import com.perforce.akana.qa.cm.admin.BusinessAdmin;
import com.perforce.akana.qa.cm.profile.CM_ProfileDetails;
import com.perforce.akana.qa.utils.Sleeper;

public class CM_Header extends APageObject {
	
	private WebDriver wd = null;
	private final WebElement linkProfilePhoto;
	private final WebElement linkMoreOptions;

	public CM_Header() {
		wd = Connection.getWebDriver();
		logger.info("Checking CM Header after Login");
		Sleeper.seconds(3, "Waiting for header to appear");
		String profilePhotoLinkXPath = "//img[@id='soa-control-cm-profile-img']";
		linkProfilePhoto = wd.findElement(By.xpath(profilePhotoLinkXPath));
		String moreOptionsLinkXPath = "(//span[@class='soa-ui-cm-resources-menu-item-title'])[4]";
		linkMoreOptions = wd.findElement(By.xpath(moreOptionsLinkXPath));
	}
	
	public CM_Landing logoutOfCM() {
		logger.info("Logging out of CM ...");
		linkProfilePhoto.click();
		Sleeper.seconds(3, "Waiting for logout link to pop up");
		String logoutLinkXPath = "//a[@class='soa-control-cm-logout-link']";
		WebElement linkLogout = wd.findElement(By.xpath(logoutLinkXPath));
		linkLogout.click();
		return new CM_Landing();
	}
	
	public CM_ProfileDetails navigateToProfilePage() {
		logger.info("Navigating to profile page ...");
		linkProfilePhoto.click();
		Sleeper.seconds(3, "Waiting for profile link to pop up");
		String profileLinkXPath = "//a[@class='soa-control-cm-handle-page-dirty']";
		WebElement linkProfile = wd.findElement(By.xpath(profileLinkXPath));
		linkProfile.click();
		
		return new CM_ProfileDetails();
	}
	
	public BusinessAdmin navigateToAdminPage() {
		logger.info("Navigating to admin page ...");
		linkMoreOptions.click();
		Sleeper.seconds(3, "Waiting for admin link to pop up");
		String adminLinkXPath = "//a[@href='#/system/businessadmins']";
		//WebElement linkAdmin = Connection.getWebDriver().findElement(By.xpath(adminLinkXPath));
		WebElement linkAdmin = wd.findElement(By.xpath(adminLinkXPath));
		linkAdmin.click();
		
		return new BusinessAdmin();
	}
}
