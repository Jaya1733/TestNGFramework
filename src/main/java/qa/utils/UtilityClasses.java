package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;

import qa.pageobjects.BasePage;

public class UtilityClasses extends BasePage {
	String keyFile = "src//main//resources//properties//config.properties";

	public UtilityClasses(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Method to capture and attach screenshot & parameter is stepdescription
	 */
	public void captureAndEmbedScreenshot(String stepDescription) {
		try {
			// Take screenshot at current test step
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotPath = "screenshots/" + stepDescription.replaceAll("\\s+", "_") + ".png";
			FileUtils.copyFile(screenshot, new File(screenshotPath));
			ChainTestListener.log(stepDescription);
			ChainTestListener.embed(new File(screenshotPath), "image/png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Method to enter date ; n is parameter to add days
	 * format dd MMM yyyy
	 */
	public String enterDate(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR,  n);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		String futureDate = dateFormat.format(calendar.getTime());
		return futureDate;
	}
	
	/*
	 * Method to Get value from property file
	 */
	public String getPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(keyFile);
		prop.load(fis);
		return prop.getProperty(key);
	}

	/*
	 * Method to javascript scroll button ; Webelement is parameter
	 */
	public void javaScriptScrollIntoView(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ele);
	}

	/*
	 * Method to explicit wait ; Webelement is parameter
	 */
	public void explicitWait(By ele, long waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	/*
	 * Method to click button using javascript executor;
	 * Webelement is parameter
	 */
	public void javaScriptclickLink(WebElement ele) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].click();", ele);
	}
	
	 /* Method to accept alert;
	 * 
	 */
	public void acceptAlert() {
		 Alert alert = driver.switchTo().alert();
	        alert.accept();
	}
	
}
