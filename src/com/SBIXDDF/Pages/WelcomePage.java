package com.SBIXDDF.Pages;

import org.openqa.selenium.WebElement;

import com.SBIXDDF.Core.PageBase;


/**
 * @author Anshul.Chauhan
 *
 */
public class WelcomePage extends PageBase{
	
	public WebElement welcomeicon = getElementByXPath("//*[@id='body_mid']/div/div[2]/div[2]/img");
	public WebElement myHomeLink = getElementByCssSelector("a[title='India'][href='/profile/home']");
	public WebElement myProgileLink = getElementByCssSelector("a[title='Global'][href='/profile/view']");
	public WebElement oyeMemberLink = getElementByCssSelector("a[title='Global'][href='/member/index']");
	public WebElement bloggerLInk = getElementByXPath("//*[@id='body_mid']/div/div[3]/div[2]/ul/li[5]/a");
	public WebElement opinoinPollLink = getElementByCssSelector("a[href='poll/index'][title='Opinion Polls']");
	public WebElement socialCampaignLink = getElementByCssSelector("a[title='Social Awareness Programs and Campaigns']");
	
	
	public WelcomePage checkHomeLink(){
		
		myHomeLink.click();
		driver.navigate().back();
		
		isElementPresent("//*[@id='body_mid']/div/div[2]/div[2]/img");
		return new WelcomePage();
	}
	
	public WelcomePage checkProfileLink(){
		myProgileLink.click();
		driver.navigate().back();
		
		isElementPresent("//*[@id='body_mid']/div/div[2]/div[2]/img");
		return new WelcomePage();
	}
	
	public WelcomePage checkMemberLink(){
		
		oyeMemberLink.click();
		driver.navigate().back();
		
		isElementPresent("//*[@id='body_mid']/div/div[2]/div[2]/img");
		return new WelcomePage();
	}
	
	public WelcomePage checkBloggerLink(){
		bloggerLInk.click();
		driver.navigate().back();
		
		isElementPresent("//*[@id='body_mid']/div/div[2]/div[2]/img");
		return new WelcomePage();
	}
	
	public WelcomePage checkPollLink(){
		opinoinPollLink.click();
		driver.navigate().back();
		
		return new WelcomePage();
	}
	
	public WelcomePage checkSocialCampaignLink(){
		socialCampaignLink.click();
		driver.navigate().back();
		
		return new WelcomePage();
	}
	
	
	public WelcomePage verifyHome(){
		boolean homeflag = false;
		
		if(welcomeicon.isDisplayed())
			homeflag = true;
		
		s_Assert.assertTrue(homeflag);
		s_Assert.assertAll();
		
		return new WelcomePage();
	}
	
	public WelcomePage getPageTitle(String pageTitle){
		s_Assert.assertEquals(driver.getTitle(), pageTitle);
		return new WelcomePage();
	}
	
	
	
}
