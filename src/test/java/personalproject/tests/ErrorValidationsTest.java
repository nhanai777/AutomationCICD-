package personalproject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;



import personalproject.TestComponents.BaseTest;
import personalproject.TestComponents.Retry;
import personalproject.pageobjects.CartPage;
import personalproject.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidations() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
//		LandingPage landingPage = launchApplication();
		landingPage.loginApplication("wrongemail@gmail.com", "wrongpassword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	@Test(groups = {"ErrorHandling"})
	public void ProductErrorValidations() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
//		LandingPage landingPage = launchApplication();
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL");
		AssertJUnit.assertFalse(match);
		
		
	}
}
