package com.SBIXDDF.Pages;

import com.SBIXDDF.Core.PageBase;

public class InvalidLoginPage extends PageBase{
	
	public InvalidLoginPage getPageTitle(String pageTitle){
		s_Assert.assertEquals(driver.getTitle(), pageTitle);
		s_Assert.assertAll();
		
		return new InvalidLoginPage();
	}

}
