package com.salesforce.loginpages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.base.BasePage;
import com.steps.stepsPOM;

public class forgotpasswordPage extends BasePage{

	protected static Logger logger = LogManager.getLogger(forgotpasswordPage.class.getName());	
	public forgotpasswordPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = ".//*[@id='un']")
	WebElement Fname;
	@FindBy(xpath = "//*[@id='continue']")
	WebElement Conti;
	
	
	public WebElement fullName() {
		return Fname;
	}
	public WebElement Continue() {
		return Conti;
	}
	

	public  void verifyforgotpasswordPage() throws InterruptedException {
	String actualtitle = "Forgot Your Password | Salesforce";
	String expectedtitle  = driver.getTitle();
	Thread.sleep(5000);
	Assert.assertEquals(actualtitle, expectedtitle);
	if (actualtitle.equalsIgnoreCase(expectedtitle)) {
	System.out.println(" user is on forgot password page");
	logger.debug(" user is on forgot password page");
	}
	else {
		System.out.println(" forgot password page is not launched");
		logger.debug(" forgot password page is not launched");
		Assert.fail(actualtitle);
	}
  }
}
	
	
	

