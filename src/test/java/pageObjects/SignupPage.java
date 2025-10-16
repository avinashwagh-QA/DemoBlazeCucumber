package pageObjects;

import factory.DriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends BasePage {
    public SignupPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    // sign up link from Home page
    @FindBy(xpath = "//a[@id='signin2' and text()='Sign up']")
    WebElement lnkSignUp;

    //Username and password input
    @FindBy(xpath = "//input[@id='sign-username']")
    WebElement inpUserName;

    @FindBy(xpath = "//input[@id='sign-password']")
    WebElement inputPassword;

    // Signup button
    @FindBy(xpath = "//button[normalize-space()='Sign up']")
    WebElement btnSignUp;

    //Modal
    @FindBy(xpath = "//div[@id='signInModal']//div[@role='document']")
    WebElement modal;

    public void clickOnSignUpLink() {
        waitHelper.waitForPageToBeLoad();
        waitHelper.waitForElementVisible(lnkSignUp);
        waitHelper.waitForElementToBeClick(lnkSignUp);
        lnkSignUp.click();
    }

    public void setUserName(String userName) {
        waitHelper.waitForElementVisible(modal);

        waitHelper.waitForElementToBeClick(inpUserName);
        inpUserName.sendKeys(userName);
    }

    public void setPassword(String password) {
        waitHelper.waitForElementToBeClick(inputPassword);
        inputPassword.sendKeys(password);
    }

    public void clickOnSignUpButton() {
        waitHelper.waitForElementToBeClick(btnSignUp);
        btnSignUp.click();
    }

    public String getConfirmationFromAlert() {
        waitHelper.waitForAlertToBePresent();
        Alert alert = driver.switchTo().alert();
        String alertTxt = alert.getText();
        alert.accept();
        return alertTxt;
    }

}
