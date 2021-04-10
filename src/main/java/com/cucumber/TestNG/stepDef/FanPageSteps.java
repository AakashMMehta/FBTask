package com.cucumber.TestNG.stepDef;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cucumber.TestNG.helper.UtilClass;
import com.cucumber.TestNG.helper.WebDriverManager;
import com.cucumber.TestNG.pages.FanPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

public class FanPageSteps extends UtilClass {
	public WebDriver driver;
	public static Logger log = Logger.getLogger(FanPageSteps.class);

	public FanPageSteps() {
		try {
			driver = WebDriverManager.getInstance().getWebDriver();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	@Then("^User likes the fanpage from FanPage$")
	public void user_likes_the_fanpage_from_SearchResults_page() {
		FanPage fp = new FanPage(driver);
		fp.likeFanPage();
	}
	
	@Then("^User navigates to Profile Page$")
	public void user_navigates_to_Profile_Page() {
		FanPage fp = new FanPage(driver);
		fp.navigateToProfilePage();	
	}
	
	@Then("^User should verify if fanpage is successfully unliked$")
	public void user_should_verify_if_fanpage_is_successfully_unliked() throws Throwable {
		FanPage fp = new FanPage(driver);
		fp.verifyUnlikeFanPage();
	}
}
