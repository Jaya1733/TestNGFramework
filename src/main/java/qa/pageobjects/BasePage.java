package qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import qa.baseclass.BaseClass;

public class BasePage{
	
	
	protected WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initialize the page elements using PageFactory
    }

}
