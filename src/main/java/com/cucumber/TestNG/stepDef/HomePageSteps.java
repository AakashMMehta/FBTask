package com.cucumber.TestNG.stepDef;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cucumber.TestNG.helper.UtilClass;
import com.cucumber.TestNG.helper.WebDriverManager;
import com.cucumber.TestNG.pages.HomePage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageSteps extends UtilClass {
	public WebDriver driver;
	public static Logger log = Logger.getLogger(HomePageSteps.class);

	public HomePageSteps() {
		try {
			driver = WebDriverManager.getInstance().getWebDriver();
		} catch (Exception e) {
			log.info(e.toString());
		}

	}

	@Then("^User should be able to verify Home page$")
	public void user_should_be_able_to_verify_Home_page() {
		HomePage hp = new HomePage(driver);
		hp.verifyHomePage();
	}

	@When("^User enters fanpage name as \"([^\"]*)\" in Search Facebook on HomePage$")
	public void user_enters_fanpage_name_as_in_Search_Facebook_on_HomePage(String fanpage) throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.enterSearchFacebookTextBox(fanpage);
	}

	@Then("^User selects the fanpage \"([^\"]*)\" on HomePage$")
	public void user_selects_the_fanpage_on_HomePage(String fanpage) throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.selectFanPage(fanpage);
	}

	@When("^User clicks on create post on HomePage$")
	public void user_clicks_on_create_post_on_HomePage() {
		HomePage hp = new HomePage(driver);
		hp.clickOnCreatePost();
	}

	@When("^User enters facebook post as \"([^\"]*)\" on HomePage$")
	public void user_enters_facebook_post_as_on_HomePage(String wallText) throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.sendWallText(wallText);
	}

	@When("^User clicks on Post button on HomePage$")
	public void user_clicks_on_Post_button_on_HomePage() throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.buttonClickPost();
	}

	@Then("^User verify if \"([^\"]*)\" was added successfuly on HomePage$")
	public void user_verify_if_post_was_added_successfuly_on_HomePage(String post) throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.verifyPostAdded(post);
	}

}
