package ShubhakankshiBhukta.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ShubhakankshiBhukta.TestComponents.BaseTest;
import shubhakankshiBhukta.pageobjects.CartPage;
import shubhakankshiBhukta.pageobjects.CheckoutPage;
import shubhakankshiBhukta.pageobjects.ConfirmationPage;
import shubhakankshiBhukta.pageobjects.OrderPage;
import shubhakankshiBhukta.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest

{
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" }) // purchasing different items with different users
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		{

			ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
			productCatalogue.getProductList();
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(input.get("productName"));

			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
			Assert.assertTrue(match);

			CheckoutPage checkoutPage = cartPage.goToCheckout();
			checkoutPage.selectCountry("India");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();
			String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		}
	}

	@Test(dependsOnMethods = { "submitOrder" })

	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("skbhukta@selenium.com", "Learn@1991");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		

		List<HashMap<String,String>> data  = getJsonDataToMap("/Users/ruchi/eclipse-workspace/SeleniumTraining/SeleniumFrameworkDesign/src/test/java/ShubhakankshiBhukta/data/PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };

	}
}

/* HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "skbhukta@selenium.com");
		map.put("password", "Learn@1991");
		map.put("productName", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "rahulshetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ADIDAS ORIGINAL"); */
