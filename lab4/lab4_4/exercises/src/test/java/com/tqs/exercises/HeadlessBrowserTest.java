package com.tqs.exercises;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.jupiter.api.Test;

// Generated by Selenium IDE
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
 
@ExtendWith(SeleniumJupiter.class)
public class HeadlessBrowserTest {

    private HtmlUnitDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    @BeforeEach
    public void setUp() {
      driver = new HtmlUnitDriver();
      js = (JavascriptExecutor) driver;
      vars = new HashMap<String, Object>();
    }
    
    @AfterEach
    public void tearDown() {
      driver.quit();
    }
    
    @Test
    public void testeAlineaA() {
      driver.get("https://blazedemo.com/");
      driver.manage().window().setSize(new Dimension(1848, 1053));
      driver.findElement(By.cssSelector("h1")).click();
      vars.put("texth1", driver.findElement(By.cssSelector("h1")).getText());
      assertEquals(vars.get("texth1").toString(), "Welcome to the Simple Travel Agency!");
      driver.findElement(By.cssSelector(".btn-primary")).click();
      driver.findElement(By.cssSelector("h3")).click();
      vars.put("texth3", driver.findElement(By.cssSelector("h3")).getText());
      assertEquals(vars.get("texth3").toString(), "Flights from Paris to Buenos Aires:");
      driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
      driver.findElement(By.cssSelector("h2")).click();
      vars.put("texth2", driver.findElement(By.cssSelector("h2")).getText());
      assertEquals(vars.get("texth2").toString(), "Your flight from TLV to SFO has been reserved.");
      driver.findElement(By.id("inputName")).click();
      driver.findElement(By.id("inputName")).sendKeys("Eva");
      driver.findElement(By.id("address")).sendKeys("123 Main");
      driver.findElement(By.id("city")).sendKeys("Aveiro");
      driver.findElement(By.id("state")).sendKeys("Portugal");
      driver.findElement(By.id("zipCode")).sendKeys("1234-567");
      driver.findElement(By.id("creditCardNumber")).click();
      driver.findElement(By.id("creditCardNumber")).click();
      driver.findElement(By.id("creditCardNumber")).sendKeys("12345678");
      driver.findElement(By.id("creditCardMonth")).click();
      driver.findElement(By.id("creditCardMonth")).sendKeys("12");
      driver.findElement(By.id("creditCardYear")).click();
      driver.findElement(By.id("creditCardYear")).sendKeys("2018");
      driver.findElement(By.id("nameOnCard")).click();
      driver.findElement(By.id("nameOnCard")).sendKeys("Eva");
      driver.findElement(By.cssSelector(".checkbox")).click();
      driver.findElement(By.cssSelector(".btn-primary")).click();
      driver.findElement(By.cssSelector("h1")).click();
      vars.put("texth1", driver.findElement(By.cssSelector("h1")).getText());
      assertEquals(vars.get("texth1").toString(), "Thank you for your purchase today!");
      assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    }
}