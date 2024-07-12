import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPageTestCases extends TestData{

	
	public void HotelSelection() {
		WebElement HotelTab=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		
		WebElement searchButtonElement=driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
		
		if(driver.getCurrentUrl().contains("en")) {
			String [] English = {"Dubai","Jeddah","Riyadh"};
			int RandomIndex= rand.nextInt(English.length);
			searchButtonElement.sendKeys(English[RandomIndex]);
		}
		else if(driver.getCurrentUrl().contains("ar")){
			String [] Arabic= {"دبي","جدة"};
			int RandomIndex=rand.nextInt(Arabic.length);
			searchButtonElement.sendKeys(Arabic[RandomIndex]);
		}
		
	}
	
	
	public void NumberOfPepoleTest() {
		WebElement MyElement=driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select MySelector=new Select(MyElement);
		
		int RandomIndex=rand.nextInt(2);
		MySelector.selectByIndex(RandomIndex);
		
		driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
		
	}
	
	public void TestThePageIsFullyLoadedTest() {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofMinutes(1));
		WebElement resultTab =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[data-testid='HotelSearchResult__resultsFoundCount']")));
			

			org.testng.Assert.assertEquals(resultTab.getText().contains("found")||resultTab.getText().contains("وجدنا"), true);

	}
	
	public void sortItemsTest() throws InterruptedException{
		WebElement LowestPriceButton=driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceButton.click();
		
		Thread.sleep(1000);
		
		WebElement PriceContainer=driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> prices= PriceContainer.findElements(By.className("Price_Value"));
		
		String FirstPrice=prices.get(0).getText();
		String LastPrice=prices.get(prices.size()-1).getText();
		
		int firstPriceAsInt=Integer.parseInt(FirstPrice);
		int LastPriceAsInt=Integer.parseInt(LastPrice);
		
		org.testng.Assert.assertEquals(LastPriceAsInt>firstPriceAsInt,true);
		
	
	}
}
