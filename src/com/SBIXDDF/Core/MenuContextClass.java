package com.SBIXDDF.Core;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SBIXDDF.Pages.HomePage;
import com.SBIXDDF.Pages.WelcomePage;
import com.SBIXDDF.Pages.LoginPage;

public class MenuContextClass {
	
	Logger APP_LOGS = Logger.getLogger("devpinoyLogger");
	
	public WelcomePage gotoWelcomePage() {
		PageBase.driver.findElement(By.cssSelector("a[class='brand'][href='/site/welcome']")).click();
		APP_LOGS.info("Welcome Linked Clicked");
		return new WelcomePage();
	}
	
	public HomePage gotoHomePage(){
		PageBase.driver.findElement(By.cssSelector("a[href='/profile/home']")).click();
		APP_LOGS.info("Home Page link clicked succcessfully");
		return new HomePage();
	}
	
	
	public LoginPage signOutUser(){
		PageBase.driver.findElement(By.xpath("//*[@id='header']/div/div/div[2]/div/div[2]/a/img")).click();
		
		WebDriverWait wait = new WebDriverWait(PageBase.driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(PageBase.driver.findElement(By.cssSelector("a[href='/user/user/logout']"))));
		
		PageBase.driver.findElement(By.cssSelector("a[href='/user/user/logout']")).click();
		APP_LOGS.info("User has been signed out");
		return new LoginPage();
	}
	
	public LoginPage gotoLoginPage(){
		//Sign-up Link, element on Invalid Login Page
		PageBase.driver.findElement(By.xpath("//*[@id='header']/div[2]/div[1]/p/a")).click();;
		return new LoginPage();
	}

}
