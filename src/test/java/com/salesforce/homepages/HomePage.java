package com.salesforce.homepages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BasePage;
import com.salesforce.utility.ExtentReportUtility;
import com.steps.stepsPOM;

import junit.framework.Assert;

public class HomePage extends BasePage{

protected static Logger logger = LogManager.getLogger(HomePage.class.getName());	
protected static ExtentReportUtility extentreport;	

public HomePage(WebDriver driver) {
		super(driver);
	}


@FindBy(xpath="//a[text() = 'Logout']")
	 WebElement logoutButton;
	 
 
@FindBy(xpath="//*[@id='userNavButton']")
 WebElement UserMenu;		

	
public WebElement Logout() {
return logoutButton;
}
public WebElement userMenuTab() {
	return UserMenu;
}
public void verifyhomepage() throws InterruptedException {	
	//selectDropdown(UserMenu, "UserMenu");
	
	String actualTitle = driver.getTitle();
    driver.manage().window().maximize();
     Thread.sleep(5000);
     String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
    // Assert.assertEquals(expectedTitle, actualTitle);
     if (actualTitle.equalsIgnoreCase(expectedTitle)) {
	     System.out.println("User is on home page");
	     logger.info("User is on home page");
	     logger.debug("Test Script for homepage launch TC_2 passsed");
      }else {
	System.out.println("home page is not Lunched");
	logger.info("home page is not Lunched");
	logger.debug("Test Script for homepage launch TC_2 Failed");
    }
}

	
}
