package pageObjects;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.WaitHelper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //link for cart
    @FindBy(xpath = "//a[@class='nav-link' and contains(text(),'Cart')]")
    WebElement lnkCart;

    //Product table
    @FindBy(xpath = "//table[@class='table table-bordered table-hover table-striped']//tbody//tr")
    WebElement productTable;

    //Total price
    @FindBy(xpath = "//h3[@id='totalp']")
    WebElement totalPrice;

    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    WebElement btnPlaceOrder;

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


    public boolean isProductRemoved(String productName) {

        waitHelper.waitForElementVisible(productTable); //wait for the cart table to visible

        // Element for total number of rows
        List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr"));

        // Loop through all the products and find the product
        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            if (cols.size() < 4) {
                continue;
            }

            String name = cols.get(1).getText().trim();

            if (name.equalsIgnoreCase(productName)) {

                // Click on the delete button from row 3
                WebElement deleteBtn = cols.get(3).findElement(By.tagName("a"));

                    deleteBtn.click();
                    waitHelper.waitForTextToDisappear(productTable, productName);
                    break;

            }
        }

        // Element for total number of rows
        List<WebElement> updatedRows = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr"));

        // verify the product removed from the table
        for (WebElement row : updatedRows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            if (cols.size() < 4) {
                continue;
            }

            String pr2_name = cols.get(1).getText().trim();

            if (pr2_name.equalsIgnoreCase(productName)) {
                return false; // product still remains
            }
        }
        return true; // product successfully removed
    }


    public boolean areProductPresent(Collection<String> productNames) {

        waitHelper.waitForElementVisible(productTable);
        // Total numbers of raws
        int rows = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr")).size();

        HashSet <String> productsToCheck = new HashSet<>(productNames); //creating Has set for list of  product to be check
        HashSet <String> foundProducts = new HashSet<>(); //Creating Has set if the products found

        for (int r = 1; r < rows; r++) {
            try {

                String name1 = driver.findElement(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr[" + r + "]//td[2]")).getText();

                if (productsToCheck.contains(name1)) {
                    foundProducts.add(name1); // add product to list
                    BaseClass.getLogger().info("Product found in the row {}{}", r, name1);
                    return true;
                }
            }catch (Exception e){
            BaseClass.getLogger().info(e.getMessage());}
        }

        productsToCheck.removeAll(foundProducts);
        if(!productsToCheck.isEmpty()){
            BaseClass.getLogger().info("Products not found in cart{}", productsToCheck);
            return false;
        }

        return true; //All products found

    }


    public boolean areTotalPriceEqualProductPrice (){

        waitHelper.waitForElementVisible(productTable);

        String tp =totalPrice.getText();
        int total =Integer.parseInt(tp);
        int productSum =0;

        List <WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table table-bordered table-hover table-striped')]//tr"));

        for(int r=1; r< rows.size();r++){

            List<WebElement> cols = rows.get(r).findElements(By.tagName("td"));

            if(cols.size()<3) continue;

            String priceStr = cols.get(2).getText();
            if (!priceStr.isEmpty())
            {
                productSum += Integer.parseInt(priceStr);
            }
        }
        return productSum == total;
    }

    public int getTotalPrice(){
      return   Integer.parseInt(totalPrice.getText());
    }

    public void clickOnPlaceOrder(){
        waitHelper.waitForElementTOClick(btnPlaceOrder);
        btnPlaceOrder.click();
    }






}


