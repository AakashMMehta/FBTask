package com.cucumber.TestNG.stepDef;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cucumber.TestNG.helper.UtilClass;
import com.cucumber.TestNG.helper.WebDriverManager;
import com.cucumber.TestNG.pages.ActivityLogPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

public class ActivityLogPageSteps extends UtilClass {
	public WebDriver driver;
	public static Logger log = Logger.getLogger(ActivityLogPageSteps.class);

	public ActivityLogPageSteps() {
		try {
			driver = WebDriverManager.getInstance().getWebDriver();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}
	
	@Then("^User should be able to verify ActivityLog Page$")
	public void verifyActivityLog_Page() throws Throwable {
		ActivityLogPage alp=new ActivityLogPage(driver);
		alp.verifyActivityLogPage();
	}
	
	@Then("^User verify liked \"([^\"]*)\" on ActivityLog Page$")
	public void user_verify_liked_fanpage_on_ActivityLog_Page(String fanpage) {
		ActivityLogPage alp=new ActivityLogPage(driver);
		alp.verifylikedFanPage(fanpage);
	}
	
}
