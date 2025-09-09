package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver = DriverFactory.initDriver();
    LoginPage loginPage = new LoginPage(driver);
    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    
    @Given("I open the VietJet Air website for Login")
    public void i_open_the_vietjet_air_website_for_login() {
    	log.info("Step: Opening VietJet Air website");
    	loginPage.openWebsite();
    }
    
    @Given("I navigate to the Login page")
    public void i_navigate_to_the_login_page() {
    	log.info("Step: Navigating to login page");
    }

    @When("I enter phone number {string}")
    public void i_enter_phone_number(String phone) {
    	log.info("Step: Entering phone number: " + phone);
        loginPage.enterPhone(phone);
    }

    @And("I click sign in")
    public void i_click_sign_in() {
    	log.info("Step: Clicking Sign in button");
        loginPage.clickSignIn();
    }

    @Then("I should see a login error message {string}")
    public void i_should_see_a_login_error_message(String expectedMsg) {
    	log.info("Step: Verifying error message: " + expectedMsg);
        String actualMsg = loginPage.getErrorMessage();
        assertTrue(actualMsg.contains(expectedMsg), "Expected: " + expectedMsg + " but found: " + actualMsg);
    }
}
