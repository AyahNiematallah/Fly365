package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import tests.TestBase;

public class SignUp extends TestBase {

	WebDriver driver;
	public static int NoOfAdmins;
	public static int ValueOfUsers;

	public SignUp(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.LINK_TEXT, using = "SIGN IN")
	public WebElement SignIn;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign up")
	public WebElement SignUp;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='First Name']")
	public WebElement FirstName;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Family Name']")
	public WebElement FamilyName;
	
	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	public WebElement Email;
	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	public WebElement Password;
	
	@FindBy(how = How.CLASS_NAME, using = "el-notification__content")
	public WebElement RegisteredNotifictaion;
	
	public void CheckSignUp() throws Exception {

		String userEmail = JsonObj.GetValue("email");
		String userPassword = JsonObj.GetValue("password");
		String userFirstName = JsonObj.GetValue("firstname");
		String userLastName = JsonObj.GetValue("lastname");
		
		SignIn.click();
		
		SignUp.click();
		
		FirstName.sendKeys(userFirstName);
		FamilyName.sendKeys(userLastName);
		
		Email.sendKeys(userEmail);
		Password.sendKeys(userPassword);
		Password.submit();
		
		waitDriver.until(ExpectedConditions.elementToBeClickable(RegisteredNotifictaion));
		
		String ActualNotification = RegisteredNotifictaion.getText();
		String ExpectedNotification = "You will receive an email shortly to verify your email address.";
		Assert.assertEquals(ActualNotification,ExpectedNotification);
		
		Thread.sleep(5000);
		
		String VerifyUrl = EmailHelper.getVerifyUrl(userEmail, userPassword);
		driver.get(VerifyUrl);
		
	}

}