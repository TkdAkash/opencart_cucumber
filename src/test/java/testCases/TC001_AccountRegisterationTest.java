package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import factory.BaseTest;
import pageObject.AccountRegisterPage;
import pageObject.HomePage;

public class TC001_AccountRegisterationTest extends BaseTest{

	@Test
	public void LoginTest() {
		
		logger.info("***** Starting TC001_AccountRegisterationTest *****");
		
		try {
			
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked Myaccount...");
		hp.clickRegister();
		logger.info("Clicked Register...");
		
		AccountRegisterPage acc = new AccountRegisterPage(driver);
		acc.setFirstName("aaa");
		logger.info("Entered first name...");
		acc.setLastName("bb");
		logger.info("Entered last name...");
		acc.setEmail(randomAlphaNumeric()+"@gmail.com");
		logger.info("Entered email...");
		acc.setTelephone("123456");
		logger.info("Entered phonenumber");
		String password = "1234";
		acc.setPassword(password);
		acc.setConfirmPassword(password);
		logger.info("Entered password and confirm password");
		acc.clickPrivacyPolicy();
		logger.info("Clicked Privacy policy ");
		acc.clickContinue();
		logger.info("Clicked Continue ");
		if(acc.isTestVisibleAccountCreated()) {
			Assert.assertEquals(acc.isTestVisibleAccountCreated(), true);			
		}
		else {
//			logger.debug("");
			logger.debug("***** Failed TC001_AccountRegisterationTest *****");
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegisterationTest *****");
		}catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getMessage());
			Assert.fail();
		}
	}
	
	
	
}
