package pageObjects;

import factory.BaseClass;
import factory.DriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class CheckoutPage extends BasePage{

    public CheckoutPage(DriverProvider provider){
        super(provider);
    }

    // Modal Xpath
    @FindBy(xpath = "//div[@id='orderModal']//div[@role='document']")
    WebElement modal;

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

    @FindBy(xpath = "//button[normalize-space()='OK']")
    WebElement btnOk;

    @FindBy(xpath = "//a[@class='navbar-brand' and contains(text(),'PRODUCT STORE')]")
    WebElement pageTittle;


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

    public Map<String, String> getOrderDetails() {
        BaseClass.getLogger().info("Checking order details ......");

        WebElement orderDetails = driver.findElement(By.cssSelector("p.lead.text-muted"));
        waitHelper.waitForElementVisible(orderDetails);

        Map<String, String> pDetails = new HashMap<>();

        String allDetails = orderDetails.getText();
        String[] lines = allDetails.split("\\r?\\n");


        for (String line : lines) {
            if (line.contains(":")) {
                String[] parts = line.split(":", 2);
                String key = parts[0].trim();
                String value = parts[1].trim();
                pDetails.put(key, value);
            }
        }
        return pDetails;
    }

    public String clickOnOkButtton (){
        waitHelper.waitForElementVisible(btnOk);
        waitHelper.waitForElementTOClick(btnOk);

        waitHelper.waitForElementVisible(pageTittle);
        return pageTittle.getText();
    }

    public String getMessageFromAlert(){

        waitHelper.waitForAlertToBePresent();
        Alert alert = driver.switchTo().alert();
        String alertTxt = alert.getText();
        alert.accept();
        return  alertTxt;
    }

    public boolean closePurchaseModal(){
        waitHelper.waitForElementVisible(btnCloseModal);
        waitHelper.waitForElementTOClick(btnCloseModal);
        btnCloseModal.click();

        try {
            return waitHelper.waitForElementInvisible(modal);
        } catch (Exception e) {
            BaseClass.getLogger().info("Exception occurs{}", e.getMessage());
            return false;
        }
    }


}
