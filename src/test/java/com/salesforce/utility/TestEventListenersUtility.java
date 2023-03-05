package com.salesforce.utility;

import org.openqa.selenium.WebDriver;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.BaseTest;


public class TestEventListenersUtility extends BaseTest implements ITestListener{
protected static ExtentReportUtility extentreport= null;
protected WebDriver driver;


public void onTestStart(ITestResult result) {
		System.out.println("starting test case...");
		extentreport.startSingleTestReport(result.getMethod().getMethodName());
		extentreport.logTestInfo("starting the test case..."+result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
	extentreport.logTestpassed(result.getMethod().getMethodName());
	extentreport.logTestInfo(result.getMethod().getMethodName()+" test passed successfully");
	}

	public void onTestFailure(ITestResult result) {
		extentreport.logTestFailed(result.getMethod().getMethodName());
		extentreport.logTestInfo(result.getMethod().getMethodName()+" test failed ");
		String path = BaseTest.takescreenshotforListener(driver);
		extentreport.logTestScreenshot(path);
			
	}

	
	public void onStart(ITestContext context) {
		extentreport = ExtentReportUtility.getInstance();
		extentreport.startExtentReport();
		System.out.println("extent report started");
	}

	public void onFinish(ITestContext context) {
		extentreport.logTestInfo(" End of Test");
		extentreport.endReport();
	}

		
}
