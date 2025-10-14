package utilities;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    /* Method for element to be invisible */
    public Boolean waitForElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }



    /* Method for multiple element to visible */
    public void waitForElementsToVisible(List <WebElement> elements){
        WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
         for (WebElement element : elements){
             Wait.until(ExpectedConditions.visibilityOf(element));
         }
    }

    /* Method for element to be clickable */
    public WebElement waitForElementTOClick(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            BaseClass.getLogger().info("Timeout Waiting for element :"+ element.toString());
            return null;
        }
    }

    /* Method for wait until text contains */
    public Boolean waitForTitleContains(String titlePart) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(default_Time));
        return wait.until(ExpectedConditions.titleContains(titlePart));
    }

    public void waitForAlerttoBePresent(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(default_Time));
        wait.until(ExpectedConditions.alertIsPresent());
    }


    public void waitForTextToDisappear(WebElement productTable, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + text + "')]")));
        } catch (TimeoutException e) {
            BaseClass.getLogger().info("Timeout waiting for text to disappear: " + text);
        }
    }


    public void waitForElementToDisappear(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.stalenessOf(element)); // waits until element is removed from DOM
        } catch (TimeoutException e) {
            BaseClass.getLogger().info("Timeout waiting for element to disappear: " + element);
        }
    }


}

