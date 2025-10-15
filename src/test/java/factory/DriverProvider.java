package factory;

import lombok.Getter;
import org.openqa.selenium.WebDriver;


import javax.inject.Singleton;
import java.io.IOException;

@Getter
@Singleton
public class DriverProvider {

    // PicoContainer injects this driver
    private final WebDriver driver;

    public DriverProvider() throws IOException {
        driver = BaseClass.initilizeBrowser();      // initialize driver
        BaseClass.setDriver(driver);      // store in ThreadLocal
    }

    public WebDriver getDriver() {
        return driver;
    }
}

