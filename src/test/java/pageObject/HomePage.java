package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//	locators
	@FindBy(xpath = "//span[text()='My Account']") WebElement txtMyAccount;
	@FindBy(linkText = "Register") WebElement lnkRegister;
	@FindBy(linkText = "Login") WebElement lnkLogin;
	
// Action method
	public void clickMyAccount() {
		txtMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	

	
}
