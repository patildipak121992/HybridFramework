package com.vtiger.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class Utilities {
	
	private WebDriver driver;
	private ExtentTest logger;
	
	public Utilities(WebDriver driver,ExtentTest logger)
	{
		this.driver = driver;
		this.logger = logger;
	}
	
	
	public void setInputText(WebElement elm, String val,String elmName)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(val);
		logger.pass(val+" has been entered successfully in "+elmName);
		}
		catch(Exception e)
		{
			logger.fail(e.getMessage()+"<span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");	
		
		}
	}
	
	
	public void ClickElement(WebElement elm,String elmName)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(elm));		
		elm.click();
		logger.pass(elmName+" has been clicked");
		}
		catch(Exception e)
		{
			logger.fail(e.getMessage()+"<span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");	
		}
	}
	
	public boolean IsElementDisplay(WebElement elm,String elmName)
	{
		boolean val = false;
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(elm));		
		val=elm.isDisplayed();
		logger.pass(elmName+" has been displayed");
		return val;
		}
		catch(Exception e)
		{
			logger.fail(e.getMessage()+"<span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");	
		return val;
		}
		
		
	}
	
	public String getScreenshot()  {
		//below line is just to append the date format with the screenshot name to avoid duplicate names		
	    String destination=null;
		try
		{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		destination = System.getProperty("user.dir") + "/src/test/java/com/vtiger/report/screenshot/"+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Returns the captured file path
		return destination;
	}

}
