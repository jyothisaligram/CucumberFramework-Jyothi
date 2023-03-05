package com.salesforce.utility;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {

	public static ExtentReports report;
	public static ExtentSparkReporter spartReporter; 
	public static ExtentTest testLogger;
	private static ExtentReportUtility extentObject;
	
	//Create a singleton class
	private ExtentReportUtility() {
		
	}
	public static ExtentReportUtility getInstance() {
		if(extentObject==null) {
			System.out.println("creating extent utility object");
			extentObject=new ExtentReportUtility();
		}
		return extentObject;
	}
	
	public void startExtentReport() {
		spartReporter=new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
		report=new ExtentReports();
		report.attachReporter(spartReporter);
		
		report.setSystemInfo("Host Name", "Salesforce");
		report.setSystemInfo("Environment", "Automation Testing");
		report.setSystemInfo("User Name", "Jyothi");

		spartReporter.config().setDocumentTitle("Test Execution Report");
		spartReporter.config().setReportName("Salesforce regression tests");
		spartReporter.config().setTheme(Theme.DARK);
	}
	
	public void startSingleTestReport(String testScript_Name) {
		testLogger=report.createTest(testScript_Name);
	}
	
	public void logTestInfo(String text) {
		testLogger.info(text);
	}
	public void logTestpassed(String testcaseName) {
		testLogger.pass(MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
	}
	
	public void logTestFailed(String testcaseName) {
		testLogger.fail(MarkupHelper.createLabel(testcaseName + "is failed", ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Exception e) {
		testLogger.log(Status.FAIL,e);
		}
	
	public void logTestScreenshot(String path) {
		
		testLogger.addScreenCaptureFromPath(path);
		}
		
	public void endReport() {
		report.flush();
	}
	

	
	
}
