package com.AutomationTask.demoDwiki;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

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
		String ExpectedTitle = "Welcome to New TESSA-GIT";
		 Assert.assertEquals(ActualTitle, ExpectedTitle);
		//softAssert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println("TC 1 is executed");
		System.out.println(ActualTitle);
		//softAssert.assertAll();
		// System.out.println("Assert passed");

	}
	
	@AfterMethod
	public void quit(ITestResult result) throws JiraException {
		driver.quit();
		
		if (result.getStatus() == ITestResult.FAILURE) {
			
			BasicCredentials creds = new BasicCredentials("yones.deliyandra", "12345678*");
			JiraClient jira = new JiraClient("https://jira.equine.co.id/",creds);
			Issue issue = jira.createIssue("TS", "Bug").field(net.rcarz.jiraclient.Field.SUMMARY, result.getMethod().getMethodName() +"is failed due to: "+ result.getThrowable().toString()).field(net.rcarz.jiraclient.Field.DESCRIPTION, "get the description").execute();
			System.out.println("issue creat in jira with the name :" +issue.getKey());
			
		}
	}

}
