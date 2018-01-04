package com.pageobject.pages.accounts;

import org.openqa.selenium.By;

import com.pageobject.base.Page;

public class AccountsPage extends Page{
	
	public CreateAccountPage goToCreateAccounts(){
		
		click("CreateAccountButton_CSS");
		
		return new CreateAccountPage();
	}

}
