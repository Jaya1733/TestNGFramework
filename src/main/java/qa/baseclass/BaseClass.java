
package qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.chaintest.plugins.ChainTestListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver;

	String keyFile = "src//main//resources//properties//config.properties";

	@BeforeMethod
	public void setUp() throws IOException {
		System.out.println("beforemethod");

		String runOnSauceLabs = getPropertyValue("runOnSauceLabs");
		if ("true".equalsIgnoreCase(runOnSauceLabs)) {
			// Running tests on Sauce Labs
			String username =getPropertyValue("sauceLabsUserName") ;
			String accessKey = getPropertyValue("sauceLabsAcessKey");
			// Desired capabilities for Sauce Labs
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			capabilities.setVersion("latest");
			capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			URL sauceURL = new URL("https://" + username + ":" + accessKey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			driver = new RemoteWebDriver(sauceURL, capabilities);
		} else {
			if (getPropertyValue("browser").equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				// options.addArguments(getPropertyValue("browserMode")); // Run Chrome in
				// headless mode (no UI)
				driver = new ChromeDriver(options);
			} else if (getPropertyValue("browser").equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments(getPropertyValue("browserMode")); // Run Firefox in headless mode
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(options);
			} else if (getPropertyValue("browser").equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments(getPropertyValue("browserMode")); // Run Edge in headless mode
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(options);
			}
		}

	}

	public static WebDriver getDriver() {
		return driver;
	}

	@AfterTest
	public static void quitBrowser() {
		driver.quit();
	}

	public void launchURL() throws IOException {
		System.out.println(getPropertyValue("url"));
		driver.get(getPropertyValue("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/*
	 * After method: Take screenshot in case of failure and quit browser
	 */
	@AfterMethod
	public void onFailureScreenshot(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			if (driver != null) {
				String screenshotPath = captureScreenshot(result.getName());
				if (screenshotPath != null) {
					ChainTestListener.embed(new File(screenshotPath), "image/png");
				}
				ChainTestListener.log("Test failed:" + result.getName());
				driver.close();
			} else {
				System.out.println("WebDriver session is null, cannot take screenshot.");
			}
		}
	}

	static String sourceFile = "DataSheet.xlsx";

	/*
	 * Method to capture screenshot & parameter is testCaseName
	 */
	private String captureScreenshot(String testName) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotPath = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
			FileUtils.copyFile(screenshot, new File(screenshotPath));
			return screenshotPath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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

}
