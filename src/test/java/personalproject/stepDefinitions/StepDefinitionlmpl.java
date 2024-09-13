package personalproject.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import personalproject.TestComponents.BaseTest;
import personalproject.pageobjects.CartPage;
import personalproject.pageobjects.CheckOutPage;
import personalproject.pageobjects.ConfirmationPage;
import personalproject.pageobjects.LandingPage;
import personalproject.pageobjects.ProductCatalogue;

public class StepDefinitionlmpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirMationPage;

	@Given("I landed on Ecommerce Website")
	public void i_landed_on_Ecommerce_Website() throws IOException {
		landingPage = launchApplication();

	}

	// (.+) bắt các tham số từ kịch bản (scenario) và truyền vào phương thức
	// ^ : Đảm bảo rằng chuỗi bắt đầu bằng "Logged in with username"
	// $: Đảm bảo rằng chuỗi kết thúc sau phần "password"
	// ^ (.+) $ luôn đi chung
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) throws InterruptedException {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("vietnam");
		confirMationPage = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) throws Throwable {
		String confirmMessage = confirMationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void error_message_displayed(String string1) throws Throwable {
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		
	}

}
