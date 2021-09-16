package com.vtiger.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class E2E extends BaseTest {
	
	
	@Test
	public void CreateLeadswithMandatoryData_TC04()
	{
		logger=extent.createTest("CreateLeadswithMandatoryData_TC04");
		LoginPage lp = new LoginPage(driver,logger);
		lp.Login(DataList.get(3).get("CreateLeadswithMandatoryData_TC04").get(0), DataList.get(3).get("CreateLeadswithMandatoryData_TC04").get(1));
		LeadPage ldp = new LeadPage(driver,logger);
		ldp.clickNewLead();
		ldp.createLeadwithMandatoryFields(DataList.get(3).get("CreateLeadswithMandatoryData_TC04").get(2), DataList.get(3).get("CreateLeadswithMandatoryData_TC04").get(3));
		ldp.clickLogout();
		extent.flush();				
	}
	
	

}
