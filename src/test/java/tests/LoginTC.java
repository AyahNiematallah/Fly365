package tests;

import java.io.IOException;

import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class LoginTC extends TestBase{
	public static void Login() throws JsonException, IOException {
		PageObjects.Login login = PageFactory.initElements(driver, PageObjects.Login.class);
		
		login.Login();
	}
	
	@AfterMethod
	public void AfterMethod(ITestResult result) throws IOException {
		TakeScreenshot(result);
	}
}
