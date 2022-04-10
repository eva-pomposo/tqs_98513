package com.tqs.blazedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlazedemoSteps {
    private final WebDriver driver = new FirefoxDriver();

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver.get(url);
    }

    @Then("A new page with the tilte {string} appears")
	public void verifyPageTitle(String title) {
        assertEquals(driver.getTitle(), title);
	}

    @When("I choose departure city {string}")
    public void chooseDeparture(String departureCity) {
        Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[3]/form/select[1]")));
        dropdown.selectByValue(departureCity);
    }

    @When("I choose destination city {string}")
    public void chooseDestination(String destinationCity) {
        Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[3]/form/select[2]")));
        dropdown.selectByValue(destinationCity);
    }

    @When("I click Find Flights")
    public void clickButtonFindFlights() {
        driver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();
    }

    @When("I click Choose This Flight")
    public void clickButtonChooseThisFlight() {
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")).click();
    }

    @When("I write the name {string}")
    public void writeName(String name) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(name);
    }

    @When("I write the address {string}")
    public void writeAddress(String address) {
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(address);
    }

    @When("I write the city {string}")
    public void writeCity(String city) {
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @When("I write the state {string}")
    public void writeState(String state) {
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys(state);
    }

    @When("I write the zipcode {string}")
    public void writeZipCode(String zipCode) {
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys(zipCode);
    }

    @When("I write the credit card number {string}")
    public void writeCreditCardNumber(String creditCardNumber) {
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber);
    }

    @When("I write the credit card month {string}")
    public void writeCreditCardMonth(String creditCardMonth) {
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys(creditCardMonth);
    }

    @When("I write the credit card year {string}")
    public void writeCreditCardYear(String creditCardYear) {
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys(creditCardYear);
    }

    @When("I write the name on card {string}")
    public void writeNameOnCard(String nameOnCard) {
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);
    }

    @When("I click Purchase Flight")
    public void clickButtonPurchaseFlight() {
        driver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
    
}

