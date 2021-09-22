package com.ebay.qa.pages;



import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ebay.qa.base.BaseClass;

public class ProductPage extends BaseClass{
	
	Select dropdown;
	
	@FindBy(xpath="//a[@id='isCartBtn_btn'] | //a[@id='atcRedesignId_btn']")
	private WebElement cart_btn;
	
	@FindBy(xpath="//span[text()='Go to cart']")
	private WebElement cart_btn_on_popup;
	
	@FindBy(xpath="//select[contains(@id, 'msku-sel')]")
	private List<WebElement> dropdowns;
	
	@FindBy(css=".app-atc-layer-redesign-content-wrapper")
	private List<WebElement> checkout_popup;
	
	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public CartPage addToCart() throws InterruptedException {
			
		 	int dropsize = dropdowns.size();
		 	if(dropsize>0){
		 		for(int i=0;i<dropsize;i++) {
		 			dropdown = new Select(dropdowns.get(i));
		 			dropdown.selectByIndex(1);
		 		}
		 	}
		 	
		 	int frame = checkout_popup.size();
		 	if(frame>0) {
		 		cart_btn_on_popup.click();
		 	}
		 	else {
		 		cart_btn.click();
		 	}
		return new CartPage();
		
	}

}
