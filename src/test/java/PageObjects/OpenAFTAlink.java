package PageObjects;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import tests.TestBase;

public class OpenAFTAlink extends TestBase {
	// Using FindBy for locating elements
	@FindBy(how = How.XPATH, using = "//img[@class='image-afta']")
	public WebElement ImageAfta;
	@FindBy(how = How.XPATH, using = "//div[@class='atas-promo']")
	public WebElement AFTASITEIMAGE;

	public void checkAFTAPage() throws IOException, InterruptedException {
		ImageAfta.click();
		String MainWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);

				String ActualUrl = driver.getCurrentUrl();
				System.out.print(ActualUrl);
				String ExpectedUrl = "http://www.afta.com.au/";
				Assert.assertEquals(ActualUrl, ExpectedUrl);
			}
			
		}
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		  File src = ts.getScreenshotAs(OutputType.FILE);
		  FileHandler.copy(src,new File(System.getProperty("user.dir")+"\\test-output\\Screenshots\\AFTA_"+System.currentTimeMillis()+".png"));
	  
		
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
	}

}
