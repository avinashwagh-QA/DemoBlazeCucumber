package stepDefinitions;

import factory.DriverProvider;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.CartPage;
import pageObjects.ProductPage;

import java.util.Arrays;

public class AddToCartSteps {

    private final WebDriver driver;
    private ProductPage prp;
    private CartPage cp;


    public AddToCartSteps(DriverProvider provider) {
        this.driver = provider.getDriver();
    }

    @When("Click on the add to cart button")
    public void click_on_the_add_to_cart_button() {
       prp = new ProductPage(driver);
       prp.clickOnAddToCart();

    }

    @And("Success message should be displayed on the page {string}")
    public void successMessageShouldBeDisplayedOnThePage(String expectedMsg) {
      String actualMsg = prp.productCartSuccessMsg();
        Assert.assertEquals(expectedMsg, actualMsg);
    }


    @Then("The product {string} should be successfully added to the cart")
    public void theProductShouldBeSuccessfullyAddedToTheCart(String productName) {

        cp = new CartPage(driver);
        cp.clickOnCartPage();
        boolean prd = cp.isProductPresent(productName);
        Assert.assertTrue(prd);
    }

    @And("The price of the product {string} in the cart should be {string}")
    public void thePriceOfTheProductInTheCartShouldBe(String productName, String productPrice) {
        cp = new CartPage(driver);
        cp.clickOnCartPage();
        boolean prd_price =cp.isProductPriceCorrect(productName,productPrice);
        Assert.assertTrue(prd_price);

    }

    @And("The total price in the cart should be equal to the product price {string}")
    public void theTotalPriceInTheCartShouldBeEqualToTheProductPrice(String expectedTotalPrice) {

        String actualTotalPrice = cp.isTotalPriceCorrect();
        Assert.assertEquals("Price Does not match", expectedTotalPrice, actualTotalPrice);

    }

    @Then("The user deletes the product {string} from the cart and it should be removed")
    public void theUserDeletesTheProductFromTheCartAndItShouldBeRemoved(String productName) {

        cp = new CartPage(driver);
        cp.clickOnCartPage();

        boolean act_product = cp.isProductRemoved(productName);
        Assert.assertTrue("Product not removed" + productName, act_product);

    }

    @And("User navigates to Home Page")
    public void userNavigatesToHomePage() {
        prp.navigateToHome();
    }

    @Then("Cart should be displayed both {string} and {string}")
    public void cartShouldBeDisplayedBothAnd(String productName1, String productName2) {
        cp = new CartPage(driver);
        cp.areProductPresent(Arrays.asList(productName1,productName2));


    }

    @And("The total price in the cart should be equal to the both product price")
    public void theTotalPriceInTheCartShouldBeEqualToTheBothProductPrice() {
    }
}
