import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {

	WebDriver driver=new ChromeDriver();
	String URL ="https://global.almosafer.com/en";
	String ExpectedLanguage="en";
	String ExpectedCurrancy="SAR";
	String ExpectedContactNumber="+966554400000";
	boolean QitaLogoIsThere=true;
	
	@BeforeTest 
	public void mySetup() {
	driver.get(URL);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click(); 
	}
	
	@Test
	public void CheckTheLanguage() {
		WebElement HTMLTag =driver.findElement(By.tagName("html"));
		String ActualLanguage=HTMLTag.getAttribute("lang");
		
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		 
	}
	
	//
	@Test
	public void CheckTheCurrancySAR() {
		String ActualCurrancy=driver.findElement(By.xpath("//button[@data-testid ='Header__CurrencySelector']")).getText();
		org.testng.Assert.assertEquals(ActualCurrancy, ExpectedCurrancy);
	}
	
	@Test
	public void CheckTheContactNumber() {
	String ActualContactNumber=driver.findElement(By.tagName("strong")).getText();
	Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}
	
	//
	@Test
	public void CheckQitaLogoIfDisapled() {
		//boolean  ActualLogo =driver.findElement(By.xpath("//svg[@data-testid='Footer_QitafLogo']")).isDisplayed();
		WebElement FooterDiv=driver.findElement(By.tagName("footer"));
		boolean ActualLogo=FooterDiv.findElement(By.xpath("//svg[@data-testid='Footer__QitafLogo']")).isDisplayed();
		Assert.assertEquals(ActualLogo, QitaLogoIsThere);
	}
	
	
	
}
