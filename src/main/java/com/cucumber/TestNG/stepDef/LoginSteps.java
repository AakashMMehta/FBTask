package com.cucumber.TestNG.stepDef;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cucumber.TestNG.helper.BasePropertyReader;
import com.cucumber.TestNG.helper.UtilClass;
import com.cucumber.TestNG.helper.WebDriverManager;
import com.cucumber.TestNG.pages.LoginPage;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginSteps extends UtilClass{
	public WebDriver driver;
	public static Logger log =Logger.getLogger(LoginSteps.class);
	
	@Before
	public void runBeforeScenario(Scenario s) {
		configureLogging();
		String featureName=s.getId().split(";")[0];
		log.info("********Started Execution : "+s.getName()+"********");
		log.info("Feature Name :"+featureName);
		try {
			driver=WebDriverManager.getInstance().createDriver();
		}catch(Exception e) {
			log.info(e.toString());
		}
	}
	@After
	public void runAfterScenario(Scenario s) {
		String status="PASSED";
		if (s.isFailed()) {
			screenShot(driver);
			status="FAILED";
		}
		WebDriverManager.getInstance().closeDriver();
		log.info("*********Completed Executing Scenario :"+ s.getName()+ " ---> " +status + "**********\n\n");
	}
	@Given("^User navigate to the Login page$")
	public void user_navigate_to_the_login_page() {
	    driver.get(BasePropertyReader.getApplicationUrl());
	    screenShot(driver);
	    log.info("Opening the url");
	}
	@When("^User enters id as \"([^\"]*)\" on LoginPage$")
	public void user_enters_loginID_on_LoginPage(String email) {
	    LoginPage lp=new LoginPage(driver);
	    lp.enterLoginID(email);
	}

	@When("^User clicks on Login button on LoginPage$")
	public void user_clicks_on_Next_button_on_LoginPage(){
		LoginPage lp=new LoginPage(driver);
	    lp.clickOnNextBtn();
	}

	@Then("^User enters password as \"([^\"]*)\" on LoginPage$")
	public void user_enters_password_on_LoginPage(String password) throws IOException{
		LoginPage lp=new LoginPage(driver);
	    lp.enterPassword(password);
	}

}
