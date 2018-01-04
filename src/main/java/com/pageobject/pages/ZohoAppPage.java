package com.pageobject.pages;

import org.openqa.selenium.By;

import com.pageobject.base.Page;
import com.pageobject.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {
	
	public CRMHomePage goToCRM(){
		
		click("CRMLink_XPATH");
		
		return new CRMHomePage();
		
	}
	
	public void goToSalesIQ(){
		
		
	}
}
