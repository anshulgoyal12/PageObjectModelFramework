package com.pageobject.rough;

import com.pageobject.base.Page;
import com.pageobject.pages.HomePage;
import com.pageobject.pages.LoginPage;
import com.pageobject.pages.ZohoAppPage;
import com.pageobject.pages.accounts.AccountsPage;
import com.pageobject.pages.accounts.CreateAccountPage;

public class LoginTest {
	
	public static void main(String[] args) throws InterruptedException{
		
	// Add all code mention here
	HomePage home = new HomePage();
	LoginPage lp = home.goToLogin();
	ZohoAppPage zap = lp.doLogin("anshulgoyal190@gmail.com", "Anshul@12345");
	zap.goToCRM();
	AccountsPage ap = Page.menu.goToAccounts();
	CreateAccountPage cap = ap.goToCreateAccounts();
	cap.accountName("Anshul");
	
	}
	
}
