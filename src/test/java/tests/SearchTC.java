package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import PageObjects.SearchForRoundTrip;

@Test
public class SearchTC extends TestBase {

	public static void Searchcode() throws InterruptedException {

		SearchForRoundTrip Search = PageFactory.initElements(driver, SearchForRoundTrip.class);
		// loginPage.checkInvalidLogin();
		Search.checkRoundTripSearch();
		

	}

	@AfterMethod
	public void AfterMethod(ITestResult result) throws IOException {
		TakeScreenshot(result);
	}
}