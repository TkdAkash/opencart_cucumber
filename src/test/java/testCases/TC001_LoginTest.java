package testCases;

import org.testng.annotations.Test;

import factory.BaseTest;
import pageObject.HomePage;

public class TC001_LoginTest extends BaseTest {

	@Test
	public void verify_Login() {
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		
	}
	
}
