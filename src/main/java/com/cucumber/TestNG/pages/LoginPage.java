package com.cucumber.TestNG.pages;

import java.io.IOException;
import java.util.Base64;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cucumber.TestNG.helper.UtilClass;

public class LoginPage extends UtilClass{
	private static Logger log= Logger.getLogger(LoginPage.class);
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement emailidTextField;
	
	@FindBy(xpath="//input[@id='pass']")
	private WebElement passwordTextField;
	
	@FindBy(xpath="//button[text()='Log In']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void enterLoginID(String email) {
		setText(driver,emailidTextField,email);
		screenShot(driver);
		log.info("Login email entered as :"+email);
	}

	public void clickOnNextBtn() {
		clickElement(driver,loginButton);
		screenShot(driver);
		log.info("Clicked on Login Button");
	}

	public void enterPassword(String password) throws IOException {
		String pass=readProperty(password);
		setText(driver,passwordTextField,pass);
		screenShot(driver);
		log.info("Login password entered successfully");
	}	
}
