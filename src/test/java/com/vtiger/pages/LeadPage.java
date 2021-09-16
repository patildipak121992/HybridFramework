package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.Utilities;

public class LeadPage extends HeaderPage {
	
private WebDriver driver;
public Utilities utils;
	
	public LeadPage(WebDriver driver,ExtentTest logger)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		utils = new Utilities(driver,logger);
	}
	
	@FindBy(name="lastname")
	WebElement tb_lastname;
	
	@FindBy(how = How.NAME, using = "company")
	WebElement tb_company;
	
	@FindBy(name="button")
	WebElement btn_save;
	
	
	public void createLeadwithMandatoryFields(String lname, String comp)
	{
		utils.setInputText(tb_lastname, lname, "Last Name");		
		utils.setInputText(tb_company, comp, "Company");		
		utils.ClickElement(btn_save, "Save Button");
	}
	
	
	
	

}
