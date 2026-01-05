package pageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.utils;

public class page_shipAndPay extends utils {
	
	WebDriver driver;
	
	public page_shipAndPay(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(xpath = "(//div[contains(@aria-labelledby, 'Resource')]//p)[1]")
	WebElement selectedProduct;
	
	@FindBy(xpath = "//div[contains(@aria-labelledby, 'Money')]//abbr/following-sibling::*")
	WebElement productCost;
	
	@FindBy(css = "input[name = 'email']")
	WebElement email;
	
	@FindBy(css = "#marketing_opt_in")
	WebElement marketingChkBox;
	
	@FindBy(css = "input[name = 'lastName']")
	WebElement lastName;
	
	@FindBy(css = "#shipping-address1")
	WebElement shipAddress;
	
	@FindBy(css = "#number")
	WebElement cardNbr;
	
	@FindBy(css = "#expiry")
	WebElement cardExpiry;
	
	@FindBy(css = "#verification_value")
	WebElement cardCVV;
	
	@FindBy(css = "#name")
	WebElement cardName;
	
	@FindBy(css = "#checkout-pay-button")
	WebElement payNowBtn;
	
	@FindBy(xpath = "//h2[contains(@id, 'deliveryAddress')]")
	WebElement deliveryAddressLbl;
	
	By addressOptionsBox = By.xpath("//div[contains(@aria-labelledby, 'shipping')]");
	
	public void isShipAndPayDisplayed() {
		waitForElementTobeVisible(deliveryAddressLbl);
		Assert.assertEquals("Delivery", deliveryAddressLbl.getText());
	}
	
	public void shipAndPay(HashMap<String, String> shipAndPay) {
		isShipAndPayDisplayed();
		validateSelectedItemTypeAndCost(shipAndPay.get("item"), shipAndPay.get("cost"));
		verifyAndEnterPersonalDeails(shipAndPay.get("email"), shipAndPay.get("lastName"), shipAndPay.get("address"));
		enterAndSelectAddress(shipAndPay.get("addressOption"));
		verifyAndEnterCreditCardDetails(shipAndPay.get("cardNbr"), shipAndPay.get("cardExpiry"), shipAndPay.get("cardCVV"), shipAndPay.get("cardName"));
		IspayNowClickable();
	}
	
	
	public void verifyAndEnterPersonalDeails(String emailID, String lName, String address) {
		Assert.assertTrue(email.isDisplayed());
		email.sendKeys(emailID);
		
		Assert.assertTrue(marketingChkBox.isDisplayed());
		marketingChkBox.click();
		
		Assert.assertTrue(lastName.isDisplayed());
		lastName.sendKeys(lName);
		
		Assert.assertTrue(shipAddress.isDisplayed());
		shipAddress.sendKeys(address);
	}
	
	public void enterAndSelectAddress(String addressOption) {
		waitForVisibilityOfElementLocated(addressOptionsBox);
		List<WebElement> addressSuggestions = driver.findElements(By.xpath("//ul/li"));
		WebElement addressItem = addressSuggestions.stream().filter(address->address.getText().contains(addressOption)).findFirst().orElse(null);
		addressItem.click();
		
	}
	
	public void verifyAndEnterCreditCardDetails(String cardNbrVal, String cardExpiryVal, String cardCVVVal, String cardNameVal) {
		Actions action = new Actions(this.driver);
		Assert.assertTrue(cardNbr.isDisplayed());
		cardNbr.click();
		action.sendKeys(cardNbrVal).build().perform();
		
		Assert.assertTrue(cardExpiry.isDisplayed());
		cardExpiry.click();
		for (char character : cardExpiryVal.toCharArray()) {
			action.sendKeys(String.valueOf(character)).build().perform();
		}
		
		Assert.assertTrue(cardCVV.isDisplayed());
		cardCVV.click();
		action.sendKeys(cardCVVVal).build().perform();	
		
		Assert.assertTrue(cardName.isDisplayed());
		cardName.click();
		action.sendKeys(cardNameVal).build().perform();
	}
	
	public void validateSelectedItemTypeAndCost(String item, String Cost) {
		Assert.assertEquals(item, selectedProduct.getText());
		Assert.assertEquals(Cost, productCost.getText());
	}
	
	
	public void IspayNowClickable() {
		Assert.assertTrue(payNowBtn.isDisplayed() && payNowBtn.isEnabled());
	}
	
}
