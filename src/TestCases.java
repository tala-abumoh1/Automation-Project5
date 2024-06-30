import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {

	WebDriver driver=new ChromeDriver();
	String URL ="https://global.almosafer.com/en";
	String ExpectedLanguage="en";
	String ExpectedLanguage2="ar";
	String ExpectedCurrancy="SAR";
	String ExpectedContactNumber="+966554400000";
	boolean QitaLogoIsThere=true;
	Random rand=new Random();
	
	@BeforeTest 
	public void mySetup() {
	driver.get(URL);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click(); 
	}
	
	@Test(enabled=false)
	public void CheckTheLanguage() {
		WebElement HTMLTag =driver.findElement(By.tagName("html"));
		String ActualLanguage=HTMLTag.getAttribute("lang");
		
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		 
	}
	
	
	@Test(enabled=false)
	public void CheckTheCurrancySAR() {
		String ActualCurrancy=driver.findElement(By.xpath("//button[@data-testid ='Header__CurrencySelector']")).getText();
		org.testng.Assert.assertEquals(ActualCurrancy, ExpectedCurrancy);
	}
	
	@Test(enabled=false)
	public void CheckTheContactNumber() {
	String ActualContactNumber=driver.findElement(By.tagName("strong")).getText();
	Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}
	
	
	@Test(enabled=false)
	public void CheckQitaLogoIfDisapled() {
		WebElement FooterDiv=driver.findElement(By.tagName("footer"));
		
		boolean ActualLogo=FooterDiv.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg")).isDisplayed();
		org.testng.Assert.assertEquals(ActualLogo, QitaLogoIsThere);
	}
	
	
	@Test(enabled=false)
	public void CheckHotelTabNotSelected() {
		String ExpectedValue="false";
		String ActualValue=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
	org.testng.Assert.assertEquals(ActualValue,ExpectedValue);
	}
	
	@Test(enabled=false)
	public void CheckDepartureAndReturnDate() {
		LocalDate today=LocalDate.now();
		int ExpectedDepartureDate=today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDate=today.plusDays(2).getDayOfMonth();
		
		String ActualDepartureDate=driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']")).getText();
		String ActualReturnDate=driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']")).getText();
		
//		System.out.println(ExpectedDepartureDate);
//		System.out.println(ExpectedReturnDate);
//		System.out.println(ActualDepartureDate);
//		System.out.println(ActualReturnDate);
		
		int ActualDepartureDateAsInt=Integer.parseInt(ActualDepartureDate);
		int ActualReturnDateAsInt=Integer.parseInt(ActualReturnDate);
		
		org.testng.Assert.assertEquals(ActualDepartureDateAsInt, ExpectedDepartureDate);
		org.testng.Assert.assertEquals(ActualReturnDateAsInt, ExpectedReturnDate);
	}
	
	@Test(priority=1)
	public void ChangeTheLanguageRandomly() {
		 String [] website= {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
		 
		 int randomIndex=rand.nextInt(website.length);
		 driver.get(website[randomIndex]);
		 
		 if(driver.getCurrentUrl().contains("en")) {
			 WebElement HTMLTag =driver.findElement(By.tagName("html"));
			 String ActualLanguage=HTMLTag.getAttribute("lang");
				
			 Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		 }
		 else if(driver.getCurrentUrl().contains("ar")) {
			 WebElement HTMLTag =driver.findElement(By.tagName("html"));
				String ActualLanguage=HTMLTag.getAttribute("lang");
				
				Assert.assertEquals(ActualLanguage, ExpectedLanguage2);
				 
		 }
	}
	
	@Test(priority=2)
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
	
	
	@Test(priority=3)
	public void NumberOfPepole() {
	WebElement MyElement=driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
	Select MySelector=new Select(MyElement);
	
	int RandomIndex=rand.nextInt(2);
	MySelector.selectByIndex(RandomIndex);
	
	driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
	}
	
	@Test(priority=4)
	public void TestThePageIsFullyLoaded() {
		
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofMinutes(1));
		WebElement resultTab =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[data-testid='HotelSearchResult__resultsFoundCount']")));
			

			org.testng.Assert.assertEquals(resultTab.getText().contains("found")||resultTab.getText().contains("وجدنا"), true);
	}
	
	@Test(priority=5)
	public void sortItems() throws InterruptedException {
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
