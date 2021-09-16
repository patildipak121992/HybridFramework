package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
	
private WebDriver driver;
	
	public HeaderPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="New Lead")
	WebElement lnk_NewLead;
	
	@FindBy(linkText="Logout")
	WebElement lnk_Logout;
	
	public void clickLogout()
	{
		lnk_Logout.click();
	}
	
	public void clickNewLead()
	{
		lnk_NewLead.click();
	}
	
	public boolean verifylogout()
	{
		return lnk_Logout.isDisplayed();
	}

}
