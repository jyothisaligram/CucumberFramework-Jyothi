package com.runners;
import org.testng.annotations.Listeners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Listeners({com.salesforce.utility.TestEventListenersUtility.class})

	@CucumberOptions(
			features = {"src/test/resources/Salesforcefeature.feature"},
			glue = {"com.steps"}
			)
  	 
	public class SalesforceRunner extends AbstractTestNGCucumberTests{

	}	
	

