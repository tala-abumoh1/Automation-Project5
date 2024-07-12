

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class TestCases extends TestData{

	MainPageTestCases TC=new MainPageTestCases(); 
	HotelPageTestCases HTC= new HotelPageTestCases();
	
	@BeforeTest 
	public void mySetup() {
	DefaultConfiguration();
 	}
	
	@Test(description="MainPageTestCases", priority = 1)
	public void CheckTheLanguage() {
		TC.CheckTheLanguage();		 
	}
	
	
	@Test(description="MainPageTestCases", priority =2)
	public void CheckTheCurrancySAR() {
		TC.CheckTheCurrancySAR();
	}
	
	@Test(description="MainPageTestCases", priority =3)
	public void CheckTheContactNumber() {
		TC.CheckTheContactNumberTest();
	}
	
	
	@Test(description="MainPageTestCases", priority =4)
	public void CheckQitaLogoIfDisapled() {
		TC.CheckQitaLogoIfDisapledTest();
	}
	
	
	@Test(description="MainPageTestCases", priority=5)
	public void CheckHotelTabNotSelected() {
	TC.CheckHotelTabNotSelectedTest();
	}
	
	@Test(description="MainPageTestCases", priority=6)
	public void CheckDepartureAndReturnDate() {
		TC.CheckDepartureAndReturnDateTest();
		}
	
	@Test(description="MainPageTestCases", priority=7)
	public void ChangeTheLanguageRandomly() {
	TC.ChangeTheLanguageRandomlyTest();	 
	}
	
	@Test(description="MainPageTestCases", priority=8)
	public void HotelSelection() {
	HTC.HotelSelection();
	}
	
	
	@Test(description="MainPageTestCases", priority=9)
	public void NumberOfPepole() {
	HTC.NumberOfPepoleTest();
	}
	
	@Test(description="MainPageTestCases", priority=10)
	public void TestThePageIsFullyLoaded() {
		HTC.TestThePageIsFullyLoadedTest();
			}
	
	@Test(description="MainPageTestCases", priority=11)
	public void sortItems() throws InterruptedException {
	HTC.sortItemsTest(); 	
	}
}
