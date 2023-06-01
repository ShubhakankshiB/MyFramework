package ShubhakankshiBhukta.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import shubhakankshiBhukta.pageobjects.LandingPage;

public class StandAloneTest

{

	public static void main(String[] args)

	{

		String productName = "ZARA COAT 3";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage lp=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("skbhukta@selenium.com");
		driver.findElement(By.id("userPassword")).sendKeys("Learn@1991");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
		WebElement prod = products.stream().filter(product ->
		product.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
		List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		Boolean exists=cartProducts.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(exists);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][1]")).click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String tytext= driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).getText();
		Assert.assertTrue(tytext.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
	}

}
