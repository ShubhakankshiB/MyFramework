package shubhakankshiBhukta.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhakankshiBhukta.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement submit;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')][1]")
	WebElement selectCountry;
	
	By results = By.xpath("//section[contains(@class,'ta-results')]");
	
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		waitFOrElementToAppear(results);
		selectCountry.click();
		
	}

	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
		// TODO Auto-generated constructor stub
	}
	
	


