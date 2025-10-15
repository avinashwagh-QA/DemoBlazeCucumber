package pageObjects;

import factory.BaseClass;
import factory.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    @FindBy(xpath = "//a[@id='nava']//img")
    WebElement logo;

    //Element for Login
    @FindBy(id = "login2")
    WebElement linkLogin;

    @FindBy(xpath = "//div[@id='logInModal']//div[@class='modal-content']")
    WebElement loginModal;

    @FindBy(xpath = "//a[@id='nameofuser']")
    WebElement welcomeMsg;

    @FindBy(xpath = "//a[@id='logout2']")
    WebElement linkLogout;

    @FindBy(xpath = "//div[@class='card-block']//h4[@class='card-title']//a")
    List<WebElement> productTitle;  // All the product tile from current page

    public void pageLoadedLogoDisplayed() {
        waitHelper.waitForElementVisible(logo);
        boolean lg = logo.isDisplayed();
        BaseClass.getLogger().info("The Page is loaded completely and Logo is displayed " + lg);
    }

    //Method to Open login modal
    public void openLoginModal() {
        // Check if modal is already displayed
        if (loginModal.isDisplayed()) {
            BaseClass.getLogger().info("Login modal already open, skipping click");
            return; // do not click navbar link
        }
        if (linkLogin != null) {
            waitHelper.waitForElementTOClick(linkLogin);
            linkLogin.click();
            waitHelper.waitForElementVisible(loginModal);
        } else {
            BaseClass.getLogger().info("Link to login not found");
        }

    }

    public String getWelcomeMsg() {
        waitHelper.waitForElementVisible(welcomeMsg);
        return welcomeMsg.getText();
    }

    public void setLinkLogout() {
        waitHelper.waitForElementVisible(linkLogout);
        linkLogout.click();
    }

    public int getTotalProductCount() {
        waitHelper.waitForElementsToVisible(productTitle);
        return productTitle.size();
    }

    public List<String> getAllproductNames() {
        waitHelper.waitForElementsToVisible(productTitle);

        List<String> names = new ArrayList<>();
        for (WebElement p : productTitle) {
            names.add(p.getText());
        }
        return names;
    }

    public boolean clickProductbyName(String productName) {
        waitHelper.waitForElementsToVisible(productTitle);
        boolean found = false;

        for (WebElement product : productTitle) {
            if (product.getText().equalsIgnoreCase(productName)) {
                waitHelper.waitForElementTOClick(product);
                product.click();
                found = true;
                break;
            }
        }
        return found;
    }






}
