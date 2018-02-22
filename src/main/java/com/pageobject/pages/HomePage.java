package com.pageobject.pages;

import org.openqa.selenium.By;

import com.pageobject.base.Page;

public class HomePage extends Page {
	
	public LoginPage goToLogin(){
		
		click("Login_XPATH");
		
		return new LoginPage();
		
	}
	
	public void goToSignUp(){
		
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/a[3]")).click();
	}
	
	public void goToSupport(){
		
		
	}
	
	public void goToLearnMore(){
		
		
	}
	
	public void validateAllFooterLinks(){
		
		
	}
	
	public void goToApplyNow(){
		
		
	}

}
