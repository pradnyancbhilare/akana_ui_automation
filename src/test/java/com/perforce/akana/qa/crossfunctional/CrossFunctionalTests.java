package com.perforce.akana.qa.crossfunctional;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.perforce.akana.qa.adminconsole.installedfeatures.AC_InstalledFeatures;
import com.perforce.akana.qa.adminconsole.login.AC_Home;
import com.perforce.akana.qa.adminconsole.login.AC_Login;
import com.perforce.akana.qa.cm.login.CM_Header;
import com.perforce.akana.qa.cm.login.CM_Landing;
import com.perforce.akana.qa.cm.login.CM_Login;
import com.perforce.akana.qa.envision.dashboards.EV_Dashboard;
import com.perforce.akana.qa.envision.dashboards.EV_NewDashboard;
import com.perforce.akana.qa.envision.login.EV_Header;
import com.perforce.akana.qa.envision.login.EV_Login;
import com.perforce.akana.qa.lm.login.LM_Home;
import com.perforce.akana.qa.lm.login.LM_Login;
import com.perforce.akana.qa.pm.dashboard.PM_AddDashboard;
import com.perforce.akana.qa.pm.dashboard.PM_Dashboard;
import com.perforce.akana.qa.pm.login.PM_Header;
import com.perforce.akana.qa.pm.login.PM_Login;
import com.perforce.akana.qa.testng.datamodel.jax.User;
import com.perforce.akana.qa.testng.test.AkanaUITest;

public class CrossFunctionalTests extends AkanaUITest {
	
	private String user1Url = null;
	private String user1Name = null;
	private String user1Password = null;
	
	private String user2Url = null;
	private String user2Name = null;
	private String user2Password = null;
	
	private String user3Url = null;
	private String user3Name = null;
	private String user3Password = null;
	
	private String user4Url = null;
	private String user4Name = null;
	private String user4Password = null;
	
	private String user5Url = null;
	private String user5Name = null;
	private String user5Password = null;
	
	@BeforeClass (alwaysRun = true)
	public void init(ITestContext context) {
        User user1 = getPrimaryUser(context);
        user1Url = user1.getUrl();
        user1Name = user1.getUsername();
        user1Password = user1.getPassword();
        
        User user2 = getSecondaryUser(context);
        user2Url = user2.getUrl();
        user2Name = user2.getUsername();
        user2Password = user2.getPassword();
        
        User user3 = getTertiaryUser(context);
        user3Url = user3.getUrl();
        user3Name = user3.getUsername();
        user3Password = user3.getPassword();
        
        User user4 = getQuaternaryUser(context);
        user4Url = user4.getUrl();
        user4Name = user4.getUsername();
        user4Password = user4.getPassword();
        
        User user5 = getQuinaryUser(context);
        user5Url = user5.getUrl();
        user5Name = user5.getUsername();
        user5Password = user5.getPassword();
	}
	
	
	@AfterClass (alwaysRun = true)
	public void teardown() {
		
	}
	
	@Test(enabled=true, groups= {"CMBusinessAdminTests"}, priority=1)
	public void doSomethingInCmAdmin() {
        CM_Landing landing = new CM_Landing(user1Url);
        landing.clickLoginLink();
        CM_Login login = new CM_Login();
        login.loginToCM(user1Name, user1Password);	
    	CM_Header header = new CM_Header();
    	header.navigateToAdminPage();
        header = new CM_Header();
        header.logoutOfCM();
	}
	
	//@Test(enabled=true, groups= {"PMRegistryTests"}, priority=1, dependsOnMethods={"doSomethingInCmAdmin"})
	@Test(enabled=true, groups= {"PMRegistryTests"}, priority=2)
	public void doSomethingInPmRegistry() {
		PM_Login login = new PM_Login(user2Url);
		login = new PM_Login(user2Url);
		login.loginToPM(user2Name, user2Password);
		PM_Header header = new PM_Header();
		header.navigateToProfile();
		header = new PM_Header();
		header.navigateToDashboard();
		PM_Dashboard dashboard = new PM_Dashboard();
		dashboard.clickAddDashboard();
		dashboard.switchToNewWindow();
		PM_AddDashboard addDashboard = new PM_AddDashboard();
		addDashboard.cancelAddDashboardAction();
		dashboard.switchToNewWindow();
		header = new PM_Header();
		header.logoutOfPM();
	}
	
//	@Test(enabled=true, groups= {"EnvisionChartsTests"}, priority=1, dependsOnMethods={"doSomethingInCmAdmin",
//			"doSomethingInPmRegistry"})
	@Test(enabled=true, groups= {"EnvisionChartsTests"}, priority=3)
	public void doSomethingWithEnvisionChart() {
		EV_Login login = new EV_Login(user3Url);
		login.loginToEnvision(user3Name, user3Password);
		EV_Header header = new EV_Header();
		header.navigateToSettings();
		new EV_Header().navigateToCharts();
		new EV_Header().navigateToDatasets();
		new EV_Header().navigateToDashboard();
		EV_Dashboard dash = new EV_Dashboard();
		dash.configureDashboard();
		new EV_NewDashboard().AddNewDashboard();
		new EV_Header().logoutOfEnvision();
	}
	
	//	@Test(enabled=true, groups= {"LifecycleManagerMainTests"}, priority=1, dependsOnMethods={"doSomethingInCmAdmin",
	//	"doSomethingInPmRegistry"})
	@Test(enabled=true, groups= {"LifecycleManagerMainTests"}, priority=4)
	public void doSomethingWithLifeCycleManagerMainMenu() {
		LM_Login login = new LM_Login(user4Url);
		login.loginToLifeCycleManager(user4Name, user4Password);
		LM_Home home = new LM_Home();
		home.navigateToSettings();
		home.logoutOfLM();
	}
	
	//	@Test(enabled=true, groups= {"AdminConsoleMainTests"}, priority=1, dependsOnMethods={"doSomethingInCmAdmin",
	//	"doSomethingInPmRegistry"})
	@Test(enabled=true, groups= {"AdminConsoleMainTests"}, priority=5)
	public void doSomethingWithAdminConsoleMenu() {
		AC_Login login = new AC_Login(user5Url);
		login.loginToAdminConsole(user5Name, user5Password);
		AC_Home home = new AC_Home();
		home.navigateToInstalledFeatures();
		AC_InstalledFeatures installedFeatures = new AC_InstalledFeatures();
		installedFeatures.openSearchForUpdates();
		home.navigateToConfiguration();
		home.navigateToRepository();
		home.navigateToHealth();
		home.navigateToSystem();
		home.navigateToAvailableFeatures();
		home.logoutOfAdminConsole();
	}

}
