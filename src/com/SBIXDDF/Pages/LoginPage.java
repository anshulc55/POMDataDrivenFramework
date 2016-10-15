package com.SBIXDDF.Pages;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SBIXDDF.Core.PageBase;

/**
 * @author Anshul.Chauhan
 *
 */
public class LoginPage extends PageBase {

	public LoginPage verifyPageTitle(String title) {
		String pagetitle = driver.getTitle();
		s_Assert.assertEquals(pagetitle, title);
		s_Assert.assertAll();
		PageBase.APP_LOGS.debug("Page title is : " + pagetitle);
		return new LoginPage();
	}

	public WelcomePage doLogin(String username, String password) {
		
		driver.get(config.getProperty("siteURL"));
		System.out.println(isElementPresent("//*[@id='focusUserName']"));
		// Enter UserName
		//login_username.clear();
		getElementByXPath("//*[@id='focusUserName']").sendKeys(username);

		// Enter Password
		//login_password.clear();
		getElementByXPath("//*[@id='YumUserLogin_password']").sendKeys(password);

		// Hit login button
		getElementByXPath("//*[@id='oplhead']/div[2]/form/input").click();

		if(isElementPresent("//*[@id='body_mid']/div/div[2]/div[2]/img"))
			return new WelcomePage();
		else 
			return null;
	}
	
public InvalidLoginPage doInvalidLogin(String username, String password) {
		
		driver.get(config.getProperty("siteURL"));
		System.out.println(isElementPresent("//*[@id='focusUserName']"));
		// Enter UserName
		//login_username.clear();
		getElementByXPath("//*[@id='focusUserName']").sendKeys(username);

		// Enter Password
		//login_password.clear();
		getElementByXPath("//*[@id='YumUserLogin_password']").sendKeys(password);

		// Hit login button
		getElementByXPath("//*[@id='oplhead']/div[2]/form/input").click();

		if(isElementPresent("//*[@id='header']/div[2]/div[1]/p/a"))
			return new InvalidLoginPage();
		else 
			return null;
	}
	
	

	// default - correct , username password
	public WelcomePage doLogin() {

		return doLogin(config.getProperty("defaultUserName"),config.getProperty("defalutPassword"));
	}
	
	public LoginPage getpageTitle(String pageTitle){
		s_Assert.assertEquals(driver.getTitle(), pageTitle);
		s_Assert.assertAll();
		
		return new LoginPage();
	}

}
