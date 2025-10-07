package pageObjects;

import factory.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@id='nava']//img")
    WebElement logo;

    //Element for Login
    @FindBy(id = "login2")
    WebElement linkLogin;

    //Element for UserName
    @FindBy(xpath = "//input[@id='loginusername']")
    WebElement txtUserName;

    //Element for Password
    @FindBy(xpath = "//input[@id='loginpassword']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    WebElement btnLogin;

    @FindBy(xpath = "//a[@id='nameofuser']")
    WebElement welcomeMsg;

    @FindBy(xpath = "//a[@id='logout2']")
    WebElement linkLogout;


public void pageLoadedLogoDisplayed ( )
{
    waitHelper.waitForElementVisible(logo);
    boolean lg= logo.isDisplayed();
    BaseClass.getLogger().info("The Page is loaded completely and Logo is displayed " + lg);
}


    //Method to Open login modal
    public void openLoginModal() {
        if (linkLogin != null) {
            waitHelper.waitForElementTOClick(linkLogin);
            linkLogin.click();
        } else {
            BaseClass.getLogger().info("Link to login not found");
        }

    }

    //Methods for set Username and Password
    public void setTxtUserName(String userName) {
        waitHelper.waitForElementVisible(txtUserName);
        txtUserName.sendKeys(userName);
    }

    public void setTxtPassword(String password) {
        waitHelper.waitForElementVisible(txtPassword);
        txtPassword.sendKeys(password);
    }

    public void clickOnLoginBtn() {
        btnLogin.click();
    }

    public String getWelcomeMsg ()
    {
        waitHelper.waitForElementVisible(welcomeMsg);
        return welcomeMsg.getText();
    }

    public void setLinkLogout(){
        waitHelper.waitForElementVisible(linkLogout);
        linkLogout.click();
    }

}
