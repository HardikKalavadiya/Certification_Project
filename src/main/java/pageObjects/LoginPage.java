package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "email")
	WebElement userEmail;
	
	@FindBy(id = "password")
	WebElement userPassword;
	
	@FindBy(css = ".align-self-start")
	WebElement loginButton;
	
	@FindBy (xpath = "//button[@class='btn btn-secondary']")
	WebElement createAccount;
	
	@FindBy (xpath = "//input[@type='password']/following-sibling::div")
	WebElement validationText;
	
	@FindBy (css = "picture img")
	WebElement homePage;
	
	
	public void loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		//scrollByLocation(driver);
		System.out.println(loginButton.getText());
		loginButton.click();
	}
	
	public void goToLogin(String testUrl) {
		driver.get(testUrl);
	}
	
	public void openCreateAccount() {
		createAccount.click();
	}
	
	public String getValidationText() {
		waitForElementToAppear(validationText);
		String validation = validationText.getText();
		return validation;
	}
	
	public void goToHomePage() {
		homePage.click();
		}
}
