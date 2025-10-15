package stepDefinitions;

import factory.DriverProvider;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pageObjects.HomePage;
import pageObjects.ProductPage;

public class ProductSteps {

    HomePage hp;
    ProductPage prp;


    public ProductSteps (DriverProvider driverProvider){
        hp = new HomePage (driverProvider);
        prp = new ProductPage(driverProvider);

    }

    @Then("Count the number of products displayed on homepage")
    public void count_the_number_of_products_displayed_on_homepage() {
        hp.getTotalProductCount();
    }

    @Then("Display all the product names on the homepage")
    public void display_all_the_product_names_on_the_homepage() {
       hp.getAllproductNames();

    }

    @When("The user click on the product {string}")
    public void the_user_click_on_the_product(String product) {
        hp.clickProductbyName(product);
    }

    @Then("Product detail page should be displayed {string}")
    public void product_detail_page_should_be_displayed(String expectedProductName) {
       String actualProductName = prp.getProductLabel();
        Assert.assertEquals("Product detail page shows the wrong product", expectedProductName, actualProductName);
    }




}
