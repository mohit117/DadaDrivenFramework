package com.ebay.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.qa.base.BaseClass;

public class HomePage extends BaseClass{

	@FindBy(xpath="//input[@type='text']")
	private WebElement searchbar;
	
	@FindBy(xpath="//input[@id='gh-btn']")
	private WebElement search_btn;
	
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public SearchResultPage searchProduct(String keyword)
	{
		searchbar.sendKeys(keyword);
		search_btn.click();
		return new SearchResultPage();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	
	
	
	
}
