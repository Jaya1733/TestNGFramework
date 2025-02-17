package qa.tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.baseclass.BaseClass;
import qa.pageobjects.LoginPageObjects;
//@Listeners(ExtentSparkReporter)
public class LoginPage extends BaseClass{
	
	 private LoginPageObjects obj;
	
	 @BeforeMethod
	 public void init() throws IOException {
		 obj =new LoginPageObjects(driver);
		 
	    }	
	@Test
	public void enterSupplyDetails() throws InterruptedException, IOException {
		obj.clickGetaQuoteBtn();
		obj.enterSupplyDetailsSection();
		obj.clickNextBtnInDetailsPage();
		//String postcode=data.get("postcode");
		//System.out.println(postcode);
		obj.enterPostCodeSearch("NPHJK");
		obj.selectBusiness("THE THAMES PROJECT MANAGEMENT COMPANY LTD");
		//TIME ASSOCIATES LTD
		
		//    (//div[h4[contains(text(),'SmartPay Standard')] and h4[contains(text(),'1 Year fix')]]//following::div//button[text()='Select'])[1]
		
	}
	
}
