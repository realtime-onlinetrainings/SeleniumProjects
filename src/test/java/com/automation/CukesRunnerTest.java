package com.automation;

import cucumber.api.CucumberOptions;

import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions (
		monochrome = true,
		features = "src/test/java/com/automation/featureFiles/NaukriReg.feature", 
//		glue = {"/src/test/java/com/automation/features/StepDefs"},
		plugin = {"pretty", "html:target/cucumber-html-report"}
	//		tags = "@GmailLogin"
		)
public class CukesRunnerTest extends AbstractTestNGCucumberTests {

}
