package stepDefinitions;

import factory.BaseClass;
import factory.DriverProvider;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import javax.inject.Inject;
import java.io.IOException;

public class LoginSteps {

    private HomePage hp;
    private final WebDriver driver;
    private LoginPage lp;

    @Inject
    public LoginSteps(DriverProvider provider) {
        this.driver = provider.getDriver();   // same driver as Hooks
    }

    @Given("The user navigates to application URL and logo is displayed")
    public void the_user_navigates_to_application_url_and_logo_is_displayed() {
        hp = new HomePage(driver);
        hp.pageLoadedLogoDisplayed();
    }

    @When("The user click on Login from Navbar")
    public void the_user_click_on_login_from_navbar() {
        hp.openLoginModal();
    }

    @When("The user enter valid User name as {string} and Password as {string} in the modal")
    public void the_user_enter_valid_user_name_as_and_password_as(String username, String password) {
        lp = new LoginPage(driver);
        lp.setTxtUserName(username);
        lp.setTxtPassword(password);
    }

    @When("The user clicks on login button")
    public void the_user_clicks_on_login_button() {
        lp.clickOnLoginBtn();
    }

    @Then("The User should be successfully logged in and the username displayed webpage")
    public void the_user_should_be_successfully_logged_in_and_the_username_displayed_webpage() throws IOException {
        String actualMessage = hp.getWelcomeMsg();
        String expectedMessage = BaseClass.getProperties().getProperty("username");
        Assert.assertTrue("Welcome David02", actualMessage.contains(expectedMessage));
    }


    @Then("An Alert should be displayed with message {string}")
    public void anAlertShouldBeDisplayedWithMessage(String expectedMsg) {
        String actualMsg = lp.getMessageFromAlert();
        Assert.assertEquals(expectedMsg, actualMsg);
    }

    @Given("The user loged in with valid User name as {string} and Password as {string} in the input")
    public void theUserLoogedInWithValidUserNameAsAndPasswordAsInTheModal(String username, String password) throws InterruptedException {
        lp = new LoginPage(driver);
        lp.loginWithValidCredential(username,password);
    }

    @When("The user clicks on logout button")
    public void theUserClicksOnLogoutButton() {

    }

    @Then("Login link should be visible on the Homepage")
    public void loginLinkShouldBeVisibleOnTheHomepage() {
        Assert.assertTrue("Login Link is not visible", lp.loginDisplayed());
    }



}
