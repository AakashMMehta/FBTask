package com.cucumber.TestNG.stepDef;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cucumber.TestNG.helper.UtilClass;
import com.cucumber.TestNG.helper.WebDriverManager;
import com.cucumber.TestNG.pages.ProfilePage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

public class ProfilePageSteps extends UtilClass {
	public WebDriver driver;
	public static Logger log = Logger.getLogger(ProfilePageSteps.class);

	public ProfilePageSteps() {
		try {
			driver = WebDriverManager.getInstance().getWebDriver();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	@Then("^User clicks on Activity Log on Profile Page$")
	public void user_clicks_on_Activity_Log_on_Profile_Page() {
		ProfilePage pp=new ProfilePage(driver);
		pp.navigateToActivityLog();
	}

	@Then("^User navigates to \"([^\"]*)\" on Profile Page$")
	public void user_navigates_to_Likes_on_Profile_Page(String menu) throws InterruptedException {
		ProfilePage pp=new ProfilePage(driver);
		pp.navigateToMenu(menu);
	}

	@Then("^User verify liked \"([^\"]*)\" under Likes on Profile Page$")
	public void user_verify_liked_fanpage_under_Likes_on_Profile_Page(String fanpage) {
		ProfilePage pp=new ProfilePage(driver);
		pp.verifyLikedFanPage(fanpage);
	}

	@Then("^User unlikes the \"([^\"]*)\" under Likes on Profile Page$")
	public void user_unlikes_the_fanpage_under_Likes_on_Profile_Page(String fanpage) {
		ProfilePage pp=new ProfilePage(driver);
		pp.unlikeFanPage(fanpage);
	}

	@Then("^User clicks on \"([^\"]*)\" under Likes on Profile Page$")
	public void user_clicks_on_fanpage_under_Likes_on_Profile_Page(String fanpage) {
		ProfilePage pp=new ProfilePage(driver);
		pp.clickOnFanPage(fanpage);
	}

	@Then("^User verify if \"([^\"]*)\" was added successfuly on Profile Page$")
	public void user_verify_if_post_was_added_successfuly_on_Profile_Page(String post) {
		ProfilePage pp=new ProfilePage(driver);
		pp.verifyPostOnProfile(post);
	}

	@Then("^User remove post on Profile Page$")
	public void user_remove_post_on_Profile_Page() {
		ProfilePage pp=new ProfilePage(driver);
		pp.removePost();
	}

	@Then("^User should verify \"([^\"]*)\" is not displayed on Profile Page$")
	public void user_should_verify_post_is_not_displayed_on_Profile_Page(String post)  {
		ProfilePage pp=new ProfilePage(driver);
		pp.verifyNoPostOnProfile(post);
	}
}
