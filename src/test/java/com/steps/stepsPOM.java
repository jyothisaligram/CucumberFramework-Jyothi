package com.steps;

import java.io.IOException;


import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.base.BaseTest;
import com.salesforce.homepages.HomePage;
import com.salesforce.loginpages.LoginPage;
import com.salesforce.loginpages.checkyouremailPage;
import com.salesforce.loginpages.forgotpasswordPage;
import com.salesforce.utility.CommonUtilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.salesforce.utility.Constants;
import com.salesforce.utility.ExtentReportUtility;



public class stepsPOM extends BaseTest{
	
	WebDriver driver;
	LoginPage login;
	HomePage home;
	forgotpasswordPage forgotpwd;
	checkyouremailPage checkemail;
	String url,browser;
	CommonUtilities common = new CommonUtilities();
	protected static Logger logger = LogManager.getLogger(stepsPOM.class.getName());	
	static ExtentReportUtility extentreport;
	
	@Before(order=0)
	public void setup1() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@Before(order=1)
	public void setup2() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@After
	public void teardown() {
		logger.info("closing webdriver");
	//	takescreenshot(driver);
		driver.close();
	}
	
	
	@When("user is on {string}")
	public void user_is_on(String page) throws IOException, InterruptedException {
	   if(page.equalsIgnoreCase("loginpage")) { 
		   login = new LoginPage(driver);
		   logger.info("started login method ...");
		  // Thread.sleep(6000);
		   login.errorLogin();
		  Thread.sleep(4000); 
	  }
	   else if(page.equalsIgnoreCase("homepage")) {
		   home = new HomePage(driver);
		   logger.info("Inside homepage");
		 }
	   else if (page.equalsIgnoreCase("forgotpasswordpage")) {
		   forgotpwd = new forgotpasswordPage(driver);
		   logger.info("Inside forgot password page");
	   }
	   else if (page.equalsIgnoreCase("checkyouremailpage")) {
		   checkemail = new checkyouremailPage(driver);
		   logger.info("Inside check your email page");
	   }
	   else {
		   System.out.println("Please enter correct page");
		   logger.debug("Incorrect page entered..");
	   }
	   
	}
/**********************************************/
// TC_1
	
	@When("user enters username")
	public void user_enters_username() throws IOException, InterruptedException {
		String Username = common.getproperty("login.valid.userid");
		 WebElement username =login.Username();
		 BaseTest.enterText(username, Username,"usernameEle");
		 logger.info("entered username");
	}
	
	@When("user clears password field")
	public void user_clears_password_field() throws InterruptedException {
		 String passWord = "" ;
		 WebElement password =login.enterIntoPassword() ;
		 BaseTest.enterText(password, passWord,"PasswordEle");
		 logger.info("entered blank  password");
		 Thread.sleep(4000); 
	}

	@When("user clicked on the login button")
	public void user_clicked_on_the_login_button() throws InterruptedException {
		 BaseTest.clickonme(login.LoginButton(), "Login");
		 logger.info("entered login button");
		 Thread.sleep(5000);
	}

	@Then("Error message should be displayed with text {string}")
	public void error_message_should_be_displayed_with_text(String string) {
		login.validateWrongPassword();
	}
/**
 * @throws IOException 
 * @throws InterruptedException *********************************************************/
	//TC_2
	@When("user enters valid username")
	public void user_enters_valid_username() throws IOException, InterruptedException {
		String Username = common.getproperty("login.valid.userid");
		 WebElement username =login.Username();
		 Thread.sleep(4000);
		 BaseTest.enterText(username, Username,"usernameEle");
		 logger.info("user name entered");
	}

	@When("user enters valid password")
	public void user_enters_valid_password() throws IOException, InterruptedException {
		String passWord = common.getproperty("login.valid.password");
		 WebElement password =login.enterIntoPassword();
		 BaseTest.enterText(password, passWord,"passwordEle"); 
		 Thread.sleep(4000);
		 logger.info("password entered");
	}
	

