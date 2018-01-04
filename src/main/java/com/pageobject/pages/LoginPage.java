package com.pageobject.pages;

import org.openqa.selenium.By;

import com.pageobject.base.Page;

public class LoginPage extends Page{
	
	public ZohoAppPage doLogin(String username, String password){
		
		type("Email_ID",username);
		type("Password_ID",password);
		
		click("SignInButton_ID");
		
		return new ZohoAppPage();
	}

}
