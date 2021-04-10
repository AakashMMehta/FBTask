package com.cucumber.TestNG.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BasePropertyReader {

	private final String propertyFilePath = System.getProperty("user.dir") + "/src/main/resources/configFiles"+ "/Config.properties";
	static Properties properties = new Properties();

	public BasePropertyReader() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Config.properties not found at " + propertyFilePath);
		}

	}

	public String getOSPath() {
		String os= properties.getProperty("os");
		if (os != null)
			switch (os) {
			case "linux":
				return System.getProperty("user.dir") + "/src/main/resources/drivers/linux";
			case "windows":
				return System.getProperty("user.dir") + "/src/main/resources/drivers/windows";
			case "mac":
				return System.getProperty("user.dir") + "/src/main/resources/drivers/mac";
			default:
				throw new RuntimeException("Please specify correct Operating System");
			}
		else
			throw new RuntimeException("Operating System not specified in the Configuration.properties file.");
	}
	public String getBrowser() {
		String browser = properties.getProperty("browser");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("browser not specified in the Configuration.properties file.");
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
	}

	public String getEnvironmentType() {
		String environment = properties.getProperty("environment");
		if (environment != null)
			return environment;
		else
			throw new RuntimeException("environment not specified in the Configuration.properties file.");
	}

	public static String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
}
