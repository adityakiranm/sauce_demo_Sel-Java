package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.utils;

public class page_selectProduct extends utils {
	
	WebDriver driver;
	
	public page_selectProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a//img[@class = 'product']/following-sibling::h3")
	List<WebElement> products;
	
	@FindBy(xpath = "//div[@id = 'breadcrumb']/following::h1")
	WebElement productsBreadCrumb;

	public void verifyProductListisDisplayed() {
		waitForElementTobeVisible(productsBreadCrumb);
		Assert.assertEquals("Products", productsBreadCrumb.getText());
		waitForElementsTobeVisible(products);
		System.out.println("Sauce-Demo Products page: Products page is displayed successfully");
	}
	
	public page_productDet selectProduct(String item) throws IOException {
		verifyProductListisDisplayed();
		captureScreenshot();
		if(products.size()>0) {
			WebElement productName = products.stream().filter(product->product.getText().equalsIgnoreCase(item)).findFirst().orElse(null);
			productName.click();
			System.out.println("Sauce-Demo Products page: Product has been selected in the Products page successfully");
		} else {
			System.out.println("Sauce-Demo Products page: Products not found on the page");
		}
		page_productDet productDetPage = new page_productDet(driver);
		return productDetPage;
	}

}
