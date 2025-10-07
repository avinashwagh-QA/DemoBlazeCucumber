package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    static Properties properties;
    static Logger logger;

    public static WebDriver initilizeBrowser() throws IOException {


        properties = getProperties();
        String executionEnv = properties.getProperty("execution_env");
        String browser = properties.getProperty("browser").toLowerCase();
        String os = properties.getProperty("os").toLowerCase();
        WebDriver driver = null;

        try {
            if (executionEnv.equalsIgnoreCase("remote")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();

                //os
                switch (os) {
                    case "windows":
                        capabilities.setPlatform(Platform.WINDOWS);
                        break;
                    case "mac":
                        capabilities.setPlatform(Platform.MAC);
                        break;
                    case "linux":
                        capabilities.setPlatform(Platform.LINUX);
                        break;
                    default:
                        throw new RuntimeException("Invalid OS name in Config file ");
                }

                //browser
                switch (browser) {
                    case "chrome":
                        capabilities.setBrowserName("chrome");
                        break;
                    case "edge":
                        capabilities.setBrowserName("MicrosoftEdge");
                        break;
                    case "firefox":
                        capabilities.setBrowserName("firefox");
                        break;
                    default:
                        throw new RuntimeException("Invalid Browser name in config file");
                }

                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

            } else if (executionEnv.equalsIgnoreCase("local")) {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "edge":
                        driver = new EdgeDriver();
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new RuntimeException("Invalid Browser name in config file");
                }
            }

            if (driver == null) {
                throw new RuntimeException("WebDriver initialization failed — check browser or remote setup");
            }
            tlDriver.set(driver);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver" + e.getMessage());
        }
        return getDriver();
    }

    // getDriver() is for reading the driver.
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // setDriver() is for writing/assigning the driver (from Hooks or any initialization code).
    public static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    public static Properties getProperties() throws IOException {
        if (properties == null) {
            FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/Config.properties");
            properties = new Properties();
            BaseClass.properties.load(file);
        }
        return properties;
    }

    /* Method to get Logger instance */
    public static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger(BaseClass.class); //Log4j
        }
        return logger;
    }

}






