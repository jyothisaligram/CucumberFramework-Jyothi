package com.salesforce.loginpages;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.base.BasePage;
import com.salesforce.utility.CommonUtilities;
import com.salesforce.utility.ExtentReportUtility;
import com.steps.stepsPOM;

public class LoginPage extends BasePage{
	String url,browser;
CommonUtilities common = new CommonUtilities();
 protected static Logger logger = LogManager.getLogger(LoginPage.class.getName());		
 protected static ExtentReportUtility extentreport;
 
 
 public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//input[@id='username']")
    WebElement Uname;
	@FindBy(xpath = "//input[@id='password']")
	WebElement pwd;
	@FindBy(xpath = "//div[@id='error']")
	WebElement errormessage;
	@FindBy(xpath = "//*[@id='Login']")
	WebElement login;
	@FindBy(xpath = "//*[@id='rememberUn']")
	WebElement remember;
	@FindBy(xpath = "//*[@id='forgot_password_link']")
	WebElement Fpwd;
	@FindBy(xpath = ".//*[@id='un']")
	WebElement Fname;
	@FindBy(xpath = "//*[@id='continue']")
	WebElement Conti;
	@FindBy(xpath="//div[@id='error']")
	WebElement actualText;
	
	@FindBy(xpath="//span[@id='idcard-identity']")
	WebElement idLoc;
	@FindBy(xpath="//h1[@id='header']")
	WebElement header;
	
	public WebElement Headerelement() {
		return header;
	}
	
	public WebElement idLocElement() {
		return idLoc;
	}

	public WebElement errormessage() {
		return actualText;
	}
	public WebElement Username() {
		return Uname;
	}

	public WebElement enterIntoPassword() {
		return pwd;
	}

	public WebElement checkRememberme() {
		return remember;
	}

	public WebElement LoginButton() {
		return login;
	}
	public WebElement forgotPassword() {
		return Fpwd;
	}
	public WebElement fullName() {
		return Fname;
	}
	public WebElement Continue() {
		return Conti;
	}

	
	public void errorLogin() throws InterruptedException, IOException {
		url = common.getproperty("url");
		//Thread.sleep(5000);
		driver.get(url);
		logger.debug("Successfully launched.... "+url);
		String title = driver.getTitle();
		System.out.println("The page title is: " + title);
		Thread.sleep(4000);
		Assert.assertEquals(title, "Login | Salesforce");
		logger.info("page title is  "+title );
	}
	
	public void validateWrongPassword() {
		String expected = errormessage().getText();
		String actual = "Please enter your password.";
		Assert.assertEquals(actual, expected);
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Error message is been displayed");
			logger.debug("Test Script for TC_1Salesforce passed");
			logger.info("Error message is displayed");
			
		} else {
			System.out.println("Error message is not been be displayed");
			logger.info("Error message is not displayed");
			logger.debug("Test Script for TC_1Salesforce Failed");
			Assert.fail(actual);
		}	
		
	}
	
	
	public void validateErrormsg() throws IOException, InterruptedException {
		String actualErrorText = common.getproperty("errormessage");
		String expectederror = errormessage().getText(); 
		Thread.sleep(5000);
		System.out.println(actualErrorText);
		logger.debug("Actual error text = "+actualErrorText);
		logger.debug("expected error text = "+expectederror);
		System.out.println(errormessage().getText());
		Assert.assertEquals(actualErrorText, expectederror);
		if(actualErrorText.equalsIgnoreCase(expectederror)) {
			System.out.println("Test Script for validate error message passed");
			logger.debug("Test Script TC_4B for validate error message passed");
			}
		else {
			System.out.println("Test Script for validate error message Failed");
			logger.debug("Test Script TC-4B for validate error message Failed");
			Assert.fail(expectederror);
		}
		
	}
	
	public void validateUsernameinRememberme() throws IOException {

		String actual = idLocElement().getText();
		String expected = common.getproperty("login.valid.userid");
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Username is displayed");
			System.out.println("The script test for rememberme passed");
			Assert.assertEquals(actual, expected);
			logger.debug("Username is displayed..The script test for rememberme TC_3 passed");
		} else {
			System.out.println("Username is not displayed");
			System.out.println("The script test for rememberme failed");
			logger.debug("Username is displayed..The script test for rememberme  TC_3 failed");
			Assert.fail(expected);
		}
		
			
		
	}
	
	
	
	
	
}
