package shubhakankshiBhukta.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhakankshiBhukta.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	
	@FindBy(xpath = "//tr //td[2]")
	private List<WebElement> productnames;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutEle;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productnames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
