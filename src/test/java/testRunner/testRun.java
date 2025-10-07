package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
        //features= {".//Features/"},
        features= {"src/test/resources/features/login.feature"},


        glue={"stepDefinations","hooks"},
        plugin= {
                "pretty", "html:reports/myreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"

        },

        dryRun=false,    // checks mapping between scenario steps and step definition methods
        monochrome=true,    // to avoid junk characters in output
        publish=true   // to publish report in cucumber server
        //tags="@sanity"  // this will execute scenarios tagged with @sanity
        //tags="@regression"
        //tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression
        //tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
        //tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
        //tags="@sc1" //Scenario with tag sc
)


public class testRun
{


}