	@Then("verify page title as {string}")
	public void verify_page_title_as(String string) throws InterruptedException {
		home.verifyhomepage();
		logger.info("This is homepage");
	}
	
/**********************************************/
	//TC_3 salesforce
	
	@When("user clicked on remember me checkbox")
	public void user_clicked_on_remember_me_checkbox() {
	    WebElement remember = login.checkRememberme();
		BaseTest.clickonme(remember, "remember ele");
	}

	@When("user is on homepage")
	public void user_is_on_homepage() throws InterruptedException {
		Thread.sleep(5000);
		home.verifyhomepage();
		logger.info("This is homepage");
	}

	@When("user clicked on usermenu dropdown")
	public void user_clicked_on_usermenu_dropdown() throws InterruptedException {
		Thread.sleep(5000);
		BaseTest.selectDropdown(home.userMenuTab(), "userMenu");
		logger.debug("Drop down menu selected ..");
		Thread.sleep(3000);
		
	}

	@When("user clicked on logout button")
	public void user_clicked_on_logout_button() {
		BaseTest.clickonme(home.Logout(), "logout"); 
	}

	@Then("username is populated in username field And remember me checkbox is checked")
	public void username_is_populated_in_username_field_and_remember_me_checkbox_is_checked() {
		String actual = login.idLocElement().getText();
		logger.debug("Username = "+actual);
	}

	@Then("validate the username displayed in username")
	public void validate_the_username_displayed_in_username() throws IOException {
		login.validateUsernameinRememberme(); 
	
	}

/*********************************************/
	
//TC_4A SAlesforce
	
	@When("user clicked on forgot password link")
	public void user_clicked_on_forgot_password_link() {
		BaseTest.clickonme(login.forgotPassword(), "ForgotPassword");
	}

	@When("user sees salesforce forgot password page")
	public void user_sees_salesforce_forgot_password_page() throws InterruptedException {
		forgotpwd.verifyforgotpasswordPage();
		
	}
	
	@Then("user Provide username in salesforce forgot password page")
	public void user_provide_username_in_salesforce_forgot_password_page() throws IOException {
		 WebElement userName = forgotpwd.fullName();
		 String userEmail =common.getproperty("login.valid.userid"); 
		 BaseTest.enterText(userName, userEmail, "user Email");
	}

	@When("click on continue button")
	public void click_on_continue_button() {
	  WebElement click = forgotpwd.Continue();
	  BaseTest.clickonme(click, "Continue Ele");
	}

	@When("user sees salesforce check your email page")
	public void user_sees_salesforce_check_your_email__page() throws InterruptedException {
		checkemail.verifycheckemailpageTitle();
	}
	@Then("Verify email associated with username account And return to salesforceloginpage")
   public void verify_email_associated_with_username_account_and_return_to_salesforceloginpage() throws InterruptedException {	
		checkemail.verifycheckmyMessage();
		Thread.sleep(4000);
		WebElement returnButton = checkemail.returnloginpage();
		BaseTest.clickonme(returnButton,"Return button Ele");
	}
	
		/**
	 * @throws IOException 
	 * @throws InterruptedException *********************************************************/
	
	//TC_4B Validate login error message
	
	@When("user enters wrong username")
	public void user_enters_wrong_username() throws IOException, InterruptedException {
		String username = common.getproperty("login.invalid.userid");
		 WebElement username1 =login.Username();
		 BaseTest.enterText(username1, username,"usernameEle");
		 logger.info("entered invalid username");	
		
	}

	@When("user enters wrong password")
	public void user_enters_wrong_password() throws InterruptedException, IOException {
		String Password = common.getproperty("login.invalid.password");
		WebElement password =login.enterIntoPassword() ;
		 BaseTest.enterText(password, Password,"PasswordEle");
		 logger.info("entered invalid  password");
		 Thread.sleep(4000);  
	}

	@Then("Error message should be displayed {string}")
	public void error_message_should_be_displayed(String string) throws IOException, InterruptedException {
		takescreenshotforListener(driver);	
		login.validateErrormsg();
				
	}


}
