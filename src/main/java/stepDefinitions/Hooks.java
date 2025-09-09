package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;

public class Hooks {
    private WebDriver driver;
    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.initDriver();
        test.set(extent.createTest(scenario.getName()));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
            String screenshotPath = screenshotUtil.captureScreenshot(scenario.getName());

            try {
                byte[] fileContent = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(screenshotPath));
                scenario.attach(fileContent, "image/png", scenario.getName());
                test.get().fail("Scenario failed: " + scenario.getName())
                        .addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            test.get().pass("Scenario passed: " + scenario.getName());
        }

        extent.flush();
        DriverFactory.quitDriver();
    }
}
