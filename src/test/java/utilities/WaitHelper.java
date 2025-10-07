package utilities;

import factory.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private WebDriver driver;
    private static final int default_Time = 20;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    /* Method for element to be visible */
    public WebElement waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /* Method for element to be clickable */
    public WebElement waitForElementTOClick(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            BaseClass.getLogger().info ("Timeout Waiting for element :"+ element.toString());
            return null;
        }
    }

    /* Method for wait until text contains */
    public Boolean waitForTitleContains(String titlePart) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
        return wait.until(ExpectedConditions.titleContains(titlePart));
    }

}
