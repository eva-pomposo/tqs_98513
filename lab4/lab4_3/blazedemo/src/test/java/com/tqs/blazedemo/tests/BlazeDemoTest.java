package com.tqs.blazedemo.tests;

import com.tqs.blazedemo.webpages.HomePage;
import com.tqs.blazedemo.webpages.ReservePage;
import com.tqs.blazedemo.webpages.PurchaseApplyPage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        //use FF Driver
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void applyAsFlight() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);

        //Check if page is opened
        assertTrue(home.isPageOpened());

        home.clickOnFindFlightsButton();

        //Create object of ReservePage Class
        ReservePage reservePage = new ReservePage(driver);

        //Check if page is opened
        assertTrue(reservePage.isPageOpened());

        reservePage.clikOnFlightButton();

        //Create object of PurchaseApplyPage
        PurchaseApplyPage applyPage =new PurchaseApplyPage(driver);

        //Check if page is opened
        assertTrue(applyPage.isPageOpened());

        //Fill up data
        applyPage.setName("Eva");
        applyPage.setAddress("123 Main");
        applyPage.setCity("Aveiro");
        applyPage.setState("Portugal");
        applyPage.setZipCode("1234-567");
        applyPage.setCreditCardNumber("12345678");
        applyPage.setCreditCardMonth("12");
        applyPage.setCreditCardYear("2018");
        applyPage.setNameOnCard("Eva");

        //Click on Purchase Flight Button
        //applyPage.clickOnPurchaseFlightButton();
    }

    @AfterEach
    public void close(){
        driver.close();
    }
}
