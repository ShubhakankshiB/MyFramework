package ShubhakankshiBhukta.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ShubhakankshiBhukta.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shubhakankshiBhukta.pageobjects.CartPage;
import shubhakankshiBhukta.pageobjects.CheckoutPage;
import shubhakankshiBhukta.pageobjects.ConfirmationPage;
import shubhakankshiBhukta.pageobjects.LandingPage;
import shubhakankshiBhukta.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^Add the product (.+) to cart$")
	 public void Add_product_to_cart(String productName) throws InterruptedException
	 {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	 }
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation Page")
	public void message_displayed_Confirmation_message(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage() );
		driver.close();
    }

	

}
