package personalproject.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import personalproject.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Khởi tạo các WebElement với PageFactory
		PageFactory.initElements(driver, this);
		// "this" là Page Object
		// PageFactory, được sử dụng để khởi tạo các phần tử web (WebElements) trong một
		// đối tượng lớp (class) đã cho.
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css ="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	public ProductCatalogue loginApplication (String email,String pass) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
