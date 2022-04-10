package com.tqs.blazedemo.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.How;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SeleniumJupiter.class)
public class PurchaseApplyPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/h2")
    WebElement heading;
 
    @FindBy(id= "inputName")
    WebElement name;
 
    @FindBy(id = "address")
    WebElement address;
 
    @FindBy(id = "city")
    WebElement city;
 
    @FindBy(id = "state")
    WebElement state;
 
    @FindBy(id = "zipCode")
    WebElement zipCode;
 
    @FindBy(id ="cardType")
    WebElement cardType;
 
    @FindBy(id ="creditCardNumber")
    WebElement creditCardNumber;

    @FindBy(id ="creditCardMonth")
    WebElement creditCardMonth;

    @FindBy(id ="creditCardYear")
    WebElement creditCardYear;

    @FindBy(id ="nameOnCard")
    WebElement nameCard;

    @FindBy( xpath = "/html/body/div[2]/form/div[11]/div/input")
    private WebElement purchaseFlightButton;

    //Constructor
    public PurchaseApplyPage(WebDriver driver){
        this.driver=driver;
 
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }  
    
    public void clickOnPurchaseFlightButton(){
        purchaseFlightButton.click();
    }

    public void setName(String nome) {
        name.clear();
        name.sendKeys(nome);
    }

    public void setAddress(String endereco) {
        address.clear();
        address.sendKeys(endereco);
    }

    public void setCity(String cidade) {
        city.clear();
        city.sendKeys(cidade);
    }

    public void setState(String estado) {
        state.clear();
        state.sendKeys(estado);
    }

    public void setZipCode(String codigoPostal) {
        zipCode.clear();
        zipCode.sendKeys(codigoPostal);
    }

    public void setCardType(String tipoCartao) {
        cardType.clear();
        cardType.sendKeys(tipoCartao);
    }

    public void setCreditCardNumber(String numCartaoCredito) {
        creditCardNumber.clear();
        creditCardNumber.sendKeys(numCartaoCredito);
    }

    public void setCreditCardMonth(String mesCartaoCredito) {
        creditCardMonth.clear();
        creditCardMonth.sendKeys(mesCartaoCredito);
    }

    public void setCreditCardYear(String anoCartaoCredito) {
        creditCardYear.clear();
        creditCardYear.sendKeys(anoCartaoCredito);
    }

    public void setNameOnCard(String nameOnCard) {
        nameCard.clear();
        nameCard.sendKeys(nameOnCard);
    }

    public boolean isPageOpened(){
        //Assertion
        return heading.getText().contains("Your flight from TLV to SFO has been reserved.");
    }
}
