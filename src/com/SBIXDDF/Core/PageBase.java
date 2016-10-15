package com.SBIXDDF.Core;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.SoftAssert;

import com.SBIXDDF.Util.Constants;

/**
 * @author Anshul.Chauhan
 *
 *         PageBase will contains the all methods which is common for all pages
 */
public class PageBase {

	public static Properties config = null;
	public static Logger APP_LOGS = null;
	public static WebDriver driver = null;
	public static SoftAssert s_Assert = null;
	public static MenuContextClass menuClass = null;
	public static boolean isLogedIn = false;

	
	public PageBase(){
		if (driver == null) {
			initConfig();
			initBrowser();
			//invokeApplication();
			
			//Invoke menu class Object
			menuClass = new MenuContextClass();
		}
	}
	
	// Initialize Project Configuration File
	public static void initConfig() {

		// Declare the Log properties
		APP_LOGS = Logger.getLogger("rootLogger");

		// Initialize the Properties class
		config = new Properties();

		// Initialize the Assertion Class
		s_Assert = new SoftAssert();

		// read properties file
		try {
			FileInputStream readConfig = new FileInputStream(
					Constants.CONFIG_FILE_PATH);

			// load properties file
			config.load(readConfig);

		} catch (Exception e) {

			e.printStackTrace();
			APP_LOGS.debug("Error", e);
		}
	}

	// Initialize the Browser
	public static void initBrowser() {

		/**
		 * Check the Browser form properties file and open Browser Check if any
		 * previous WebDriver Instance is exist then execute the code in same
		 * web browser instance
		 */

		// No Exiting Browser, Then Open the new Browser
		if (driver == null) {
			if (config.getProperty("testBrowser").equalsIgnoreCase("Mozila")) {
				driver = new FirefoxDriver();

				

			} else if (config.getProperty("testBrowser").equalsIgnoreCase(
					"Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDrivers\\chromedriver.exe");
				driver = new ChromeDriver();


			} else if (config.getProperty("testBrowser").equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir")
								+ "\\BrowserDrivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

				APP_LOGS.debug("IE browser invoked");
			}else if (config.getProperty("testBrowser").equalsIgnoreCase(
					"HTMLUnit")){
				driver = new HtmlUnitDriver();
			}

			// Maximize the Browser window
			driver.manage().window().maximize();
			//driver.get(config.getProperty("siteURL"));
		//	invokeApplication();
		}
	}

	// Close the Browser

	public static void closeBrowser() {
		driver.close();
		APP_LOGS.debug("Browser closed ");
	}

	// Quit the Browser
	public void quitBrowser() {
		driver.quit();
		APP_LOGS.debug("Browser Quit : All Browser instaknces are closed");
	}

	// getElement using CSS Selector
	public WebElement getElementByCssSelector(String cssValue) {
		try {
			// This block will find element using CSS Selector value from web
			// page and return It.
			return driver.findElement(By.cssSelector(cssValue));
		} catch (Throwable t) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + cssValue);
			return null;
		}
	}

	// getElement using XPath
	public WebElement getElementByXPath(String xPath) {
		try {
			// This block will find element using Xapth value from web page and
			// return It.
			return driver.findElement(By.xpath(xPath));
		} catch (Exception e) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + xPath);
			return null;
		}
	}

	// getElement using Class Name
	public WebElement getElementByClassName(String className) {
		try {
			// This block will find element using Class Name value from web page
			// and return It.
			return driver.findElement(By.className(className));
		} catch (Exception e) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + className);
			return null;
		}
	}

	// getElement using Element ID
	public WebElement getElementByID(String elementID) {
		try {
			// This block will find element using Element ID value from web page
			// and return It.
			return driver.findElement(By.id(elementID));
		} catch (Exception e) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + elementID);
			return null;
		}
	}

	// getElement using Link Name
	public WebElement getElementByLinkName(String linkName) {
		try {
			// This block will find element using Link Name value from web
			// page and return It.
			return driver.findElement(By.linkText(linkName));
		} catch (Exception e) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + linkName);
			return null;
		}
	}

	// getElement using partial Link Text
	public WebElement getElementByPartialLinkText(String p_linkName) {
		try {
			// This block will find element using Link Text value from web
			// page and return It.
			return driver.findElement(By.partialLinkText(p_linkName));
		} catch (Exception e) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + p_linkName);
			return null;
		}
	}

	// getElement using Tag Name
	public WebElement getElementByTagName(String tagName) {
		try {
			// This block will find element using Tag Name value from web
			// page and return It.
			return driver.findElement(By.tagName(tagName));
		} catch (Exception e) {
			// If element not found on page then It will return null.
			APP_LOGS.debug("Object not found for key --" + tagName);
			return null;
		}
	}
	
	// verification
		public boolean isElementPresent(String xpathKey){
			try{
				getElementByXPath(xpathKey);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			return true;
		}			
		// finds the link on page
		public boolean isLinkPresent(String linkText){
			try{
				getElementByLinkName(linkText);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
}
