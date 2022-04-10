package com.tqs.blazedemo.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SeleniumJupiter.class)
public class ReservePage {
   private WebDriver driver;

   @FindBy(xpath = "/html/body/div[2]/h3")
   private WebElement heading;

   @FindBy( xpath = "/html/body/div[2]/table/tbody/tr[1]/td[1]/input")
   private WebElement chooseFlightButton;

   //Constructor
   public ReservePage (WebDriver driver){
       this.driver=driver;

       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

   //We will use this boolean for assertion. To check if page is opened
   public boolean isPageOpened(){
       return heading.getText().contains("Flights from Paris to Buenos Aires:");
   }

   public void clikOnFlightButton(){
       chooseFlightButton.click();
   }

    
}
