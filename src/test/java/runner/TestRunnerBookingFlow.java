package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-report-bookingflow.html",
                "json:target/cucumber-report-bookingflow.json",
                "junit:target/cucumber-bookingflow.xml"
        },
        tags = "@bookingFlow",
        monochrome = true
)
public class TestRunnerBookingFlow extends AbstractTestNGCucumberTests {}
