package qa.tests;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;

import qa.baseclass.BaseClass;
import qa.pageobjects.SamplePageClass;
import qa.utils.ExcelUtilityClass;
import qa.utils.RetryAnalyzer;


@Listeners(ChainTestListener.class)
public class samplePageDemo extends BaseClass{
	
	private SamplePageClass obj;
	
	@BeforeMethod
	 public void init() throws IOException {
		 obj =new SamplePageClass(driver);
		 
	    }	
	 
	@Test(priority = 1,dataProvider = "testDataProvider",dataProviderClass = ExcelUtilityClass.class,retryAnalyzer = RetryAnalyzer.class)
	public void TestCase1(Map<String, String> testData) throws InterruptedException, IOException {
		System.out.println("@Test");
		launchURL();
		obj.chooseFileButton();
		obj.enterInputFields(testData.get("name"),testData.get("email"),testData.get("website"));
		obj.clickCheckBoxes();
		obj.clickSubmitBtn(testData.get("comment"));
		
	}
	
	
	@Test(priority = 0,dataProvider = "testDataProvider",dataProviderClass = ExcelUtilityClass.class,retryAnalyzer = RetryAnalyzer.class)
	public void TestCase2(Map<String, String> testData) throws InterruptedException, IOException {
		launchURL();
		obj.chooseFileButton();
		obj.enterInputFields(testData.get("name"),testData.get("email"),testData.get("website"));
		obj.clickCheckBoxes();
		obj.clickSubmitBtn(testData.get("comment"));
		obj.verifyTextMessage();		
	}
	
	
}
