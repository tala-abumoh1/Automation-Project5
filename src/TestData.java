import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestData {

    protected static WebDriver driver;
	String URL ="https://global.almosafer.com/en";
	String ExpectedLanguage="en";
	String ExpectedLanguage2="ar";
	String ExpectedCurrancy="SAR";
	String ExpectedContactNumber="+966554400000";
	
	boolean QitaLogoIsThere=true;
	Random rand=new Random();
	
	public void DefaultConfiguration() {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click(); 

	}
	
	 @BeforeSuite
	    public void setUp() {
	        if (driver == null) {
	            driver = new ChromeDriver();
	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	        }
	    }

	    @AfterSuite
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            driver = null;
	        }
	    }

	  
}
