package com.salesforce.loginpages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.base.BasePage;
import com.salesforce.homepages.HomePage;

public class checkyouremailPage extends BasePage{
protected static Logger logger = LogManager.getLogger(checkyouremailPage.class.getName());	
	
public checkyouremailPage(WebDriver driver) {
		super(driver);
		
	}
@FindBy(xpath="//p[1]")	
WebElement emailmessage;

@FindBy(xpath="//a[text()='Return to Login']")
WebElement returntologinButton;


@FindBy(xpath="//h1[@id='header']")
WebElement Header1;

//a[text()='Return to Login']
public WebElement sendEmailmessage() {
	return emailmessage;
}

public WebElement returnloginpage() {
	return returntologinButton;
}

public void verifycheckemailpageTitle() {
	String actualtitle = driver.getTitle();
	String expectedtitle  = "Check Your Email | Salesforce";
	Assert.assertEquals(actualtitle, expectedtitle);
	if(actualtitle.equalsIgnoreCase(expectedtitle)) {
		System.out.println("user is on check your email page....Test passed");
		logger.info("user is on check your email page....Testscript TC_4A passed");
	}
	else {
		System.out.println("user is NOT on check your email page....Test failed");
		logger.info("user is NOT on check your email page....Testscript TC_4A failed");
		Assert.fail(expectedtitle);
	}
	
}
public void verifycheckmyMessage() {
	String actualMessage = "We’ve sent you an email with a link to"
			+ " finish resetting your password.";
	
	String expectedMessage = emailmessage.getText();
	logger.debug("actual message = "+actualMessage);
	logger.debug("expected message = "+expectedMessage);
	Assert.assertEquals(actualMessage, expectedMessage);
	if(expectedMessage.equalsIgnoreCase(actualMessage)) {
		System.out.println("email sent successfully to reset password");
		logger.info("email sent successfully to reset password");
		logger.info("Test Script TC_4A pass");
	}
	else {
		System.out.println("email was not sent successfully to reset password");
		logger.info("email was Not sent to reset password");
		logger.info("Test Script TC_4A failed");
		Assert.fail(expectedMessage);
	}
	
  }

}
