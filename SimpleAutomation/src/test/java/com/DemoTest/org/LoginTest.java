package com.DemoTest.org;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utilities.org.ExcelDataRead;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	WebDriver driver;
	ChromeOptions options;
	String appUrl = "https://orangehrm-demo-6x.orangehrmlive.com/auth/login";
	String inputfilename="C:/Users/Karthik/eclipse-workspace/SimpleAutomation/src/test/java/com/utilities/org/TestData.xlsx";
	String sheetname = "Inputs";

	@Test(dataProvider = "userdata")
	public void toLogin(String username, String password) throws InterruptedException {

		System.out.println("In Login Method!!!!");

		System.out.println("username is: " + username);
		System.out.println("password is: " + password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name=\"txtUsername\"]")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name=\"txtUsername\"]")).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).clear();
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"account-job\"]/i")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@id=\"logoutLink\"]")).click();
	}

	@BeforeClass
	 public void setup() {
		  System.out.println("In setup method!!!!");

		  WebDriverManager.chromedriver().setup();
		  options = new ChromeOptions();
		  options.addArguments("--ignore-certificate-errors");
		  driver = new ChromeDriver(options);
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.get(appUrl);
		  driver.manage().window().maximize();
	}

	@AfterClass
	public void teardown() {
		System.out.println("In tear down method");
		if (driver != null)
			driver.close();
		driver.quit();

	}

	@DataProvider(name = "userdata")
	public String[][] getData() throws IOException {
		System.out.println("In get data method!!!!");

		int rowcount = ExcelDataRead.getRowCount(inputfilename, sheetname);
		System.out.println("Rowcount is: " + rowcount);

		int cellcount = ExcelDataRead.getCellData(inputfilename, sheetname, 0);
		System.out.println("Cell count is: " + cellcount);

		String loginData[][] = new String[rowcount][cellcount];
		for (int row = 1; row <= rowcount; row++) {
			for (int cell = 0; cell < cellcount; cell++) {
				loginData[row - 1][cell] = ExcelDataRead.getCellData(inputfilename, sheetname, row, cell);
			}
		}
		return loginData;
	}
}
