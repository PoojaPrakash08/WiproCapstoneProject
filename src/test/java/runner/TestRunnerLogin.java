package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-report-login.html",
                "json:target/cucumber-report-login.json",
                "junit:target/cucumber-login.xml"
        },
        tags = "@login",
        monochrome = true
)
public class TestRunnerLogin extends AbstractTestNGCucumberTests {}
