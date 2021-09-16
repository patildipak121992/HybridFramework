package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends HeaderPage {
	
private WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//h3[text()='My Tickets']")
	WebElement lbl_MyTicket;
	
	
	
	
	
	public boolean verifyMyTicketDashboard()
	{
		return lbl_MyTicket.isDisplayed();
	}

}
