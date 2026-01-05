package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.utils;

public class page_login extends utils {
	
	WebDriver driver;
	
	public page_login(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[contains(text(), 'Customer Login')]")
	WebElement custLogin;
	
	@FindBy(css = "#customer_email")
	WebElement userEmail;
	
	@FindBy(css = "#customer_password")
	WebElement userPwd;
	
	@FindBy(xpath = "//input[contains(@value, 'Sign In')]")
	WebElement signIn;
	
	public void isCustLoginDisplayed(WebElement custLogin) {
		waitForElementTobeVisible(custLogin);
		Assert.assertTrue(custLogin.isDisplayed());
	}
	
	public page_accHome login(String email, String password) {
		/*isCustLoginDisplayed(custLogin);
		userEmail.sendKeys(email);
		userPwd.sendKeys(password);
		signIn.click();*/		//Bypassing Login for now (due to captcha issue in the site)
		page_accHome accHomePage = new page_accHome(driver);
		return accHomePage;
	}

}
