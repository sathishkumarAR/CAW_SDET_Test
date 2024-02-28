package com.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.pages.DynamicTablePage;

public class TestNGconfig {
	
	public DynamicTablePage dynamicTablePage;
	public WebDriver driver;
	public SoftAssert softAssert= new SoftAssert();
	

	@BeforeMethod
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
		
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--incognito");
		
		driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		
		dynamicTablePage= PageFactory.initElements(driver, DynamicTablePage.class);
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		softAssert.assertAll();
		driver.quit();
	}
	
}
