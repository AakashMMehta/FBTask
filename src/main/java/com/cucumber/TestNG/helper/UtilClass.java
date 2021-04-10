package com.cucumber.TestNG.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.listener.Reporter;

public class UtilClass {

	private static Logger Log = Logger.getLogger(UtilClass.class);
	private WebDriverWait wait;

	public void clickElement(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void clickElementByLocator(WebDriver driver, By bylocator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		wait.until(ExpectedConditions.elementToBeClickable(bylocator));
		driver.findElement(bylocator).click();
	}

	public void setText(WebDriver driver, WebElement element, String text) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}

	public String getText(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		Log.info("Element text : " + element.getText());
		return element.getText();
	}

	public String getText(WebDriver driver, By locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String value = driver.findElement(locator).getText();
		Log.info("Element text : " + value);
		return value;
	}

	public void sleepinSeconds(int sec) {
		try {
			Log.info("Sleeping for " + sec + " seconds");
			Thread.sleep(sec * 1000);
		} catch (Exception e) {
			Log.info("Problem in sleep");
		}
	}

	public void waitForSpinner(WebDriver driver, By locator, int timeout) {
		try {
			wait = new WebDriverWait(driver, timeout);
			Log.info("Waiting for Loading Icon - Max Wait time is " + timeout + " Seconds");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			Log.info("Loading Icon completed");
		} catch (Exception e) {
			Log.info("Problem in Loading Icon - Waited for " + timeout + " Seconds");
		}
	}

	protected boolean isAtleastOneElementDisplayed(WebDriver driver, By by) {
		List<WebElement> elements = driver.findElements(by);
		for (WebElement element : elements) {
			if (element.isDisplayed())
				return true;
		}
		return false;
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/main/resources/configFiles"
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	protected boolean isElementPresent(WebDriver driver, WebElement element) {
		boolean value = false;
		try {
			element.isDisplayed();
			value = true;
		} catch (Exception e) {
			Log.info("Element is not present " + element);
		}
		return value;
	}

	protected boolean isElementPresent(WebDriver driver, By locator) {
		boolean value = false;
		try {
			driver.findElement(locator).isDisplayed();
			value = true;
		} catch (Exception e) {
			Log.info("Element is not present " + locator);
		}
		return value;
	}

	protected boolean isElementEnabled(WebDriver driver, WebElement element) {
		boolean value = false;
		try {
			value = element.isEnabled();
		} catch (Exception e) {
			Log.info("Element is not Enabled " + element);
		}
		return value;
	}

	protected boolean isElementEnabled(WebDriver driver, By locator) {
		boolean value = false;
		try {
			value = driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			Log.info("Element is not Enabled" + e.getMessage());
		}
		return value;
	}

	public void mouseOverElementAndClick(WebDriver driver, WebElement element, By locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		a.moveToElement(driver.findElement(locator)).click().build().perform();
	}

	public void waitForLoad(WebDriver driver) {
		By spinner = By.xpath(
				"//div[@class='iqfcb0g7 tojvnm2t a6sixzi8 k5wvi7nf q3lfd5jv pk4s997a bipmatt0 cebpdrjk qowsmv63 owwhemhu dp1hu0rb dhp61c6y l9j0dhe7 iyyx5f41 a8s20v7p']");
		waitForSpin(driver, spinner, 1);
	}

	public boolean waitForSpin(WebDriver driver, By waitspinnerloc, int maxwaitminutes) {
		boolean iswaitingComplete = false;
		int sleepTimeSeconds = 3;
		int maxNumberofTrials = maxwaitminutes * 60 / sleepTimeSeconds;
		Log.info("Waiting for loading bar/spinner ...");
		Log.debug("Sleeping... maxTimeout=" + maxwaitminutes + " minutes.");
		try {
			for (int i = 0; !iswaitingComplete && i < maxNumberofTrials; i++) {
				try {
					Thread.sleep(sleepTimeSeconds);
				} catch (Exception e) {
				}

				try {
					iswaitingComplete = !this.isAtleastOneElementDisplayed(driver, waitspinnerloc);
				} catch (StaleElementReferenceException e) {
					iswaitingComplete = !this.isAtleastOneElementDisplayed(driver, waitspinnerloc);
				} catch (Exception e) {
					Log.info("Failed to find atleast one element");
				}
			}
		} catch (StaleElementReferenceException e) {
			iswaitingComplete = true;
		}
		if (!iswaitingComplete) {
			Log.warn("Waited for " + maxwaitminutes + " minutes. But still the operation/load is in progress.");
			return false;
		}
		Log.info("Loading Spinner is closed.");
		return true;
	}

	public void screenShot(WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
			String workingDir = System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshots\\";
			// String path=workingDir+"/target/surefire-reports/Screenshots/"+timeStamp+new
			// Random().nextInt(10000)+".png";
			String path = timeStamp + new Random().nextInt(10000) + ".png";
			FileUtils.copyFile(scrFile, new File(workingDir + path));
			// String relativepath="./surefire-reports/Screenshots/" + path;
			try {
				Reporter.addScreenCaptureFromPath(workingDir + path);
			} catch (Exception e) {
				Log.info("Problem in adding screenshot to the Reports- " + e.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.info("Problem in taking screenshot - " + e.getMessage());
		}
	}

	public String readProperty(String key) throws IOException {
		Properties prop = new Properties();// creating object of Properties
		String result = null;
		try (InputStream ip = new FileInputStream("src/main/resources/configFiles/config.properties")) {
			prop.load(ip); // Loading property from file
			result = prop.getProperty(key);
		} catch (IOException io) {
			Log.info(io.getMessage());
		}
		return result;
	}
}