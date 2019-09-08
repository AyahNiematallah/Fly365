package tests;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Data.JSON;
import PageObjects.Login;
//Creating class to define all the constant I will use it later
public class TestBase {
	
 public static WebDriver driver;
 protected static JSON JsonObj;
 public static WebDriverWait waitDriver;
	
  @BeforeSuite
  public void IntilizeOrgnaization() throws IOException{
	  // Define chrome webdriver 
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+("\\src\\test\\java\\drivers\\chromedriver.exe"));
	  driver = new ChromeDriver() ;
	  driver.manage().window().maximize();	
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  waitDriver = new WebDriverWait(driver, 15);
	  
	  ClassLoader classLoader = new Login().getClass().getClassLoader();
	  File file = new File(classLoader.getResource("testdata.json").getFile());
	  JsonObj = new JSON(file.toString());
	   

	  driver.get("https://www.fly365stage.com/");
  }
 
  public void TakeScreenshot(ITestResult result) throws IOException {
	  TakesScreenshot ts = (TakesScreenshot)driver;
	  File src = ts.getScreenshotAs(OutputType.FILE);
	  FileHandler.copy(src,new File(System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+result.getName()+"_"+System.currentTimeMillis()+".png"));
  }
  
  
  
  @AfterSuite
  public void teerDownTest() {
	  //driver.close();
	  //driver.quit();
  }

}

