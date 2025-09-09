package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-report-payment.html",
                "json:target/cucumber-report-payment.json",
                "junit:target/cucumber-payment.xml"
        },
        tags = "@payment",
        monochrome = true
)
public class TestRunnerPayment extends AbstractTestNGCucumberTests {}
