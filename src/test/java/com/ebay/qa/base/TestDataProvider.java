package com.ebay.qa.base;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name = "test-data")
  	public static Object[][] dataProvFunc(){
        	return new Object[][]{
              	{"shoes"},{"iphone"}
        	};
  	}
	
	
}
