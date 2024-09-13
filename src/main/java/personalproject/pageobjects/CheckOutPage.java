package personalproject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import personalproject.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submitButton;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException {
		Thread.sleep(4000);
		Actions a = new Actions(driver);
		 a.sendKeys(country,countryName).build().perform();
		 waitForElementToAppear(results);
		 scrollToElementByJs();
//		 a.sendKeys(Keys.ARROW_DOWN)
//		 .click()
//		 .build()
//		 .perform();
		 clickElementByJs(selectCountry);
		 //selectCountry.click();
		 scrollToElementByJs();
	}

	public ConfirmationPage submitOrder() {
		clickElementByJs(submitButton);
   //submitButton.click();
		return new ConfirmationPage(driver);
	}
}

//Thread.sleep(4000); // To slow the ễxecution
//Actions a = new Actions(driver);
//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//js.executeScript("window.scrollBy(0,100)");
//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//js.executeScript("window.scrollBy(0,100)");
////wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
//driver.findElement(By.cssSelector(".action__submit")).click();