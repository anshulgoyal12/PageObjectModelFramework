package com.pageobject.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pageobject.pages.accounts.AccountsPage;

public class TopMenu {
	
	WebDriver driver;
	
	public TopMenu(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	public void goToHome(){
		
		
	}
	
	public void goToSalesInbox(){
		
		
	}
	
	public void goToFeeds(){
		
		
	}
	
	public AccountsPage goToAccounts() {
		
		Page.click("AccountsTab_CSS");
		
		return new AccountsPage();
		
	}
	
	public void signOut(){
		
		
	}
	
	

}
