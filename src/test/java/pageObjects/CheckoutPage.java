package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    //Name
    @FindBy(xpath = "//input[@id='name']")
    WebElement inpName;

    @FindBy(xpath = "//input[@id='country']")
    WebElement inpCountry;

    @FindBy(xpath = "//input[@id='city']")
    WebElement inpCity;

    @FindBy(xpath = "//input[@id='card']")
    WebElement inpCard;

    @FindBy(xpath = "//input[@id='month']")
    WebElement inpMonth;

    @FindBy(xpath = "//input[@id='year']")
    WebElement inpYear;

    @FindBy(xpath = "//button[contains(text(), 'Purchase')]")
    WebElement btnPurchase;

    @FindBy(xpath = "//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']")
    WebElement btnCloseModal;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//*[contains(text(),'Thank you for your purchase!')]")
    WebElement successCheckOutMsg;


    public void setName (String name){
        waitHelper.waitForElementVisible(inpName);
        inpName.sendKeys(name);
    }
    public void setCountryName (String countryName){
        waitHelper.waitForElementVisible(inpCountry);
        inpCountry.sendKeys(countryName);
    }

    public void setInputCity(String city){
        waitHelper.waitForElementVisible(inpCity);
        inpCity.sendKeys(city);
    }

    public void setCardDetails(String cardNo){
        waitHelper.waitForElementVisible(inpCard);
        inpCard.sendKeys(cardNo);
    }

    public void setMonth (String month){
        waitHelper.waitForElementVisible(inpMonth);
        inpMonth.sendKeys(month);
    }

    public void setYear(String year){
        waitHelper.waitForElementVisible(inpYear);
        inpYear.sendKeys(year);
    }

    public void clickOnPurchase(){
        waitHelper.waitForElementTOClick(btnPurchase);
        btnPurchase.click();
    }

    public String successCheckoutMsg(){
        waitHelper.waitForElementVisible(successCheckOutMsg);
        return successCheckOutMsg.getText();

    }





}
