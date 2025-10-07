package hooks;

import factory.BaseClass;
import factory.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class Hooks {

    WebDriver driver;
    Properties properties;


    // PicoContainer will inject WebDriver
    public Hooks(DriverProvider provider) {
        this.driver = provider.getDriver();
    }

    /*To initiate the browser and set URL */
    @Before
    public void setUp() throws IOException {

        BaseClass.getLogger().info(" Before Driver is initialize in the hooks..........");

        if (driver == null) {
            BaseClass.getLogger().error("Driver initialized as null in Hooks!");
            throw new RuntimeException("WebDriver initialization failed in Hooks.");
        } else {
            BaseClass.getLogger().info("Driver initialized in Hooks successfully: " + driver);
        }

        properties = BaseClass.getProperties();
        String appURL = properties.getProperty("appURL");
        BaseClass.getLogger().info("App URL From Properties" + appURL);
        System.out.println("App URL From Properties" + appURL);
        driver.get(appURL);

        BaseClass.getLogger().info(" Navigating to URL .........");
        driver.manage().window().maximize();

        BaseClass.getLogger().info(" Before hooks setup completed..........");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @AfterStep
    public void addscreenShot(Scenario scenario) {

        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

    }


}
