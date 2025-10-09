package pageObjects;

import factory.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".name")
    WebElement productNameLabel;

    @FindBy(xpath = "//a[normalize-space()='Add to cart']")
    WebElement btnAddToCart;


    public String getProductLabel ()
    {
        waitHelper.waitForElementVisible(productNameLabel);
        return productNameLabel.getText();
    }

    public void clickOnAddToCart (){
        waitHelper.waitForElementTOClick(btnAddToCart);
        btnAddToCart.click();
    }

    public String productCartSuccessMsg (){
        try {
            waitHelper.waitForAlerttoBePresent();
            Alert alert = driver.switchTo().alert();
            String alertTXT = alert.getText();
            alert.accept();
            BaseClass.getLogger().info("Alert found...");
            return alertTXT;

        } catch (Exception e){

            BaseClass.getLogger().info("Alert not found{}", e.getMessage());
            return null;
        }

    }


    
}
