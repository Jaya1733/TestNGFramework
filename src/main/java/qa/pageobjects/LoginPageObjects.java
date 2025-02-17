package qa.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

import qa.baseclass.BaseClass;

public class LoginPageObjects extends BasePage{
	
	
	public LoginPageObjects(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath="//button[@class='primary-teal-btn primary-teal-btn--append']/span[text()='Get a Quote']")
	WebElement getQuoteBtn;
	
	@FindBy(xpath="//label[text()='Business Name'] /../parent::div/child::input")
	WebElement businessNameInput;
	
	@FindBy(xpath="//label[text()='Title'] /../parent::div/child::input")
	WebElement titleInput;
	
	@FindBy(xpath="//label[text()='First Name'] /../parent::div/child::input")
	WebElement firstNameInput;
	
	@FindBy(xpath="//label[text()='Last Name'] /../parent::div/child::input")
	WebElement lastNameInput;
	
	@FindBy(xpath="//label[text()='Email Address'] /../parent::div/child::input")
	WebElement emailAddressInput;
	
	@FindBy(xpath="//label[text()='Confirm Email Address'] /../parent::div/child::input")
	WebElement confirmEmailAddressInput;
	
	@FindBy(xpath="//label[text()='Phone Number'] /../parent::div/child::input")
	WebElement phoneNumInput;
	
	@FindBy(xpath="(//button/span[text()='Next'])[1]")
	WebElement nextBtnInQuoteDetailsBtn;
	
	@FindBy(xpath="//input[contains(@class, 'vlocity-input') and contains(@class, 'nds-input')]")
	WebElement enterPC;
	
	@FindBy(xpath = "//button[contains(@class, 'primary-teal-btn') and normalize-space()='Search']")
 	WebElement btnSearch;
	
	@FindBy(xpath = "//h4[text()='Select your registered business address']")
 	WebElement SelectAddressText;
    
    @FindAll({@FindBy(xpath="//table[.//th[contains(text(),'Business Name')]]//td[1]")})
    List<WebElement> businessNames;
    
    @FindAll({@FindBy(xpath="//button[@title='Next page' and @disabled and .//span[text()='Next']]")})
    List<WebElement> nextBtnPgdisabled;
    
    @FindBy(xpath="//button[.//span[contains(@class, 'paginator-button--label') and text()='Next']]")
   	WebElement nextBtnPg;
    
    @FindBy(xpath="//button[@class='primary-teal-btn primary-teal-btn--append' and .//span[text()='Next']]")
   	WebElement nextBtn;
	
	//*********************************************** Actions ******************************************************//
	
	public void clickGetaQuoteBtn() throws InterruptedException {
		Thread.sleep(7000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
       // js.executeScript("arguments[0].checked= true;", getQuoteBtn);
	//	wait.until(ExpectedConditions.visibilityOf(getQuoteBtn));
		//WebDriverWait wait = new WebDriverWait(driver,30);
		//
		getQuoteBtn.click();
    }
	
	
	public void enterSupplyDetailsSection() throws InterruptedException {
		businessNameInput.sendKeys("tomcast broadcom");
		Thread.sleep(2000);
		titleInput.click();
		titleInput.sendKeys("Mr");
		Thread.sleep(1000);
		titleInput.sendKeys(Keys.DOWN);
		titleInput.sendKeys(Keys.ENTER);
		titleInput.sendKeys(Keys.TAB);
		firstNameInput.sendKeys("ccddgg");
		lastNameInput.sendKeys("eeffjj");
		emailAddressInput.sendKeys("libdan69uhi@in.com");
		confirmEmailAddressInput.sendKeys("libdan69uhi@in.com");
		phoneNumInput.sendKeys("0192 234 4561");
		phoneNumInput.sendKeys(Keys.TAB);
    }
	
	public void clickNextBtnInDetailsPage() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].click();", nextBtnInQuoteDetailsBtn);
		//nextBtnInQuoteDetailsBtn.click();
    }
	
	
	public void enterPostCodeSearch(String postCode) throws InterruptedException {
		Thread.sleep(5000);
 		enterPC.sendKeys(postCode);
 		enterPC.sendKeys(Keys.TAB);
		// JavascriptExecutor js = (JavascriptExecutor) BaseClass.getDriver();
       //  js.executeScript("arguments[0].checked= true;", btnSearch);
         btnSearch.click();
         Thread.sleep(2000);
 	}
	
	
	public void selectBusiness(String selectBusinessName) throws InterruptedException {
		Thread.sleep(2000);
		String pageNum= driver.findElement(By.xpath("//span[@class='item regular-bold'][2]")).getText();
		int i=Integer.parseInt(pageNum);//count of pages
		System.out.println(i);
		 outerloop:
		for(int j=1;j<=i;i++) {/// Loop is for pages
			for(int k=0;k<businessNames.size();k++) {// Loop is for businessname in each page
				String value=businessNames.get(k).getText().replaceAll("*", "");
		          System.out.println(businessNames.get(k).getText());
           if (value.equalsIgnoreCase(selectBusinessName)) {
        	   System.out.println("firstPage");
               break outerloop;
            }
		}
			nextBtnPg.click();
		}
		
     	  WebElement selectBtn= driver.findElement(By.xpath("//table[.//th[contains(text(),'Business Name')]]//td[1][text()='"+selectBusinessName+"']/./following::td[2]//button"));
     	 // wait.until(ExpectedConditions.elementToBeClickable(selectBtn));
     	  System.out.println(selectBtn);
     	 selectBtn.click();
     	//   selectBtn.click();
     	  Thread.sleep(2000);
     	  
     	 nextBtn.click();
         Thread.sleep(2000);
      }
	         	     
        
   }
