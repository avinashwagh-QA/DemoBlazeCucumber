package pageObjects;

import factory.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

public class BasePage {

    protected WebDriver driver;
    protected WaitHelper waitHelper;


    public BasePage(DriverProvider driverProvider) {
        this.driver = driverProvider.getDriver();
        PageFactory.initElements(driver,this);
        waitHelper = new WaitHelper(driverProvider);
    }
}
