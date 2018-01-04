package com.pageobject.pages.accounts;

import org.openqa.selenium.By;

import com.pageobject.base.Page;

	public class CreateAccountPage extends Page{
	
		public void accountName(String name){
			
			type("AccountName_CSS",name);
		}
		
		
	}


