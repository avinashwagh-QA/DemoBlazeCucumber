package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
        //features= {"src/test/resources/features"},
        //features= {"src/test/resources/features/login.feature"},
        //features = {"src/test/resources/features/AllProduct.feature"},
        features = {"src/test/resources/features/AddToCart.feature"},


        glue={"stepDefinitions","hooks"},
        plugin= {
                "pretty", "html:reports/myreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"

        },

        dryRun=false,    // checks mapping between scenario steps and step definition methods
        monochrome=true,    // to avoid junk characters in output
        publish=true   // to publish report in cucumber server
        //tags="@Sanity"  // this will execute scenarios tagged with @sanity
        //tags="@Regression"
        //tags="@Sanity and @Regression" //Scenarios tagged with both @sanity and @regression
        //tags="@Sanity and not @Regression" //Scenarios tagged with @sanity but not tagged with @regression
        //tags="@Sanity or @Regression" //Scenarios tagged with either @sanity or @regression

)


public class testRun
{


}
