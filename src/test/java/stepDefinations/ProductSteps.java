package stepDefinations;

import factory.DriverProvider;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.ProductPage;

public class ProductSteps {

    WebDriver driver;
    HomePage hp;
    ProductPage prp;


    public ProductSteps (DriverProvider provider){
        this.driver =  provider.getDriver();
    }

    @Then("Count the number of products displayed on homepage")
    public void count_the_number_of_products_displayed_on_homepage() {
        hp = new HomePage(driver);
        hp.getTotalProductCount();
    }

    @Then("Display all the product names on the homepage")
    public void display_all_the_product_names_on_the_homepage() {
       hp.getAllproductNames();

    }

    @When("The user click on the product {string}")
    public void the_user_click_on_the_product(String product) {
        hp = new HomePage(driver);
        hp.clickProductbyName(product);
    }

    @Then("Product detail page should be displayed {string}")
    public void product_detail_page_should_be_displayed(String expectedProductName) {
       prp = new ProductPage(driver);

       String actualProductName = prp.getProductLabel();

        Assert.assertEquals("Product detail page shows the wrong product", expectedProductName, actualProductName);
    }




}
