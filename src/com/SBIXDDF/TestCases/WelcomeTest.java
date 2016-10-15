package com.SBIXDDF.TestCases;

import org.testng.annotations.Test;

import com.SBIXDDF.Core.PageBase;
import com.SBIXDDF.Pages.LoginPage;
import com.SBIXDDF.Pages.WelcomePage;
import com.SBIXDDF.Util.TestUtil;

public class WelcomeTest {
	
	@Test
	public void verifyHeaderLinksIsPresent(){
		
		//Check Run Mode of Test
		TestUtil.checkTestRunMode("WelcomeSheet", "verifyHeaderLinksIsPresent");
		 
		WelcomePage welcomepage = null;
		//Verify is user logged in
		if(PageBase.isLogedIn){
			welcomepage = PageBase.menuClass.gotoWelcomePage();
		}else {
			LoginPage loginpage = new LoginPage();
			welcomepage = loginpage.doLogin();
		}
		
		welcomepage = welcomepage.checkHomeLink();
		welcomepage = welcomepage.checkProfileLink();
		welcomepage = welcomepage.checkPollLink();
		welcomepage = welcomepage.checkBloggerLink();
		welcomepage = welcomepage.checkMemberLink();
		welcomepage = welcomepage.checkSocialCampaignLink();
	}
	
	

}
