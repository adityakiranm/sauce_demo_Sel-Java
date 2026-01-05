package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.utils;

public class page_myCartCheckOut extends utils {
	
	WebDriver driver;
	
	public page_myCartCheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(xpath = "//h1[text() = 'My Cart']")
	WebElement cartHdr;
	
	@FindBy(css = "#checkout")
	WebElement checkOutBtn;
	
	public void verifyCartPageIsDisplayed() {
		waitForElementTobeVisible(cartHdr);
		Assert.assertEquals("My Cart", cartHdr.getText());
		Assert.assertTrue(checkOutBtn.isDisplayed());
	}
	
	public page_shipAndPay checkoutMyCart() throws IOException {
		verifyCartPageIsDisplayed();
		captureScreenshot();
		checkOutBtn.click();
		System.out.println("Check Out button has been clicked successfully");
		page_shipAndPay shipPayPage = new page_shipAndPay(driver);
		return shipPayPage;
	}
}
