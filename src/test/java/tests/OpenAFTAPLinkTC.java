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

import PageObjects.OpenAFTAlink;

@Test
public class OpenAFTAPLinkTC extends TestBase {

	public static void AFTAcode() throws IOException, InterruptedException {

		OpenAFTAlink AFTA = PageFactory.initElements(driver, OpenAFTAlink.class);
	
		AFTA.checkAFTAPage();
		

	}
}