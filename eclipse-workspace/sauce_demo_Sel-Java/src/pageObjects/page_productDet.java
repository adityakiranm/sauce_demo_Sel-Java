package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.utils;

public class page_productDet extends utils {
	
	WebDriver driver;
	
	public page_productDet(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//h1[@class = 'accounts-title']")
	WebElement accountTitle;
	
	@FindBy(xpath = "//a[text() = 'Catalog']")
	WebElement catalog;
	
	@FindBy(css = "#product-select-option-0")
	WebElement productSize;
	
	@FindBy(css = "#product-select-option-1")
	WebElement productColor;
	
	@FindBy(css = "#add")
	WebElement addToCart;
	
	@FindBy(xpath = "//*[contains(text(), 'Check Out')]")
	WebElement checkOutBtn;
	
	By cartCount = By.xpath("//*[contains(@id, 'cart-target-desktop')]/span[contains(text(), '1')]");
	
	public void verifyProductDetailsAreDisplayed() {
		waitForElementTobeVisible(productSize);
		Assert.assertEquals("Add to Cart", (addToCart.getAttribute("value")));
	}
	
	public page_myCartCheckOut addProductToCartAndCheckout() {
		verifyProductDetailsAreDisplayed();
		Select selectSize = new Select(productSize);
		selectSize.selectByValue("M");
		
		Select selectColor = new Select(productColor);
		selectColor.selectByValue("Blue");
		
		Assert.assertTrue(addToCart.isDisplayed());
		addToCart.click();
		waitForPresenceOfElementLocated(cartCount);
		
		Assert.assertTrue(checkOutBtn.isDisplayed());
		checkOutBtn.click();
		System.out.println("My Cart has been selected successfully");
		page_myCartCheckOut cartpage = new page_myCartCheckOut(driver);
		return cartpage;
	}
	

}
