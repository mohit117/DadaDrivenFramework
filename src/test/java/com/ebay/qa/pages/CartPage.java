package com.ebay.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ebay.qa.base.BaseClass;

public class CartPage extends BaseClass {

	@FindBy(xpath="//i[@id='gh-cart-n']")
	private WebElement cartIcon;

	@FindBy(xpath ="//button[contains(text(),'Go to checkout')]")
	private WebElement checkout_btn;

	@FindBy(xpath="//*[@id='gxo-btn']")
	private WebElement guest_checkout;


	@FindBy(xpath = "//i[@id='gh-cart-n']") 
	private WebElement total_cart_items;


	public CartPage() {
		PageFactory.initElements(driver, this);
	}


	public void validateTotalCartItems() { 
		String str = total_cart_items.getText();

	try { 
		int items = Integer.parseInt(str); 
		Assert.assertEquals(items, 1, "Total 1 item is present"); 
	} catch (NumberFormatException e) { 
		e.printStackTrace(); }

	}



	public CheckoutPage goToCheckout() {
		checkout_btn.click();
		guest_checkout.click();

		return new CheckoutPage();
	}

}
