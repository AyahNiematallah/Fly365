package PageObjects;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import tests.TestBase;

public class Login extends TestBase {
	@FindBy(how=How.LINK_TEXT, using="SIGN IN") public WebElement BtnLogin;
	
	@FindBy(how=How.CSS, using="input[type=email]") public WebElement txtLoginEmail;
	@FindBy(how=How.CSS, using="input[type=password]") public WebElement txtLoginPass;
	
	@FindBy(how=How.CSS, using="button[type=submit]") public WebElement BtnSubmitLogin;
	
	public void Login() throws JsonException, IOException {
		BtnLogin.click();
		
		waitDriver.until(ExpectedConditions.elementToBeClickable(txtLoginEmail));
		
		String userEmail = JsonObj.GetValue("email");
		String userPassword = JsonObj.GetValue("password");
		
		txtLoginEmail.sendKeys(userEmail);
		txtLoginPass.sendKeys(userPassword);

		txtLoginPass.submit();
	}
}
