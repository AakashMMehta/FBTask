package com.cucumber.TestNG.pages;

import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.TestNG.helper.UtilClass;

public class FanPage extends UtilClass {
	private static Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//span[@class and contains(text(),'Like')]")
	private WebElement likeButton;

	@FindBy(xpath = "//div[@aria-label='Account']")
	private WebElement accountIcon;

	@FindBy(xpath = "//span[text()='See your profile']")
	private WebElement selectProfile;

	public FanPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
	}

	public void likeFanPage() {
		clickElement(driver, likeButton);
		screenShot(driver);
		log.info("Clicked on Like button on FanPage");
	}

	public void navigateToProfilePage() {
		clickElement(driver, accountIcon);
		clickElement(driver, selectProfile);
		screenShot(driver);
		log.info("Clicked on Profile Icon to naigate on Profile Page");

	}

	public void verifyUnlikeFanPage() {
		assertTrue(isElementPresent(driver, likeButton), "No Error Found");
		screenShot(driver);
		log.info("FanPage unliked success");
	}

}
