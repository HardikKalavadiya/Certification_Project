package tests;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import testComponents.BaseTest;

public class GuestUserInteractionsTest extends BaseTest{

	@Test(groups= {"guestUser"})
	public void moveToHomePage() {
		loginPg.goToHomePage();
		
	}
	
	@Test(groups= {"guestUser"})
	public void openCertificationPage() throws InterruptedException {
		loginPg.goToHomePage();
		HomePage homePg = new HomePage(driver);
		homePg.goToCertificationPage();
	}
	
	
}
