package pageObjects;

import factory.BaseClass;
import factory.DriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    public ProductPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    // Element for product name
    @FindBy(css = ".name")
    WebElement productNameLabel;

    //Element for Add to cart Button
    @FindBy(xpath = "//a[normalize-space()='Add to cart']")
    WebElement btnAddToCart;

    //Element for Home tab from product page
    @FindBy(xpath = "//li[@class='nav-item active']//a[@class='nav-link']")
    WebElement lnkHome;

    // Method for to get product name
    public String getProductLabel() {
        waitHelper.waitForElementVisible(productNameLabel);
        return productNameLabel.getText();
    }

    // Method for add to cart button
    public void clickOnAddToCart() {
        waitHelper.waitForElementVisible(btnAddToCart);
        waitHelper.waitForElementTOClick(btnAddToCart);
        btnAddToCart.click();
    }

    // To verify Product added from product page
    public String productCartSuccessMsg() {
        try {
            waitHelper.waitForAlertToBePresent();
            Alert alert = driver.switchTo().alert();
            String alertTXT = alert.getText();
            alert.accept();
            BaseClass.getLogger().info("Alert found...");
            return alertTXT;

        } catch (Exception e) {

            BaseClass.getLogger().info("Alert not found{}", e.getMessage());
            return null;
        }

    }

    // Navigate to Home
    public void navigateToHome() {
        lnkHome.click();
    }

}
