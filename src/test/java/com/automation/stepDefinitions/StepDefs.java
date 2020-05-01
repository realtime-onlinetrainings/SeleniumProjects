package com.automation.stepDefinitions;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.helperClasses.BaseClass;	
import com.automation.pages.Personal;
import com.automation.pages.TellUsAboutYourself;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class StepDefs {
	
	public static Logger logger = LogManager.getLogger(StepDefs.class);
	
	WebElement element;
	WebDriver driver = BaseClass.driver;
	String url = "https://my.naukri.com/account/register/basicdetails";
	
	@Given("^I am at the naukri Login screen$")
	public void i_am_at_the_naukri_screen(){
		driver.get(url);
		logger.info("Successfully Lanuched url "+url);
	}
	
	@And("^I click on I am a professional$") 
	public void i_click_on_i_am_a_professioanl(){
		element = driver.findElement(TellUsAboutYourself.buttonUserType);
		element.click();
	}
	
	@And("^I enter Name as (.*)$")
	public void i_enter_name_as(String name){
		element = driver.findElement(Personal.txtFirstname);
		element.sendKeys(name);
	}
	@And("^I enter EmailId as (.*)$")
	public void i_enter_emailId_as(String email){
		element = driver.findElement(Personal.txtEmail);
		element.sendKeys(email);
	}	
	 
	@And("^I enter Password as (.*)$")
	public void I_enter_Password_as(String password){
		element = driver.findElement(Personal.txtPassword);
		element.sendKeys(password);
	}
	 
	

}
