package com.perforce.akana.qa.cm.admin;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.perforce.akana.qa.cm.login.CM_Header;
import com.perforce.akana.qa.cm.login.CM_Landing;
import com.perforce.akana.qa.cm.login.CM_Login;
import com.perforce.akana.qa.testng.datamodel.jax.User;
import com.perforce.akana.qa.testng.test.AkanaUITest;

public class BusinessAdminRelatedTests extends AkanaUITest {
	
	@BeforeMethod (alwaysRun = true)
	public void login(ITestContext context) {
        User user = getPrimaryUser(context);
        String url = user.getUrl();
        String userEmail = user.getUsername();
        String password = user.getPassword();
        CM_Landing landing = new CM_Landing(url);
        landing.clickLoginLink();
        CM_Login login = new CM_Login();
        login.loginToCM(userEmail, password);		
	}
	
    @AfterMethod(alwaysRun = true)
    private void logout() {
        CM_Header header = new CM_Header();
        header.logoutOfCM();
    }
    
    @Test(enabled=true, groups= {"BusinessAdminTests"}, priority=1)
    public void navigateToBusinessAdmin() {
    	logger.info("Test the navigation to Business Admin");
    	CM_Header header = new CM_Header();
    	header.navigateToAdminPage();   	
    }

}
