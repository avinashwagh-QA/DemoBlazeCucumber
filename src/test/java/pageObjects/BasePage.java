package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

public class BasePage {

    protected WebDriver driver;
    protected WaitHelper waitHelper;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        waitHelper = new WaitHelper(driver);
    }
}
