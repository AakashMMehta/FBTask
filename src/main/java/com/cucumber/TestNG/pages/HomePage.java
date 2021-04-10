package com.cucumber.TestNG.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.TestNG.helper.UtilClass;

public class HomePage extends UtilClass {
	private static Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//*[local-name()='svg' and contains(@class,'aaxa7vy3')]/*[local-name()='path']")
	private WebElement homeIcon;

	@FindBy(xpath = "//input[@aria-label='Search Facebook']")
	private WebElement searchFacebookTextBox;

	@FindBy(xpath = "//span[contains(text(),'What')]")
	private WebElement writeOnWall;

	@FindBy(xpath = "//div[@contenteditable='true']")
	private WebElement sendTextOnWall;

	@FindBy(xpath = "//span[text()='Post']")
	private WebElement clickOnPost;

	@FindBy(xpath = "//div[@dir='auto']//child::a")
	private WebElement verifyPostAdded;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
	}

	public void verifyHomePage() {
		wait.until(ExpectedConditions.elementToBeClickable(homeIcon));
		assertTrue(isElementPresent(driver, homeIcon), "Problem in loading HomePage");
		screenShot(driver);
		log.info("HomePage verfied successfully");
	}

	public void enterSearchFacebookTextBox(String fanpage) {
		setText(driver, searchFacebookTextBox, fanpage);
		searchFacebookTextBox.sendKeys(Keys.RETURN);
		screenShot(driver);
		log.info("Fanpage name entered as : " + fanpage);
	}

	public void selectFanPage(String fanpage) {
		waitForLoad(driver);
		By fanPageName = By.xpath("//a[contains(@aria-label,'Verified account')]//span[text()='" + fanpage + "']");
		wait.until(ExpectedConditions.elementToBeClickable(fanPageName));
		clickElementByLocator(driver, fanPageName);
		screenShot(driver);
		log.info("Clicked on Verified Fanpage : " + fanpage);
	}

	public void clickOnCreatePost() {
		clickElement(driver, homeIcon);
		clickElement(driver, writeOnWall);
		screenShot(driver);
		log.info("Clicked on Create Post");
	}

	public void sendWallText(String wallText) {
		wait.until(ExpectedConditions.elementToBeClickable(sendTextOnWall));
		clickElement(driver, sendTextOnWall);
		setText(driver, sendTextOnWall, wallText);
		screenShot(driver);
		log.info("Set Text on Wall");
	}

	public void buttonClickPost() {
		clickElement(driver, clickOnPost);
		screenShot(driver);
		log.info("Clicked on Post Button");
	}

	public void verifyPostAdded(String post) throws InterruptedException {
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(verifyPostAdded)));
		assertEquals(verifyPostAdded.getText(),post, "Problem with entered post");
		screenShot(driver);
		log.info("verified Post");
	}
}
