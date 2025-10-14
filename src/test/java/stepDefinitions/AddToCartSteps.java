package stepDefinitions;

import factory.DriverProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddToCartSteps {

    private final WebDriver driver;
    private ProductPage prp;
    private CartPage cp;
    HomePage hp;
    CheckoutPage chkp;


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
        boolean prd_price = cp.isProductPriceCorrect(productName, productPrice);
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
        Assert.assertTrue("Product not removed" + productName, act_product);
    }

    @And("User navigates to Home Page")
    public void userNavigatesToHomePage() {
        prp.navigateToHome();
    }

    @Then("Cart should be displayed both {string} and {string}")
    public void cartShouldBeDisplayedBothAnd(String productName1, String productName2) {
        cp = new CartPage(driver);
        cp.clickOnCartPage();
        boolean arePresent = cp.areProductPresent(Arrays.asList(productName1, productName2));
        Assert.assertTrue("Not all product are peresent in the cart", arePresent);
    }

    @And("The total price in the cart should be equal to the both product price")
    public void theTotalPriceInTheCartShouldBeEqualToTheBothProductPrice() {
        cp = new CartPage(driver);
        boolean totalPriceCorrect = cp.areTotalPriceEqualProductPrice();
        Assert.assertTrue("The Total product price are not equal", totalPriceCorrect);
    }

    @When("The user adds the following products to cart")
    public void theUserAddsTheFollowingProductsToCart(DataTable table) {

        // Data table (Keys and values) stored in the list of map
        List<Map<String, String>> Products = table.asMaps(String.class, String.class);

        //Looping through each product adding product to cart
        for (Map<String, String> product : Products) {

            // Reading product from map
            String name = product.get("Product Name");
            hp = new HomePage(driver);

            hp.clickProductbyName(name); // Clicking on product by finding its name
            prp = new ProductPage(driver);
            prp.clickOnAddToCart(); // Click on add to cart button

            prp.productCartSuccessMsg(); // getting the message fron alert
            prp.navigateToHome(); // navigating to home for the second product
        }

    }

    @Then("User navigates to Cart Page then Cart should be displayed all the product")
    public void theCartShouldBeDisplayedAllTheProduct(DataTable expectedtable) {
        cp = new CartPage(driver);
        cp.clickOnCartPage();
        List<String> expectedProducts = (List<String>) expectedtable.asMaps().stream()
                .map(row -> row.get("Product Name"))
                .collect(Collectors.toList());
        Assert.assertTrue(cp.areProductPresent(expectedProducts));
    }

    @And("The total price in the cart should be equal to the {string}")
    public void theTotalPriceInTheCartShouldBeEqualToThe(String expectedTotal) {
        int expected = Integer.parseInt(expectedTotal);
        int actual = cp.getTotalPrice();
        Assert.assertEquals(expected, actual);

    }

    @And("The user click on the Place order")
    public void theUserClickOnThePlaceOrder() {
        cp = new CartPage(driver);
        cp.clickOnPlaceOrder();
    }

    @And("User confirms the purchase")
    public void userConfirmsThePurchase() {
        chkp = new CheckoutPage(driver);
        chkp.clickOnPurchase();
    }


}


