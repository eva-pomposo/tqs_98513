package com.tqs.blazedemo.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SeleniumJupiter.class)
public class HomePage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/";

    //Locators
    @FindBy( xpath = "/html/body/div[2]/div/h1")
    WebElement heading;

    //Apply as Developer Button
    @FindBy( xpath = "/html/body/div[3]/form/div/input")
    private WebElement findFlightsButton;

    //Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return heading.getText().contains("Welcome to the Simple Travel Agency!");
    }

    public void clickOnFindFlightsButton(){
        findFlightsButton.click();
    }
    
}
