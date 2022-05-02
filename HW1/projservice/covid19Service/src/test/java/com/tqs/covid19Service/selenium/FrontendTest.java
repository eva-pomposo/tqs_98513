package com.tqs.covid19Service.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class FrontendTest {
    private WebDriver driver;
    JavascriptExecutor js;

    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void tearDown() {
      driver.quit();
    }

    @Test
    public void getHistoryWithoutDayTest() {
      driver.get("http://localhost:8080/");
      driver.manage().window().setSize(new Dimension(1848, 1053));
      driver.findElement(By.cssSelector("h2")).click();
      assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Analysis");
      driver.findElement(By.cssSelector(".btn")).click();
      driver.findElement(By.cssSelector("h2")).click();
      assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Analysis: Afghanistan");
      driver.findElement(By.linkText("2")).click();
    }

    @Test
    public void getHistoryWithDayTest() {


      driver.get("http://localhost:8080/");
      driver.manage().window().setSize(new Dimension(1848, 1053));
      driver.findElement(By.cssSelector("h2")).click();
      assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Analysis");
      driver.findElement(By.cssSelector(".fa-calendar")).click();
      driver.findElement(By.cssSelector("tr:nth-child(1) > .day:nth-child(1)")).click();
      driver.findElement(By.cssSelector(".btn")).click();
      driver.findElement(By.cssSelector("h2")).click();
      assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Analysis: Afghanistan on 05/01/2022");
    }

    @Test
    public void cacheTest() {
      driver.get("http://localhost:8080/");
      driver.manage().window().setSize(new Dimension(1848, 1053));
      assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Analysis");
      driver.findElement(By.linkText("Cache Statistics")).click();
      driver.findElement(By.cssSelector("h2")).click();
      assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Cache Statistics");
    }

}