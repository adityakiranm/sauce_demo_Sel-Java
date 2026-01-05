package testScripts;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pageObjects.page_accHome;
import pageObjects.page_landing;
import pageObjects.page_login;
import pageObjects.page_myCartCheckOut;
import pageObjects.page_selectProduct;
import pageObjects.page_productDet;
import pageObjects.page_shipAndPay;
import testObjects.driverInit;
import utilities.utils;

public class test_placeOrder_E2E extends driverInit {
	
	
	@Test
	public void placeOrder() throws IOException {
		
		driver = initializeDriver();
		page_landing landingPage = createLandingPageObject(driver);
		utils util = createUtilsObject(driver);
		
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> shipAndPayMap = null;
		try {
			shipAndPayMap = mapper.readValue(util.fetchDataFilePath(), new TypeReference<HashMap<String, String>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		page_login loginPage = landingPage.launchURLAndSelectLogIn();
		page_accHome accHomePage = loginPage.login(shipAndPayMap.get("loginEmail"), shipAndPayMap.get("loginPwd"));
		page_selectProduct productPage = accHomePage.verifyAndSelectCatalog();
		page_productDet productDetPage = productPage.selectProduct(shipAndPayMap.get("item"));
		page_myCartCheckOut cartpage = productDetPage.addProductToCartAndCheckout();
		page_shipAndPay shipPayPage = cartpage.checkoutMyCart();
		shipPayPage.shipAndPay(shipAndPayMap);
	}

}
