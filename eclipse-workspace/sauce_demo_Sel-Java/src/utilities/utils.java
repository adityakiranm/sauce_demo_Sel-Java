package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils {
	
	WebDriver driver;
	
	public utils(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementTobeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsTobeVisible(List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitForPresenceOfElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForVisibilityOfElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public File fetchDataFilePath() {
		File jsonFile = new File("C:\\Users\\adity\\eclipse-workspace\\sauce_demo_Sel-Java\\src\\utilities\\data.json");
		return jsonFile;
	}
	
	public void captureScreenshot() throws IOException {
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 String fileName = String.valueOf(Math.random());
         File destination = new File(System.getProperty("user.dir") + "//Screenshots" + "//" + fileName + ".png");
         FileUtils.copyFile(source, destination);
	}
	

}
