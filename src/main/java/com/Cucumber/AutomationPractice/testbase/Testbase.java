package com.Cucumber.AutomationPractice.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.Cucumber.AutomationPractice.utils.TestUtil;


import org.assertj.core.api.SoftAssertions;

public class Testbase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
//	public static WebDriverEventListener eventListener;
	private static EventFiringWebDriver Event_Driver;
	private static WebDriverEventListener eventlistner;
	TestUtil testutil;
	private static final Logger log = Logger.getLogger(Testbase.class.getName());
	// private will be accessed only in the same class

	public Testbase() {

		// Registering log4j
		String log4jconfigPath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\Cucumber\\AutomationPractice\\config\\log4j.properties";
		PropertyConfigurator.configure(log4jconfigPath);
		log.info("log4j is registered");

		try {
			prop = new Properties();

			FileInputStream ip = new FileInputStream(
					"src\\main\\java\\com\\Cucumber\\AutomationPractice\\config\\config.properties");

			log.info("loading config files");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() throws InterruptedException {

		String browserName = "chrome";//prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"src\\main\\java\\com\\Cucumber\\AutomationPractice\\config\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					"src\\main\\java\\com\\Cucumber\\AutomationPractice\\config\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		log.info("getting browser value from config.properties");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		log.info("browser opened");
		
		//evenlistener
		Event_Driver=new EventFiringWebDriver(driver);
		//create an object of eventlistnerhandler  to register it with eventfiringwevdriver
		eventlistner=new com.Cucumber.AutomationPractice.utils.WebEventListener();
		Event_Driver.register(eventlistner);
		driver=Event_Driver;	
				
		driver.get("https://cartaxcheck.co.uk/");
		Thread.sleep(2000);

	}

	public static void scrollintoview(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	/*
	 * @After public void teardown() { driver.quit(); }
	 * 
	 * public static void main(String args[]) throws InterruptedException { Testbase
	 * t = new Testbase(); t.initialization(); t.tear();
	 * 
	 * }
	 */

}
