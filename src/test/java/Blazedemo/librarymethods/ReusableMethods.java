package Blazedemo.librarymethods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReusableMethods {
	
	public static enum Locators {
        xpath, id, name, classname, paritallinktext, linktext, tagname
    };
	
	public WebElement getElement(WebDriver driver, Locators locators, String elementLocator) {
		By byElement = null;
		switch (locators) {
		case name:
			byElement = By.name(elementLocator);
			break;

		case id:
			byElement = By.id(elementLocator);
			break;

		case xpath:
			byElement = By.xpath(elementLocator);
			break;

		case classname:
			byElement = By.className(elementLocator);
			break;

		case linktext:
			byElement = By.linkText(elementLocator);
			break;

		case paritallinktext:
			byElement = By.partialLinkText(elementLocator);
			break;

		case tagname:
			byElement = By.tagName(elementLocator);
			break;
		}
		WebElement element = driver.findElement(byElement);
		return element;
	}
	
	public void implictWait(WebDriver driver, long time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectDropdown(WebElement element, String value, int index, String text) {
		Select drpDown = new Select(element);
		try {
			if(value != null) {
				drpDown.selectByValue(value);
			} else if(index > -1) {
				drpDown.selectByIndex(index);
			} else if(text != null) {
				drpDown.selectByVisibleText(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
