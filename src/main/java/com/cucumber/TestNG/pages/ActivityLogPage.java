package com.cucumber.TestNG.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.TestNG.helper.UtilClass;

public class ActivityLogPage extends UtilClass {
	private static Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//h1[text()='Activity log']")
	private WebElement verifyActivityLog;
	 
	public ActivityLogPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
	}

	public void verifyActivityLogPage() {
		assertTrue(isElementPresent(driver, verifyActivityLog), "Problem in loading Activity Log Page");	
		assertEquals(verifyActivityLog.getText(), "Activity log");
		screenShot(driver);
		log.info("Activity log verfied successfully");
	}

	public void verifylikedFanPage(String fanpage) {
		By fanname=By.xpath("//strong[text()='"+fanpage+"']/parent::div[contains(text(),'likes')]");
		assertTrue(isElementPresent(driver, fanname), fanpage+" FanPage not liked");
		screenShot(driver);
		log.info("FanPage is liked :"+fanpage);
	}

}
