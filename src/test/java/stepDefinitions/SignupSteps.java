package stepDefinitions;

import factory.DriverProvider;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pageObjects.SignupPage;

public class SignupSteps {

    private final DriverProvider driverProvider;// Injected DI managed driver Provider
    private SignupPage sp;

    public SignupSteps(DriverProvider driverProvider) {
        this.driverProvider = driverProvider;
    sp = new SignupPage(driverProvider);
    }

    @Given("The user clicks on sign up")
    public void the_user_clicks_on_sign_up() {
        sp.clickOnSignUpLink();
    }

    @When("The user enter valid User name as {string} and Password as {string} in the sing up modal")
    public void theUserEnterValidUserNameAsAndPasswordAsInTheSingUpModal(String userName, String password) {
        sp.setUserName(userName);
        sp.setPassword(password);
    }

    @And("The user clicks on sign up button")
    public void theUserClicksOnSignUpButton() {
        sp.clickOnSignUpButton();
    }

    @Then("An alert should be displayed with message {string}")
    public void anAlertShouldBeDisplayedWithMessage(String expectedMsg) {
        String actualMsg = sp.getConfirmationFromAlert();

        Assert.assertEquals("Alert Message does not matched", expectedMsg, actualMsg);
    }


    @Then("An alert should be displayed an error message {string}")
    public void anAlertShouldBeDisplayedAnErrorMessage(String expectedErrorMsg) {
        String actualErrorMsg = sp.getConfirmationFromAlert();

        Assert.assertEquals("Error message does not matched ", expectedErrorMsg,actualErrorMsg);
    }
}
