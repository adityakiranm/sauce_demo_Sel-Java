package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.utils;

public class page_landing extends utils {
	
	WebDriver driver;
	
	public page_landing(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement landingPageLogo = driver.findElement(By.xpath("//a/img[contains(@src, 'sauce-demo.myshopify.com/cdn/shop/t/2/assets/logo')]"));
	
	@FindBy(xpath = "//a/img[contains(@src, 'sauce-demo.myshopify.com/cdn/shop/t/2/assets/logo')]")
	WebElement landingPageLogo;
	
	@FindBy(linkText = "Log In")
	WebElement landingPageLogIn;
	
	public void gotoShopLandingPage() {
		String testURL = "https://sauce-demo.myshopify.com/";
		driver.get(testURL);
		Assert.assertTrue(landingPageLogo.isDisplayed());
	}
	public page_login launchURLAndSelectLogIn() throws IOException {
		gotoShopLandingPage();
		/*waitForElementTobeVisible(landingPageLogIn);
		landingPageLogIn.click();
		System.out.println("Sauce-Demo Home: Log In link has been clicked successfully");*/ //Bypassing Login for now (due to captcha issue in the site)
		page_login loginPage = new page_login(driver);
		captureScreenshot();
		return loginPage;
	}

}
