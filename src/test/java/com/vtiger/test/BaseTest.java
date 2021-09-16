package com.vtiger.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.common.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
public static WebDriver driver;
public static ExtentHtmlReporter htmlReporter;
public static ExtentReports extent;
public static ExtentTest logger;
public static Properties prop;
public static List<Map<String,List<String>>> DataList;
	


	@BeforeSuite
	public void launchApp()
	{
		DataList=loadTestData();		
		readProperties();
		createExtentReport();
		if(prop.getProperty("browser").equals("Chrome"))
		{
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equals("Firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
		}
		else if(prop.getProperty("browser").equals("Edge"))
		{
		WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
		}
		else if(prop.getProperty("browser").equals("IE"))
		{
		WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
		}
        driver.get(prop.getProperty("AppUrl"));        
        driver.manage().window().maximize();
	}
	
	
	public void readProperties()
	{
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/vtiger/config/projectSetting.properties");
			prop.load(fis);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	public void createExtentReport()
	{
		DateFormat fmat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		Date d = new Date();
		String str = fmat.format(d);
		System.out.println(str);
		String ReportPath = System.getProperty("user.dir")+"/src/test/java/com/vtiger/report/";
		String ReportName = "vTigerAutomationResult_"+str+".html";
		htmlReporter = new ExtentHtmlReporter(ReportPath+ReportName);
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
	}
	
	public List<Map<String,List<String>>> loadTestData()
	{
		List<Map<String,List<String>>> ls = new ArrayList<Map<String,List<String>>>();		
		Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx");
		int rowcount = xr.getRowCount("Sheet1");
		int colcount = xr.getColumnCount("Sheet1");
		for(int i=2;i<=rowcount;i++)
		{
			Map<String,List<String>> m = new HashMap<String,List<String>>();
			List<String> lst = new ArrayList<String>();
		    String vTCname= xr.getCellData("Sheet1", "TestCaseName", i).trim();
		    for(int j=1;j<=colcount;j++)
		    {
		    	String vData=xr.getCellData("Sheet1", j, i).trim();
		    	lst.add(vData);
		    }
		    
		    m.put(vTCname, lst);
		    ls.add(m);
		}
		
		return ls;
	}
	
	@AfterSuite
	public void closeApp()
	{
		driver.quit();
	}

}
