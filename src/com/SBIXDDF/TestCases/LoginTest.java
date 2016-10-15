package com.SBIXDDF.TestCases;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.SBIXDDF.Core.PageBase;
import com.SBIXDDF.Pages.InvalidLoginPage;
import com.SBIXDDF.Pages.WelcomePage;
import com.SBIXDDF.Pages.LoginPage;
import com.SBIXDDF.Util.TestUtil;

public class LoginTest {

	@DataProvider
	public Object[][] getLoginData() {
		return TestUtil.getTestData("LoginSheet", "loginTest");
	}

	@Test(dataProvider = "getLoginData")
	public void loginTest(HashMap<String, String> dataMap) {

		// Check RunMode for TestCase
		TestUtil.checkTestRunMode("LoginSheet", "loginTest");

		// Logout of already Loggedin
		if (PageBase.isLogedIn) {
			PageBase.menuClass.signOutUser();
			PageBase.isLogedIn = false;
		}

		LoginPage loginPage = new LoginPage();
		WelcomePage homePage = loginPage.doLogin(dataMap.get("UserName"),dataMap.get("Password"));
		
		homePage.getPageTitle(dataMap.get("PageTitle"));

		PageBase.isLogedIn = true;
	}

	@DataProvider
	public Object[][] getInvalidLoginData() {
		return TestUtil.getTestData("LoginSheet", "invalidloginTest");
	}

	@Test(dataProvider = "getInvalidLoginData")
	public void invalidloginTest(HashMap<String, String> datamap) {

		// Check Run Mode for TestCase
		TestUtil.checkTestRunMode("LoginSheet", "invalidloginTest");

		// Logout of already Loggedin
		//LoginPage loginPage = null;
		
				if (PageBase.isLogedIn) {
					LoginPage loginPage = PageBase.menuClass.signOutUser();
					PageBase.isLogedIn = false;
				}

				LoginPage loginPage = new LoginPage();
			   InvalidLoginPage invalidLogin = loginPage.doInvalidLogin(datamap.get("UserName"),datamap.get("Password"));
				invalidLogin.getPageTitle(datamap.get("PageTitle"));
				PageBase.menuClass.gotoLoginPage();
	}
	

}
