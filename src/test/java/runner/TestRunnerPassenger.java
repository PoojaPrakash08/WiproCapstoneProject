package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-report-passenger.html",
                "json:target/cucumber-report-passenger.json",
                "junit:target/cucumber-passenger.xml"
        },
        tags = "@passenger",
        monochrome = true
)
public class TestRunnerPassenger extends AbstractTestNGCucumberTests {}
