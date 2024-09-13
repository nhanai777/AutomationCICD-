package personalproject.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import personalproject.pageobjects.CartPage;
import personalproject.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsExecutor;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.jsExecutor = (JavascriptExecutor) driver;
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='/dashboard/myorders']")
	WebElement ordersHeader;
	
	public void clickElementByJs(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElementByJs() {
		jsExecutor.executeScript("window.scrollBy(0,100)");
	}

	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy ) {
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}


	public  void waitForElementToDisappear(WebElement element) {
		 wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
//	public  void waitForElementToDisappear1(By finBy) {
//		 wait.until(ExpectedConditions.invisibilityOfElementLocated(finBy));
//	}
	
//	public void waitForElementToBeClickable() {
//		
//	}
	
	public CartPage goToCartPage () {
		clickElementByJs(cartHeader);
//		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrdersPage () {
		clickElementByJs(ordersHeader);
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
