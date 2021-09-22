package com.ebay.qa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ebay.qa.base.BaseClass;
import com.ebay.qa.pages.CartPage;
import com.ebay.qa.pages.CheckoutPage;
import com.ebay.qa.pages.HomePage;
import com.ebay.qa.pages.ProductPage;
import com.ebay.qa.pages.SearchResultPage;

public class DataProviderTestUsingMap extends BaseClass{

	HomePage hpObj;
	SearchResultPage srpObj;
	ProductPage ppObj;
	CartPage cartObj;
	CheckoutPage coutObj;


	@BeforeMethod
	public void setUp()
	{
		hpObj = new HomePage();
	}

	@DataProvider(name = "test-data-from-map")
	public Object[][] getData() throws IOException{


		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\testData\\Data.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[lastRowNum][1];

		for(int i =0;i<lastRowNum;i++) {
			HashMap<Object,Object> datamap = new HashMap();
			
			for(int j=0; j<lastCellNum;j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			
			obj[i][0] = datamap;
		}

		return obj;

	}

	@Test(dataProvider ="test-data-from-map")
	public void buyProduct(Map<Object,Object> map) throws InterruptedException {
		srpObj = hpObj.searchProduct(map.get("SearchKeyword").toString());
		//Thread.sleep(3000);
		ppObj = srpObj.goToProductPage();
		//Thread.sleep(3000);
		cartObj = ppObj.addToCart();
		cartObj.validateTotalCartItems();
		//Thread.sleep(3000);
		coutObj = cartObj.goToCheckout();
		//Thread.sleep(2000);
		coutObj.enterShippingAddress();
		Thread.sleep(3000);
		coutObj.enterCardDetails(map.get("CardNo").toString(), map.get("CardExpiryDate").toString(), map.get("SecurityCode").toString());
		
		Assert.assertEquals(coutObj.getCardError().getText(), "We don't support this card. Please use a different one.", "Card details are not valid");
	

	}





}
