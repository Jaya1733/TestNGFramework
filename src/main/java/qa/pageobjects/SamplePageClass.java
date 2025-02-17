package qa.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import qa.utils.UtilityClasses;

public class SamplePageClass extends BasePage{
	
	UtilityClasses util=new UtilityClasses(driver);
	
	public SamplePageClass(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@type='file']")
	WebElement chooseFileBtn;
	
	@FindBy(xpath="//input[@class='name']")
	WebElement nameInput;
	
	@FindBy(xpath="//input[@class='email']")
	WebElement emailInput;
	
	@FindBy(xpath="//input[@class='url']")
	WebElement websiteInput;
	
	@FindBy(xpath="//label[contains(text(),'Functional Testing')]")
	WebElement functionalTestingCheckBox;
	
	@FindBy(xpath="//label[contains(text(),'Automation Testing')]")
	WebElement automationTestingCheckBox;
	
	@FindBy(xpath="//label[contains(text(),'Manual Testing')]")
	WebElement manualTestingCheckBox;
	
	@FindBy(xpath="//button[contains(text(),'Alert Box : Click Here')]")
	WebElement alertBox;
	
	@FindBy(xpath="//textarea[contains(@id,'comment')]")
	WebElement commentInput;
	
	@FindBy(xpath="//button[(@type='submit')]")
	WebElement submitBtn;
	
	//*********************************************** Actions ******************************************************//
	
	/*
	 * Enter input fields in firstpage
	 * @param inputname
	 * @param email
	 * @param website
	 */
		public void enterInputFields(String name,String email,String Website) throws InterruptedException, IOException {
			nameInput.sendKeys(name);
			System.out.println(name);
			util.captureAndEmbedScreenshot("Entered name ");
			emailInput.sendKeys(email);
			util.captureAndEmbedScreenshot("Entered email ");
			websiteInput.sendKeys(Website);
	    }
		
	
	/*
	 * Method to Upload a file in chooseFileBTN input field
	 */
		public void chooseFileButton() throws InterruptedException, IOException {
			Thread.sleep(7000);
			chooseFileBtn.sendKeys(util.getPropertyValue("profilePic"));;
			util.captureAndEmbedScreenshot("Entered choosefile ");
	    }
		
		
			
			/*
			 * clickCheckBoxes
			 * @param inputname
			 * @param email
			 * @param website
			 */
				public void clickCheckBoxes() throws InterruptedException, IOException {
					functionalTestingCheckBox.click();
					automationTestingCheckBox.click();
					manualTestingCheckBox.click();
					util.captureAndEmbedScreenshot("Checked boxes");
			    }
	
				/*
				 * HandleALertPopups
				 * @param inputname
				 * @param email
				 * @param website
				 */
					public void handleAlertBox() throws InterruptedException, IOException {
						alertBox.click();
						util.captureAndEmbedScreenshot("clicked AlertBOX");
						util.acceptAlert();
						Thread.sleep(3000);
						util.acceptAlert();
				    }

					
					/*
					 * method to clicksubmitbutton
					 * @param comment input
					 */
						public void clickSubmitBtn(String comments) throws InterruptedException, IOException {
							commentInput.sendKeys(comments);
							submitBtn.click();
					    }

}
