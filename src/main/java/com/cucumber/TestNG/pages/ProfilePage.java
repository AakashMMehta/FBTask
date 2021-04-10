package com.cucumber.TestNG.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.TestNG.helper.UtilClass;

public class ProfilePage extends UtilClass {
	private static Logger log = Logger.getLogger(ProfilePage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "(//div[@aria-haspopup='menu']//child::i)[1]")
	private WebElement accountMenu;

	@FindBy(xpath = "//span[text()='Activity log']")
	private WebElement activityLog;

	@FindBy(xpath = "//div[@aria-haspopup='menu']//child::span[text()='More']")
	private WebElement moreMenu;
	
	@FindBy(xpath = "//div[@dir='auto']//child::a")
	private WebElement verifyPostAdded;
	
	@FindBy(xpath = "//span[text()='No posts available']")
	private WebElement verifyPostNotFound;
	
	@FindBy(xpath = "//div[@aria-label='Actions for this post']")
	private WebElement removeMenu;
	
	@FindBy(xpath = "//span[text()='Move to Recycle bin']")
	private WebElement removeBtn;
	
	@FindBy(xpath = "//span[text()='Move']")
	private WebElement moveBtn;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
	}

	public void navigateToActivityLog() {
		wait.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(accountMenu)));
		clickElement(driver, accountMenu);
		wait.until(ExpectedConditions.visibilityOf(activityLog));
		clickElement(driver, activityLog);
		screenShot(driver);
		log.info("Clicked on Activity log");
	}

	public void navigateToMenu(String menu) throws InterruptedException {
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(moreMenu)));
		clickElement(driver, moreMenu);
		By subMenu=By.xpath("//a[@role='menuitemradio']//child::span[text()='"+menu+"']");
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(subMenu)));
		clickElementByLocator(driver, subMenu);
		screenShot(driver);
		log.info("Clicked on menu :" + menu);
	}

	public void verifyLikedFanPage(String fanpage) {
		By fanname = By.xpath("//span[text()='" + fanpage + "']");
		assertTrue(isElementPresent(driver, fanname), fanpage + " FanPage not liked");
		screenShot(driver);
		log.info("FanPage is liked :" + fanpage);
	}

	public void unlikeFanPage(String fanpage) {
		WebElement ele = driver.findElement(By.xpath("//span[text()='" + fanpage + "']//ancestor::a//child::img"));
		By unlike = By.xpath("//span[contains(text(),'Liked')]");
		mouseOverElementAndClick(driver, ele, unlike);
		screenShot(driver);
		log.info("FanPage is unliked :" + fanpage);
	}

	public void clickOnFanPage(String fanpage) {
		By fanname = By.xpath("//span[text()='" + fanpage + "']//ancestor::a//child::img");
		clickElementByLocator(driver, fanname);
		screenShot(driver);
		log.info("Clicked on FanPage :" + fanpage);
	}

	public void verifyPostOnProfile(String post) {
		JavascriptExecutor js = (JavascriptExecutor) driver;	
        js.executeScript("window.scrollBy(0,1000)");
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(verifyPostAdded)));
		assertEquals(verifyPostAdded.getText(),post, "Problem with entered post");
		screenShot(driver);
		log.info("verified Post");	
	}

	public void removePost() {
		clickElement(driver, removeMenu);
		clickElement(driver, removeBtn);
		clickElement(driver, moveBtn);
		screenShot(driver);
		log.info("Clicked on Remove Button");
	}

	public void verifyNoPostOnProfile(String post) {
		driver.navigate().refresh();
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(verifyPostNotFound)));
		Assert.assertNotEquals(verifyPostNotFound.getText(),post, "Problem with entered post");
		screenShot(driver);
		log.info("verified Post");
	}

}
