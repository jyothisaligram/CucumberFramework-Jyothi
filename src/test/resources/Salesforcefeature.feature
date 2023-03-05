Feature: login functionality
Background:
Given: user opens salesforce application 
  
  Scenario: login with correct username and password field empty
  
  When user is on "loginPage"
  When user enters username 
  When user clears password field
  When  user clicked on the login button
  Then Error message should be displayed with text "Please enter your password" 

  Scenario: login with correct username and correct password
  
  When user is on "loginpage"
  When user enters valid username
  When user enters valid password
  When user clicked on the login button
  When user is on "homepage" 
  Then verify page title as "Home Page ~ Salesforce - Developer Edition"
  
 Scenario: Test the remember username checkbox
  
  When user is on "loginpage"
  When user enters valid username
  When user enters valid password
  When user clicked on remember me checkbox
  When user clicked on the login button
  When user is on "homepage"
  Then verify page title as "Home Page ~ Salesforce - Developer Edition"
   
  When user clicked on usermenu dropdown
  When user clicked on logout button
  Then user is on "loginpage"
  Then username is populated in username field And remember me checkbox is checked
  Then validate the username displayed in username
  
  
  Scenario: Test forgot password
  When user is on "loginpage"
  When user clicked on forgot password link
  When user is on "forgotpasswordpage"
  When user sees salesforce forgot password page
  Then user Provide username in salesforce forgot password page 
                      And click on continue button
  When user is on "checkyouremailpage"
  When user sees salesforce check your email page
  Then Verify email associated with username account And return to salesforceloginpage
  
    
  
  Scenario: Validate login error message
  When user is on "loginpage"
  When user enters wrong username
  When user enters wrong password
  When user clicked on the login button
  Then Error message should be displayed "Your login attempt has failed The username or password may be incorrect...." 
                                       
                                      
  
                                      
                                      
  
  