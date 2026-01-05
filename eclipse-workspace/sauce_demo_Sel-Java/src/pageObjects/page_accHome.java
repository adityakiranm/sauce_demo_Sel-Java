package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.utils;

public class page_accHome extends utils {
	
	WebDriver driver;
	
	public page_accHome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	page_selectProduct productPage = new page_selectProduct(driver);
	
	
	@FindBy(xpath = "//h1[@class = 'accounts-title']")
	WebElement accountTitle;
	
	@FindBy(xpath = "//a[text() = 'Catalog']")
	WebElement catalog;
	
	String accDetHdr = "Account Details and Order History";
	String products = "Products";

	
	public void isAccHomeHdrDisplayed(WebElement accountTitle) {
		waitForElementTobeVisible(accountTitle);
		Assert.assertEquals(accDetHdr, accountTitle.getText());
	}
	
	public page_selectProduct verifyAndSelectCatalog() throws IOException {
		//isAccHomeHdrDisplayed(accountTitle);			//disabling this assertion as the site is throwing captcha intermittently while login
		Assert.assertTrue(catalog.isDisplayed());
		catalog.click();
		page_selectProduct productPage = new page_selectProduct(driver);
		return productPage;
	}
}
