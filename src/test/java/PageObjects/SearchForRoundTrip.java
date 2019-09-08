package PageObjects;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.google.gson.JsonArray;

import tests.TestBase;

public class SearchForRoundTrip extends TestBase {
	// Using FindBy for locating elements
	@FindBy(how = How.NAME, using = "origin")
	public WebElement origin;
	@FindBy(how = How.NAME, using = "destination")
	public WebElement Destination;
	@FindBy(how = How.XPATH, using = "//div[@class='col-lg-5 form-date-pass']")
	public WebElement Dates;
	@FindBy(how = How.NAME, using = "d")
	public WebElement DepartDate;
	@FindBy(how = How.NAME, using = "a")
	public WebElement ReturnDate;
	
	@FindBy(how = How.CSS, using = ".available.today")
	public WebElement Date;
	
	@FindBy(how = How.XPATH, using = "//div[@class='bg-white text-sm h-50 px-3 rounded flex items-center text-primary-third font-medium el-popover__reference']")
	public WebElement Passengers;
	
	@FindBy(how = How.XPATH, using = "//input[@name='adult']")
	public WebElement AdultCount;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn uppercase btn-search-form font-bold lg:w-full w-2/5 m-auto btn-primary-second h-full']")
	public WebElement SearchButton;
	
	@FindBy(how = How.CSS, using = ".result-title")
	public WebElement SearchResults;
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	public void checkRoundTripSearch() throws InterruptedException {
		
		JsonArray cities = JsonObj.GetArray("Cities");
		
		String randomOriginCity = cities.get(ThreadLocalRandom.current().nextInt(1, cities.size())).getAsString();
		origin.click();
		origin.sendKeys(randomOriginCity);
		WebElement OriginAutoCompleteItem = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("highlighted")));
		OriginAutoCompleteItem.click();
		
		String randomDestCity = cities.get(ThreadLocalRandom.current().nextInt(1, cities.size())).getAsString();
		Destination.click();
		Destination.sendKeys(randomDestCity);
		WebElement DestAutoCompleteItem = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("highlighted")));
		DestAutoCompleteItem.click();
	
		jse.executeScript("availableDates = document.querySelectorAll('.available:not(.today)');");
		jse.executeScript("availableDates[Math.floor(Math.random() * (availableDates.length - 8)) + 8].click();");
		jse.executeScript("startDate = [].indexOf.call(document.querySelectorAll('.available:not(.today)'), document.querySelector('.start-date'));");
		jse.executeScript("availableDates[Math.floor(Math.random() * (availableDates.length - startDate)) + startDate].click();");

		int randomAdults = ThreadLocalRandom.current().nextInt(1, 8 + 1);
		Passengers.click();
		AdultCount.clear();
		AdultCount.sendKeys(Integer.toString(randomAdults));
		
		SearchButton.click();
		
		waitDriver.until(ExpectedConditions.visibilityOf(SearchResults));
		
		Assert.assertEquals(SearchResults.getText(),"FLY365 MIX AND MATCH FLIGHT OPTIONS");
	}
	

}