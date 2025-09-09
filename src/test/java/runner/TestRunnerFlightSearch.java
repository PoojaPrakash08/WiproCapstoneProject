package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-report-flightsearch.html",
                "json:target/cucumber-report-flightsearch.json",
                "junit:target/cucumber-flightsearch.xml"
        },
        tags = "@flightsearch",
        monochrome = true
)
public class TestRunnerFlightSearch extends AbstractTestNGCucumberTests {}
