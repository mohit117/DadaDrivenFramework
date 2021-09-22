package com.ebay.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static FileInputStream fis = null;
	public static Properties prop = null;
	
	@BeforeTest
	public void initalSetUp()
	{
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	@Parameters(value={"browserName"})
	public void beforeMethodtMethod(String browserName)
	{


		try {
			setupDriver(browserName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();
		
		driver.get(prop.getProperty("url"));

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}


	
	@AfterMethod
	public void afterMethodMethod()
	{
		driver.quit();
	}

	

	@AfterTest
	public void afterTestMethod()
	{
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void setupDriver(String browserName)
	{

		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.iedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{

			WebDriverManager.chromedriver().setup(); 
			driver = new ChromeDriver();

		}


	}

}
