package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (css = "#NavDropDown0")
	WebElement certificationNavBar;
	
	@FindBy (linkText = "Certification")
	WebElement certificationPageLink;
	
	public void goToCertificationPage() throws InterruptedException {
		
		Actions a = new Actions(driver);
		waitForElementToAppear(certificationNavBar);
		a.moveToElement(certificationNavBar).build().perform();
		waitForElementToAppear(certificationPageLink);
		a.moveToElement(certificationPageLink).click().build().perform();
		
	}

}
