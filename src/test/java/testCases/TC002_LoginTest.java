package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import factory.BaseTest;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC002_LoginTest extends BaseTest {

	@Test
	public void verify_Login() {

		logger.info("***** Starting verify_Login *****");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked Myaccount...");
			hp.clickLogin();
			logger.info("Clicked Login...");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			logger.info("Entered email...");
			lp.setPassword(p.getProperty("password"));
			logger.info("Entered password... ");
			lp.clickLogin();
			logger.info("Clicked Login...");
			
			MyAccountPage macc = new MyAccountPage(driver);
			if(macc.isTestVisible()) {
				Assert.assertEquals(macc.isTestVisible(), true);
				macc.clickLogout();
			}
			else {
				logger.debug("***** Login Failed *****");
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
