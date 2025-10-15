package pageObjects;

import factory.DriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    private DriverProvider driverProvider;

    public LoginPage(DriverProvider driverProvider) {
        super(driverProvider);
        this.driverProvider = driverProvider;
    }

    //Element for UserName
    @FindBy(xpath = "//input[@id='loginusername']")
    WebElement txtUserName;

    //Element for Password
    @FindBy(xpath = "//input[@id='loginpassword']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    WebElement btnLogin;

    //Methods for set Username and Password
    public void setTxtUserName(String userName) {
        waitHelper.waitForElementTOClick(txtUserName);
        txtUserName.sendKeys(userName);
    }

    public void setTxtPassword(String password) {
        waitHelper.waitForElementTOClick(txtPassword);
        txtPassword.sendKeys(password);
    }

    public void clickOnLoginBtn() {
        btnLogin.click();
    }

    public boolean loginDisplayed() {
        return btnLogin.isDisplayed();
    }

    public String getMessageFromAlert() {
        waitHelper.waitForAlertToBePresent();
        Alert alert = driver.switchTo().alert();
        String alertTxt = alert.getText();
        alert.accept();
        return alertTxt;
    }

    public void loginWithValidCredential(String username, String password) throws InterruptedException {
        HomePage hmp = new HomePage(driverProvider);
        hmp.openLoginModal();
        Thread.sleep(5000);
        setTxtUserName(username);
        setTxtPassword(password);
        clickOnLoginBtn();
    }

}
