package testObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.page_accHome;
import pageObjects.page_landing;
import pageObjects.page_login;
import pageObjects.page_myCartCheckOut;
import pageObjects.page_productDet;
import pageObjects.page_selectProduct;
import pageObjects.page_shipAndPay;
import resources.extentReports;
import utilities.utils;

public class driverInit {
	
	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\resources\\GlobalProperties.properties");
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();    // Setup with WebDriverManager dependency
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public page_landing createLandingPageObject(WebDriver driver) {
		page_landing landingPage = new page_landing(driver);
		return landingPage;
	}
	
	public utils createUtilsObject(WebDriver driver) {
		utils util = new utils(driver);
		return util;
	}
	
	@AfterMethod
	public void teardown() {
		try {
	    	driver.quit();  
	    } catch (Exception e) {
	    	System.out.println("Driver cleanup warning ignored: " + e.getMessage());
	    } finally {
		System.out.println("Test run is completed");
	    }
	}

}
