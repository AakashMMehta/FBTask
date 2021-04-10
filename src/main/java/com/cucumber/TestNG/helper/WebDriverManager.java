package com.cucumber.TestNG.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager extends BasePropertyReader {
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	private WebDriver driver;
	private static WebDriverManager browserInst = null;
	String driverType = getBrowser();
	String environmentType = getEnvironmentType();

	public WebDriver getWebDriver() {
		return dr == null ? null : dr.get();
	}

	public static WebDriver getDriver() {
		return dr.get();
	}

	public static void setWebDriver(WebDriver driver) {
		dr.set(driver);
	}

	public static WebDriverManager getInstance() {
		if (browserInst == null) {
			browserInst = new WebDriverManager();
		}
		return browserInst;
	}

	public WebDriver createDriver() {
		switch (environmentType.toLowerCase()) {
		case "local":
			driver = createLocalDriver();
			break;
		case "remote":
			driver = createRemoteDriver();
			break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
		switch (driverType.toLowerCase()) {
		case "firefox":
			FirefoxProfile profile = new FirefoxProfile();
		    profile.setPreference("permissions.default.desktop-notification", 1);
		    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
		    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		    System.setProperty("webdriver.gecko.driver", getOSPath() + "/geckodriver.exe");
		    driver = new FirefoxDriver(capabilities);
			break;
		case "chrome":
			//Create a map to store  preferences 
			Map<String, Object> prefs = new HashMap<String, Object>();
			//add key and value to map as follow to switch off browser notification
			//Pass the argument 1 to allow and 2 to block
			prefs.put("profile.default_content_setting_values.notifications", 2);
			//Create an instance of ChromeOptions 
			ChromeOptions options = new ChromeOptions();
			// set ExperimentalOption - prefs 
			options.setExperimentalOption("prefs", prefs);
			System.setProperty("webdriver.chrome.driver", getOSPath() + "/chromedriver.exe");
			driver = new ChromeDriver(options);
			break;
		case "ie":
			System. setProperty("webdriver. ie. driver", getOSPath() + "/iedriver.exe");
			driver = new InternetExplorerDriver();
			break;
		}
		dr.set(driver);
		dr.get().manage().window().maximize();
		dr.get().manage().deleteAllCookies();
		dr.get().manage().timeouts().implicitlyWait(getImplicitlyWait(), TimeUnit.SECONDS);
		return dr.get();
	}

	public void closeDriver() {
		if (dr != null) {
			try {
				dr.get().close();
				dr.get().quit();
			} catch (WebDriverException e) {
				e.toString();
			}
		}
	}

}