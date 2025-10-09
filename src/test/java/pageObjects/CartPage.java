package pageObjects;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //link for cart
    @FindBy(xpath = "//a[@id='cartur']")
    WebElement lnkCart;

    //Product table
    @FindBy(xpath = "//table[@class='table table-bordered table-hover table-striped']//tbody//tr")
    WebElement productTable;

    //Total price
    @FindBy(xpath = "//h3[@id='totalp']")
    WebElement totalPrice;

    public void clickOnCartPage() {
        waitHelper.waitForElementVisible(lnkCart);
        waitHelper.waitForElementTOClick(lnkCart);
        lnkCart.click();
    }

    public boolean isProductPresent(String productName) {

        waitHelper.waitForElementVisible(productTable);

        int totalPage = 1;
        int rows = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr")).size();

        for (int r = 1; r < rows; r++) {

            String name = driver.findElement(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr[" + r + "]//td[2]")).getText();

            BaseClass.getLogger().info("Product found in raw{}{}", r, name);

            if (name.equalsIgnoreCase(productName)) {
                BaseClass.getLogger().info("Product found in the cart{}", productName);
                return true;
            }
        }

        BaseClass.getLogger().info("Product not found in cart{}", productName);
        return false;

    }

    public boolean isProductPriceCorrect(String productName,String productPrice)
    {
        waitHelper.waitForElementVisible(productTable);

        int totalPage = 1;
        int rows = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr")).size();

        for (int r =1; r< rows; r++) {
            String name = driver.findElement(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr[" + r + "]//td[2]")).getText();
            String price = driver.findElement(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr[" + r + "]//td[3]")).getText();

            if (name.equalsIgnoreCase(productName) && price.equalsIgnoreCase(productPrice)) {
                BaseClass.getLogger().info("Product found in the cart with correct price {} {}", productName, productPrice);
                return true;
            }
        }

        BaseClass.getLogger().info("Product price does not match {} {}", productName, productPrice);
        return false;
        }


        public String isTotalPriceCorrect(){

        waitHelper.waitForElementVisible(totalPrice);
            return totalPrice.getText();
    }


public boolean isProductRemoved (String prpductName) {
    waitHelper.waitForElementVisible(productTable);

    int totalPage = 1;
    List <WebElement> web = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr"));
    String rows = web.size();


    for (int r =1; r< rows; r++) {
        String name = driver.findElement(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr[" + r + "]//td[2]")).getText();

        if(name.equalsIgnoreCase(prpductName)){

            driver.findElement(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr[" + r + "]//td[3]")).click();

            waitHelper.WaitForElementNotVisible(name);

        }




}







}

