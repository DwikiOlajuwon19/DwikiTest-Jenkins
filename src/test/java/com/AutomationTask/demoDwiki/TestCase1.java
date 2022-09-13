package com.AutomationTask.demoDwiki;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase1 {
	
	public static WebDriver driver;

	@BeforeMethod
	public void launchDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Jenkins\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void Test1() throws InterruptedException {
		//SoftAssert softAssert = new SoftAssert();
		driver.navigate().to("https://tessa.equine.co.id/");
		//Thread.sleep(10000);
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Welcome to New TESSA";
		 Assert.assertEquals(ActualTitle, ExpectedTitle);
		//softAssert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println("TC 1 is executed");
		System.out.println(ActualTitle);
		//softAssert.assertAll();
		// System.out.println("Assert passed");

	}
	
	@AfterMethod
	public void quit() {
		driver.quit();
	}

}
