package stepDefinitions;

import factory.DriverProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.CheckoutPage;

import java.util.Map;

public class CheckoutSteps {

    private final DriverProvider driverProvider;
    CheckoutPage chkp;

    public CheckoutSteps(DriverProvider driverProvider) {
        this.driverProvider = driverProvider;
        chkp = new CheckoutPage(driverProvider);
    }

    @And("User the user fill outs the checkout details")
    public void userTheUserFillOutsTheCheckoutDetails(DataTable detailsTable) {

        Map<String, String> data = detailsTable.asMap(String.class, String.class);
        chkp.setName(data.get("Name"));
        chkp.setCountryName(data.get("Country"));
        chkp.setInputCity(data.get("City"));
        chkp.setCardDetails(data.get("Credit Card"));
        chkp.setMonth(data.get("Month"));
        chkp.setYear(data.get("Year"));
    }

    @Then("A Confirmation message {string} should be displayed")
    public void aConfirmationMessageShouldBeDisplayed(String expectedMessage) {

        String actual = chkp.successCheckoutMsg();
        Assert.assertEquals(expectedMessage, actual);
    }

    @And("The Order details should include")
    public void theOrderDetailsShouldInclude(DataTable expectedDetails) {

        Map<String, String> expectedData = expectedDetails.asMap(String.class, String.class);
        Map<String, String> actualData = chkp.getOrderDetails();

        Assert.assertFalse("Order details are empty!", actualData.isEmpty());

        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String field = entry.getKey();
            String expValue = entry.getValue();

            String actualValue = actualData.get(field);

            Assert.assertNotNull("Field '" + field + "' not found in order details! Actual map: " + actualData, actualValue);
            Assert.assertTrue("Value mismatch for '" + field + "': expected '" + expValue + "but found '" + actualValue + "'", actualValue.contains(expValue));

        }
    }

    @And("Clicking on ok button then user should be navigated to Home Page and title should be {string}")
    public void clickingOnOkButtonThenUserShouldBeNavigatedToHomePageAndTitleShouldBe(String expectTitle) {
        String actualTitle = chkp.clickOnOkButtton();
        Assert.assertEquals(expectTitle, actualTitle);
    }

    @Then("Alert should be displayed with message {string}")
    public void alertShouldBeDisplayedWithMessage(String expectedAlertMsg) {

        String actualMsg = chkp.getMessageFromAlert();
        Assert.assertEquals(expectedAlertMsg, actualMsg);
    }

    @Then("User clicks on close button then modal should be closed")
    public void userClicksOnCloseButtonThenModalShouldBeClosed() {

        boolean modalStatus = chkp.closePurchaseModal();
        Assert.assertTrue("Modal did not closed", modalStatus);
    }
}
