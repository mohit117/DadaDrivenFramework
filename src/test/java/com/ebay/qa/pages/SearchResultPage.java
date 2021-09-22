package com.ebay.qa.pages;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.qa.base.BaseClass;

public class SearchResultPage extends BaseClass{

	@FindBy(xpath="//div[@id='srp-river-results']//h3[@class='s-item__title']")
	private List<WebElement> product_list;
	
	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public ProductPage goToProductPage()
	{
		/*
		 * if((ArrayList)product_list.size() == 0 || product_list == null) { throw new
		 * NullPointerException(); }
		 */
		System.out.println(product_list.get(0));
		product_list.get(0).click();
		return new ProductPage();
		
	}
	
}
