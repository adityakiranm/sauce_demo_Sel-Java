package z_backup;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*NOTES
 * Classes like ChromeDriver, FirefoxDriver, SafariDriver etc implement the WebDriver Interface
 * and provide their own implementation to the WebDriver methods
 * An object of chrome driver referring to the implementations of web driver is created so that
 * we implement generic methods only and not methods specific to chrome driver thus allowing
 * the flexibility to execute the same tests by configuring other drivers also*/

public class Raw_E2EFlow {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");	//Manually download Chrome driver to system and setup 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sauce-demo.myshopify.com/");
		Actions action = new Actions(driver);
		
		//Verify Home page is displayed
		if(driver.findElement(By.xpath("//a/img[contains(@src, 'sauce-demo.myshopify.com/cdn/shop/t/2/assets/logo')]")).isDisplayed()) {
			System.out.println("User is directed to the sauce-demo website successfully");
			Thread.sleep(2000);
		} else {
			System.out.println("User could NOT be directed to the sauce-demo website");
		}
		
		//Click on Log In link
		if(driver.findElement(By.linkText("Log In")).isDisplayed()) {
			driver.findElement(By.linkText("Log In")).click();
			Thread.sleep(2000);
			System.out.println("Log In link has been clicked successfully");
		} else {
			System.out.println("Log In link could not be clicked");
		}
		
		//Enter Email Address, Password and click Sign In
		if(driver.findElement(By.xpath("//h1[contains(text(), 'Customer Login')]")).isDisplayed()) {
			System.out.println("Customer Login page is displayed successfully");
		} else {
			System.out.println("Customer Login page is not displayed");
		}
		if(driver.findElement(By.cssSelector("#customer_email")).isDisplayed()) {
			driver.findElement(By.cssSelector("#customer_email")).sendKeys("adityakiran.m@gmail.com");
			driver.findElement(By.cssSelector("#customer_password")).sendKeys("RegressionTester");
			driver.findElement(By.xpath("//input[contains(@value, 'Sign In')]")).click();
			System.out.println("Customer Sign In is successful");
			Thread.sleep(3000);
		} else {
			System.out.println("Customer Sign In is not successful");
		}
		
		//Click on "Catalog" link displayed on the left-nav pane
		if(driver.findElement(By.xpath("//a[text() = 'Catalog']")).isDisplayed()) {
			driver.findElement(By.xpath("//a[text() = 'Catalog']")).click();
			Thread.sleep(2000);
			System.out.println("Catalog link on the left-nav pane has been clicked successfully");
		} else {
			System.out.println("Catalog link on the left-nav pane could not be clicked");
		}
		
		//Select the Product and land on Product details page
		if(driver.findElement(By.xpath("//h3[text() = 'Noir jacket']/preceding-sibling::img")).isDisplayed()
				&& !(driver.findElements(By.xpath("//h3[text() = 'Noir jacket']/preceding-sibling::div")).size()>0)) {
			driver.findElement(By.xpath("//h3[text() = 'Noir jacket']/preceding-sibling::img")).click();
			System.out.println("Product has been selected successfully");
			Thread.sleep(2000);
			if(driver.findElement(By.cssSelector("#breadcrumb")).isDisplayed() && 
					driver.findElement(By.xpath("//div[@id =  'breadcrumb']//span[contains(text(), 'Home')]")).isDisplayed() &&
					driver.findElement(By.xpath("//div[@id =  'breadcrumb']//a[contains(text(), 'Noir jacket')]")).isDisplayed()) {
				System.out.println("User landed on the correct product details page successfully");
			} else {
				System.out.println("User could not land on product details page");
			}
		} else {
			System.out.println("Product could not be selected");
		}
		
		//Select product size, color and click on Add to Cart button
		Select selectSize = new Select(driver.findElement(By.cssSelector("#product-select-option-0")));
		selectSize.selectByValue("M");
		
		Select selectColor = new Select(driver.findElement(By.cssSelector("#product-select-option-1")));
		selectColor.selectByValue("Blue");
		
		if(driver.findElement(By.cssSelector("#add")).isDisplayed()) {
			driver.findElement(By.cssSelector("#add")).click();
			System.out.println("Product has been added to cart succesfully");
			Thread.sleep(3000);
		} else {
			System.out.println("Product could not be added to cart");
		}
		
		//Select My Cart and verify if user has been navigated to My Cart / Check Out page
		if(driver.findElement(By.xpath("//*[contains(text(), 'Check Out')]")).isDisplayed()) {
			driver.findElement(By.xpath("//*[contains(text(), 'Check Out')]")).click();
			System.out.println("My Cart has been selected successfully");
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//h1[text() = 'My Cart']")).isDisplayed()) {
				System.out.println("My Cart is displayed");
			} else {
				System.out.println("My Cart page is not displayed");
			}
		} else {
			System.out.println("My Cart has not been selected");
		}
		
		//Select Check Out option
		if(driver.findElement(By.cssSelector("#checkout")).isDisplayed()) {
			driver.findElement(By.cssSelector("#checkout")).click();
			System.out.println("Check Out button has been clicked successfully");
			Thread.sleep(3000);
			if(driver.findElement(By.cssSelector("#deliveryAddress")).isDisplayed()) {
				System.out.println("Shipping and Payments page is displayed");
			} else {
				System.out.println("Shipping and Payments page is not displayed");
			}
		} else {
			System.out.println("Check Out button could not be clicked");
		}
		
		//Complete entering details on Shipping page and verify if "Pay now" is displayed and enabled
		if(driver.findElement(By.xpath("(//div[contains(@aria-labelledby, 'Resource')]//p)[1]")).getText().equalsIgnoreCase("Noir jacket")) {
			System.out.println("Selected product is displayed in Shipping page successfully");
		} else {
			System.out.println("Selected product is not displayed in Shipping page");
		}
		if(driver.findElement(By.xpath("//div[contains(@aria-labelledby, 'Money')]//abbr/following-sibling::*")).getText().equalsIgnoreCase("80.00")) {
			System.out.println("Total Bill Amount, including shipping cost, has been validated successfully");
		} else {
			System.out.println("Total Bill Amount value is incorrect");
		}
		
		if(driver.findElement(By.cssSelector("input[name = 'email']")).isDisplayed()) {
			driver.findElement(By.cssSelector("input[name = 'email']")).sendKeys("test.tester@outlook.com");
			System.out.println("Email has been entered as test.tester@outlook.com");
		} else {
			System.out.println("Email value could not been entered");
		}
		if(driver.findElement(By.cssSelector("#marketing_opt_in")).isDisplayed()) {
			driver.findElement(By.cssSelector("#marketing_opt_in")).click();
			System.out.println("Email me checkbox has been unchecked");
		} else {
			System.out.println("Email me checkbox could not be unchecked");
		}
		if(driver.findElement(By.cssSelector("input[name = 'lastName']")).isDisplayed()) {
			driver.findElement(By.cssSelector("input[name = 'lastName']")).sendKeys("Regression");
			System.out.println("Last name has been entered as test.tester@outlook.com");
		} else {
			System.out.println("Last name value could not been entered");
		}
		if(driver.findElement(By.cssSelector("#shipping-address1")).isDisplayed()) {
			driver.findElement(By.cssSelector("#shipping-address1")).sendKeys("4011 Brickstone Mews");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[contains(@id, 'shipping-address1-option-0')]")).click();
			System.out.println("Shipping Address has been entered as 4011 Brickstone Mews");
			Thread.sleep(2000);
		} else {
			System.out.println("Shipping Address could not been entered");
		}
		if(driver.findElement(By.cssSelector("#number")).isDisplayed()) {
			driver.findElement(By.cssSelector("#number")).click();
			action.sendKeys("4111 1111 1111 1111").build().perform();
			System.out.println("Credit Card number has been entered successfully");
		} else {
			System.out.println("Credit Card number could not been entered");
		}
		if(driver.findElement(By.cssSelector("#expiry")).isDisplayed()) {
			driver.findElement(By.cssSelector("#expiry")).click();
			Thread.sleep(1000);
			action.sendKeys("1226").build().perform();
			System.out.println("Credit Card expiration date has been entered successfully");
		} else {
			System.out.println("Credit Card expiration date could not been entered");
		}
		if(driver.findElement(By.cssSelector("#verification_value")).isDisplayed()) {
			driver.findElement(By.cssSelector("#verification_value")).click();
			action.sendKeys("123").build().perform();
			System.out.println("Credit Card Security code has been entered successfully");
		} else {
			System.out.println("Credit Card Security code could not been entered");
		}
		if(driver.findElement(By.cssSelector("#name")).isDisplayed()) {
			driver.findElement(By.cssSelector("#name")).click();
			action.sendKeys("Regression Tester").build().perform();
			System.out.println("Name on Credit Card has been entered successfully");
		} else {
			System.out.println("Name on Credit Card could not been entered");
		}
		if(driver.findElement(By.cssSelector("#checkout-pay-button")).isDisplayed() 
				&& driver.findElement(By.cssSelector("#checkout-pay-button")).isEnabled()) {
			System.out.println("Pay now button is enabled");
		} else {
			System.out.println("Pay now button is not enabled");
		}
		
		//Close the driver and exit the test
		try {
		    driver.quit();  // Preferred over close()
		} catch (Exception e) {
		    // Ignore connection reset errors during cleanup
		    System.out.println("Driver cleanup warning ignored: " + e.getMessage());
		} finally {
			System.out.println("Test run is completed");
		}
	}

}
