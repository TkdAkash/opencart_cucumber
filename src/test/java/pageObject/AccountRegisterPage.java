package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {

	public AccountRegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//	locatots
	@FindBy(id = "input-firstname")
	WebElement txtFirstName;
	@FindBy(id = "input-lastname")
	WebElement txtLastName;
	@FindBy(id = "input-email")
	WebElement txtEmail;
	@FindBy(id = "input-telephone")
	WebElement txtTelephone;
	@FindBy(id = "input-password")
	WebElement txtPassword;
	@FindBy(id = "input-confirm")
	WebElement txtConfirmPassword;
	@FindBy(name = "agree")
	WebElement chkPolicy;
	@FindBy(css = "input[value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement txtConfirmMessageAccountCreated;

//	Action methods
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void setConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}

	public void clickPrivacyPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {

//		sol1
		btnContinue.click();

//		sol2
//		btnContinue.submit();

//		sol3
//		Actions act = new Actions(driver);
//		act.moveToElement(btnContinue).click().perform();

//		sol4
//		btnContinue.sendKeys(Keys.RETURN);

//		sol5
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click()", btnContinue);

//		sol6
//		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	public boolean isTestVisibleAccountCreated() {
		
		try {
			return txtConfirmMessageAccountCreated.isDisplayed();
		}catch (Exception e) {
			return false;
		}
	}
	
}
