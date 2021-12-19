package Blazedemo.blazedemo.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Blazedemo.baseclass.BaseClass;
import Blazedemo.librarymethods.DataHandlers;

public class Booking extends BaseClass{
	
  private WebDriver driver;
	
  @Test(priority = 0)
  public void verifyTitleOfThePage() throws IOException {
	  WebElement element = getElement(driver, Locators.xpath, DataHandlers.getProperty("object_repository/objectProperties.properties", "homePageTitle"));
	  String actualtitle = element.getText();
	  String expectedTitle = "Welcome to the Simple Travel Agency!";
	  Assert.assertEquals(actualtitle, expectedTitle);
  }
  
  @Test(priority = 1)
  public void submitFindFlights() throws IOException {
	  WebElement departureElement = getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "dropDownDepartureCity"));
	  selectDropdown(departureElement, "Boston", -1 , null);
	  WebElement destinationElement = getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "dropDownDestinationCity"));
	  selectDropdown(destinationElement, "London", -1 , null);
	  getElement(driver, Locators.xpath,  DataHandlers.getProperty("object_repository/objectProperties.properties", "findFlightsButton")).click();
	  getElement(driver, Locators.xpath,  DataHandlers.getProperty("object_repository/objectProperties.properties", "defaultChooseflight")).click();
  }
  
  @Test(priority = 2 , dependsOnMethods="submitFindFlights")
  public void fillReservationForm() throws IOException {
	  getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "firstName")).sendKeys("Gamer");
	  getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "address")).sendKeys("Soe Hwrer");
	  getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "cityName")).sendKeys("city Name");
	  getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "stateName")).sendKeys("state Name");
	  getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "zipCodeName")).sendKeys("state zipCode");
	  getElement(driver, Locators.id,  DataHandlers.getProperty("object_repository/objectProperties.properties", "cardTypeId")).sendKeys("card Type Id");
	  getElement(driver, Locators.name,  DataHandlers.getProperty("object_repository/objectProperties.properties", "creditCardNumberName")).sendKeys("1234567890");
	  getElement(driver, Locators.id,  DataHandlers.getProperty("object_repository/objectProperties.properties", "creditCardMonthId")).sendKeys("11");
	  getElement(driver, Locators.id,  DataHandlers.getProperty("object_repository/objectProperties.properties", "creditCardYearId")).sendKeys("2021");
	  getElement(driver, Locators.id,  DataHandlers.getProperty("object_repository/objectProperties.properties", "nameOnCardId")).sendKeys("Card Member Name");
	  getElement(driver, Locators.xpath,  DataHandlers.getProperty("object_repository/objectProperties.properties", "purchaseFlightXpath")).click();
  }
  
  @Test(priority = 3, dependsOnMethods="fillReservationForm")
  public void captureSuccessMessage() throws IOException {
	  WebElement element = getElement(driver, Locators.xpath,  DataHandlers.getProperty("object_repository/objectProperties.properties", "successMessage"));
	  String actualMessage = element.getText().trim();
	  String expectedMessasge = "Thank you";
	  Assert.assertTrue(actualMessage.contains(expectedMessasge));
  }
  
  @Test(priority = 4, dependsOnMethods="captureSuccessMessage")
  public void assertConfirmationId() throws IOException {
	  WebElement element = getElement(driver, Locators.xpath,  DataHandlers.getProperty("object_repository/objectProperties.properties", "fetchConfirmationId"));
	  String confirmationId = element.getText().trim();
	  Assert.assertTrue(confirmationId != null, " Confirmation id is generated and printed as "+confirmationId);
  }
  
}
