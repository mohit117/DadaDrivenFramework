package com.ebay.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ebay.qa.base.BaseClass;

public class CheckoutPage extends BaseClass{

	@FindBy(xpath="//input[@id='firstName']")
	private WebElement first_Name_txt;

	@FindBy(xpath="//input[@id='lastName']")
	private WebElement last_Name_txt;

	@FindBy(xpath="//input[@id='addressLine1']")
	private WebElement street_address_txt;

	@FindBy(xpath="//input[@id='city']")
	private WebElement city_txt;

	@FindBy(xpath="//select[@id='stateOrProvince']")
	private WebElement state_dropdown;

	@FindBy(xpath="//input[@id='postalCode']")
	private WebElement postal_code_txt;

	@FindBy(xpath="//input[@id='email']")
	private WebElement email_txt;

	@FindBy(xpath="//input[@id='emailConfirm']")
	private WebElement email_confirm_txt;

	@FindBy(xpath="//input[@id='phoneNumber']")
	private WebElement phone_txt;

	@FindBy(xpath="//button[contains(text(),'Done')]")
	private WebElement address_done_btn;

	@FindBy(xpath="//div[@id='addressLine1-error']")
	private WebElement address_error_lbl;

	@FindBy(xpath="//input[@id='selectable-render-summary1']")
	private WebElement add_card_radio_btn;

	@FindBy(xpath="//input[@id='cardNumber']")
	private WebElement card_txt;

	@FindBy(xpath="//input[@id='cardExpiryDate']")
	private WebElement card_Expiry_Date_txt;

	@FindBy(xpath="//input[@id='securityCode']")
	private WebElement security_code_txt;

	@FindBy(xpath="//button[contains(text(),'Done')]")
	private WebElement card_done_btn;

	@FindBy(xpath="//div[@id='cardNumber-error']")
	private WebElement card_error_lbl;

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement getCardError() {
		return card_error_lbl;
	}

	public void enterShippingAddress() throws InterruptedException {

		first_Name_txt.sendKeys(prop.getProperty("firstName"));
		last_Name_txt.sendKeys(prop.getProperty("lastName"));
		street_address_txt.sendKeys(prop.getProperty("streetAddress"));
		city_txt.sendKeys(prop.getProperty("city"));

		Select drp = new Select(state_dropdown);
		drp.selectByVisibleText(prop.getProperty("state"));

		postal_code_txt.sendKeys(prop.getProperty("postalCode"));
		email_txt.sendKeys(prop.getProperty("email"));
		email_confirm_txt.sendKeys(prop.getProperty("email"));

		phone_txt.sendKeys(prop.getProperty("phone"));

		address_done_btn.click();

		Thread.sleep(4000);

		if(address_error_lbl.isDisplayed()) {
			address_done_btn.click();
		}

	}

	public void enterCardDetails() {

		add_card_radio_btn.click();
		card_txt.sendKeys(prop.getProperty("cardNo"));
		card_Expiry_Date_txt.sendKeys(prop.getProperty("cardExpiryDate"));
		security_code_txt.sendKeys(prop.getProperty("security_code"));
		card_done_btn.click();
	}


	public void enterCardDetails(String cardNo, String expiryDate, String code) {

		add_card_radio_btn.click();
		card_txt.sendKeys(cardNo);
		card_Expiry_Date_txt.sendKeys(expiryDate);
		security_code_txt.sendKeys(code);
		card_done_btn.click();
	}


}
