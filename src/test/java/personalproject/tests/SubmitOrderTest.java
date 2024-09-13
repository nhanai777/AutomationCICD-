package personalproject.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import personalproject.TestComponents.BaseTest;
import personalproject.pageobjects.CartPage;
import personalproject.pageobjects.CheckOutPage;
import personalproject.pageobjects.ConfirmationPage;
import personalproject.pageobjects.OrdersPage;
import personalproject.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

//		LandingPage landingPage = launchApplication();

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		AssertJUnit.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("vietnam");

		ConfirmationPage confirMationPage = checkOutPage.submitOrder();
		String confirmMessage = confirMationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("vuthinhanai@gmail.com", "Nhanai44@");
		OrdersPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyProductDisplay(productName));
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/personalproject/data/PurchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(path);
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "vuthinhanai@gmail.com", "Nhanai44@", "ZARA COAT 3" },
//				{ "anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };
//	}

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email","vuthinhanai@gmail.com");//put(key, value)
//	map.put("password","Nhanai44@");
//	map.put("product","ZARA COAT 3");
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email","anshika@gmail.com");
//	map1.put("password","Iamking@000");
//	map1.put("product","ADIDAS ORIGINAL");
}
