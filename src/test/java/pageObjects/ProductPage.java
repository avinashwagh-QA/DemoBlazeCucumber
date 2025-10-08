package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".name")
    WebElement productNameLabel;


    public String getProductLabel ()
    {
        waitHelper.waitForElementVisible(productNameLabel);
        return productNameLabel.getText();
    }


    
}
