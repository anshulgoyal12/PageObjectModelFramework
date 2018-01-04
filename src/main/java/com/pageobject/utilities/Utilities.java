package com.pageobject.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.pageobject.base.Page;

public class Utilities extends Page{
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static boolean isExecutables(String testName){
		
		String sheetName = "Test_Suite";
		int rows = excel.getRowCount(sheetName);
		
		for(int rowNum=2;rowNum<=rows;rowNum++){
			
			String testcase = excel.getCellData(sheetName, "TCID", rowNum);
			
			if(testcase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rowNum);
				
				if(runmode.equalsIgnoreCase("Y")){
					
					return true;
				}
				
				else{
					
					return false;
				}
					
					
				
			}
			
			
		}
		
		return false;
		
		
	}
	
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum=2; rowNum<=rows; rowNum++){
			
			for(int colNum=0; colNum<cols; colNum++){
				
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		return data;
	}
	
	public static void captureScreenshot() throws IOException{
		
		
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
	}

}