package Blazedemo.baseclass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Blazedemo.librarymethods.ReusableMethods;

public class BaseClass extends ReusableMethods{
	
	public WebDriver driver;

	@BeforeTest
	public void setupApplication() {
		Reporter.log("===== Browser Session Started =====", true);
		System.setProperty("webdriver.chrome.driver", "browser_servers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments(" --no-sandbox");
		driver = new ChromeDriver(options);
		driver.get("https://blazedemo.com/");
		Reporter.log("=====Application Started=====", true);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	 @AfterTest
	  public void afterTest() {
		 driver.close();
		 Reporter.log("=====Application Finished=====", true);
	  }
	
}