package personalproject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import personalproject.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver ;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> productOrder;
	
	@FindBy(css= ".totalRow button")
	WebElement checkOutButton;
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = productOrder.stream()
				.anyMatch(Product -> Product.getText().equalsIgnoreCase(productName));
		return match ;
	}
	
	
	public CheckOutPage goToCheckOut () {
		clickElementByJs(checkOutButton);
//		checkOutButton.click();
		return new CheckOutPage(driver);
	}
}
