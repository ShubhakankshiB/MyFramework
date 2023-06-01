package ShubhakankshiBhukta.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ShubhakankshiBhukta.TestComponents.BaseTest;
import ShubhakankshiBhukta.TestComponents.Retry;
import shubhakankshiBhukta.pageobjects.CartPage;
import shubhakankshiBhukta.pageobjects.CheckoutPage;
import shubhakankshiBhukta.pageobjects.ConfirmationPage;
import shubhakankshiBhukta.pageobjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest


{

	@Test(groups= {"ErrorHandling"},retryAnalyzer= Retry.class)
	public void  LoginError() throws InterruptedException, IOException{

	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue= landingPage.loginApplication("skbhukta@selenium.com", "123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage() );
	}

	}
	
	@Test
	public void  ProductErrorValidation() throws InterruptedException, IOException{

	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue= landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		productCatalogue.getProductList();
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage= productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA");
		Assert.assertFalse(match);
		

	}


}
}
