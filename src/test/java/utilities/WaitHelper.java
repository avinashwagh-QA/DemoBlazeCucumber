package utilities;

import factory.BaseClass;
import factory.DriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitHelper {

    private WebDriver driver;
    private static final int default_Time = 20;
    WebDriverWait wait;

    public WaitHelper(DriverProvider provider) {
        this.driver = provider.getDriver();
         wait= new WebDriverWait(driver, Duration.ofSeconds(default_Time));
    }

    /* Method for element to be visible */
    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /* Method for element to be invisible */
    public Boolean waitForElementInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }


    /* Method for multiple element to visible */
    public void waitForElementsToVisible(List <WebElement> elements){
         for (WebElement element : elements){
             wait.until(ExpectedConditions.visibilityOf(element));
         }
    }

    /* Method for element to be clickable */
    public WebElement waitForElementToBeClick(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            BaseClass.getLogger().info("Timeout Waiting for element :{}", element.toString());
            return null;
        }
    }

    public void waitForAlertToBePresent(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(default_Time));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForTextToDisappear(WebElement productTable, String text) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + text + "')]")));
        } catch (TimeoutException e) {
            BaseClass.getLogger().info("Timeout waiting for text to disappear: {}", text);
        }
    }

    public void waitForPageToBeLoad(){
        wait.until(webDriver -> ((JavascriptExecutor) driver)
                .executeScript("Return document.ready state").equals("complete"));
    }




}

