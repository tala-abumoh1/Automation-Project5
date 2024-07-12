import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MainPageTestCases extends TestData{

	public void CheckTheLanguage() {
		WebElement HTMLTag =driver.findElement(By.tagName("html"));
		String ActualLanguage=HTMLTag.getAttribute("lang");
		
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}
	
	public void CheckTheCurrancySAR() {

		String ActualCurrancy=driver.findElement(By.xpath("//button[@data-testid ='Header__CurrencySelector']")).getText();
		org.testng.Assert.assertEquals(ActualCurrancy, ExpectedCurrancy);

	}
	
	public void CheckTheContactNumberTest() {

		String ActualContactNumber=driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}
	
	public void CheckQitaLogoIfDisapledTest() {
		WebElement FooterDiv=driver.findElement(By.tagName("footer"));
		
		boolean ActualLogo=FooterDiv.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg")).isDisplayed();
		org.testng.Assert.assertEquals(ActualLogo, QitaLogoIsThere);
	}
	
	public void CheckHotelTabNotSelectedTest() {
		String ExpectedValue="false";
		String ActualValue=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
	org.testng.Assert.assertEquals(ActualValue,ExpectedValue);

	}
	
	public void CheckDepartureAndReturnDateTest() {

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
	
	public void ChangeTheLanguageRandomlyTest() {
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
}
