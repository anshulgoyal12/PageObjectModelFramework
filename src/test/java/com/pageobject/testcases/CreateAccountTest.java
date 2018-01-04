package com.pageobject.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.pageobject.base.Page;
import com.pageobject.pages.ZohoAppPage;
import com.pageobject.pages.accounts.AccountsPage;
import com.pageobject.pages.accounts.CreateAccountPage;
import com.pageobject.utilities.Utilities;



public class CreateAccountTest {
	
	// Create Account on Zoho Website
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(String AccountName, String Runmode){
		
		if(!Runmode.equals("Y")){
			
			throw new SkipException("Skipping the test case as the run mode for data set is NO");
		}
		
		ZohoAppPage zap = new ZohoAppPage();
		zap.goToCRM();
		AccountsPage ap = Page.menu.goToAccounts();
		CreateAccountPage cap = ap.goToCreateAccounts();
		cap.accountName(AccountName);
		
	}



}
