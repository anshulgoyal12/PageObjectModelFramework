package com.pageobject.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.pageobject.utilities.ExtentManager;
import com.pageobject.utilities.Utilities;
import com.pageobject.utilities.Xls_Reader;

public class Page {

	public static WebDriver driver;
	public static TopMenu menu;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger logs = Logger.getLogger("devpinoyLogger");
	public static Xls_Reader excel = null;
	public static WebDriverWait wait;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	public Page() {

		if (driver == null) {
			
			try {

				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageobject\\properties\\Config.properties");
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();

			}
			
			try{
				
				Config.load(fis);
				logs.debug("Loading the Config Properties");
				
			}catch (IOException e) {
				
				e.printStackTrace();
				
			}

			try {
				
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageobject\\properties\\OR.properties");
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			
			try{
				
				OR.load(fis);
				logs.debug("Loading the OR Properties");
				
			}catch (IOException e){
				
				e.printStackTrace();
			}

			excel = new Xls_Reader(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageobject\\Excel\\testdata.xls");
			
			if(System.getenv("browser")!= null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
				
			}else{
				
				browser = Config.getProperty("browser");
			}

			Config.setProperty("browser", browser);
			
			if (Config.getProperty("browser").equals("Firefox")) {

				driver = new FirefoxDriver();
				logs.debug("Firefox Browser Launched !!");
			}
			
						
			else if (Config.getProperty("browser").equals("Chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();

			}

			else if (Config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}
			
			logs.debug("Navigating to:" + Config.getProperty("testsite"));
			driver.get(Config.getProperty("testsite"));
			logs.info("Website succesfully opened");

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);

			menu = new TopMenu(driver);
			

		}

	}
	
	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;
		}

		catch (NoSuchElementException e) {

			return false;

		}
	}
	
	public void verifyEquals(String expected, String actual) throws IOException{
		
		try{
			
			Assert.assertEquals(actual, expected);
		}
		
		catch (Throwable t){
			
			//Reportng Report
			Utilities.captureScreenshot();
			Reporter.log("<br>"+"Verification failure : "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+"><img src="+Utilities.screenshotName+" height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//Extent Report
			test.log(LogStatus.FAIL, "Verification failed with Exception: "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
			
		}
	}
	
	public static void click(String locator){
		
		if(locator.endsWith("_CSS")){
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		
		else if(locator.endsWith("_XPATH")){
			
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		
		else if(locator.endsWith("_ID")){
			
			driver.findElement(By.id(OR.getProperty(locator))).click();
		
		}
		
		test.log(LogStatus.INFO, "Clicking on : "+locator);
	}
	

	public static void type(String locator, String value){
		
		if(locator.endsWith("_CSS")){
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}
		
		else if(locator.endsWith("_XPATH")){
			
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		
		else if(locator.endsWith("_ID")){
			
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		
		test.log(LogStatus.INFO, "Typing in : "+locator+" entered values as : "+value);
	}
	
	public static WebElement dropdown;
	
	public void select(String locator, String value){
		
		if(locator.endsWith("_CSS")){
			
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}
		
		else if(locator.endsWith("_XPATH")){
			
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}
		
		else if(locator.endsWith("_ID")){
			
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting from dropdown: "+locator+"value as"+value);
	}

}
