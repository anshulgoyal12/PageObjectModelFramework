package com.pageobject.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.pageobject.pages.HomePage;
import com.pageobject.pages.LoginPage;
import com.pageobject.utilities.Utilities;

public class LoginTest {
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void loginTest(String Email, String Password, String Runmode){
		
		if(!Runmode.equals("Y")){
			
			throw new SkipException("Skipping the test case as the run mode for data set is NO");
		}
		
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		lp.doLogin(Email, Password);
		
		
	}

}
