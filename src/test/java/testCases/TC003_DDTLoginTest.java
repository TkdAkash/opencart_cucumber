package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import factory.BaseTest;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviderManager;

public class TC003_DDTLoginTest extends BaseTest {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviderManager.class, groups = {"dataProvider"})
	public void verify_Login(String email, String password,String exp) {

		logger.info("***** Starting verify_DDTLogin *****");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked Myaccount Link...");
			hp.clickLogin();
			logger.info("Clicked Login...");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			logger.info("Entered email: "+email);
			lp.setPassword(password);
			logger.info("Entered password: "+password);
			lp.clickLogin();
			logger.info("Clicked Login...");
			
			MyAccountPage macc = new MyAccountPage(driver);
			if(macc.isTestVisible()) {
				Assert.assertEquals(macc.isTestVisible(), true);
				macc.clickLogout();
			}
			else {
				logger.debug("***** Login Failed with email: "+email+" Password: "+password);
				Assert.fail();
			}
			
			logger.info("***** Finished verify_Login *****");
			
		} catch (Exception e) {
			// TODO: handle exception\
			logger.debug(e.getMessage());
			Assert.fail();
		}
	}

}
