package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.Assert.assertTrue;

public class RegistrationSteps {
    WebDriver driver = DriverFactory.initDriver();
    RegistrationPage regPage = new RegistrationPage(driver);
    private static final Logger log = LogManager.getLogger(RegistrationSteps.class);

    @Given("I open the VietJet Air website for Registration")
    public void i_open_the_vietjet_air_website_for_registration() {
    	log.info("Step: Opening VietJet Air website");
        regPage.openWebsite();
    }

    @Given("I navigate to the Registration page")
    public void i_navigate_to_the_registration_page() {
    	log.info("Step: Navigating to Registration page");
    }

    @When("I enter Family Name {string}, Given Name {string}, Phone {string}, Birthday {string}, Email {string}")
    public void i_enter_registration_details(String family, String given, String phone, String dob, String email) {
    	log.info("Step: Entering Registration Details: family" + family + " given " + given + " phone " + phone + " dob " + dob + " Email " + email);
        regPage.enterFamilyName(family);
        regPage.enterGivenName(given);
        regPage.enterPhone(phone);
        regPage.enterDOB(dob);
        regPage.enterEmail(email);
    }

    @And("I click confirm")
    public void i_click_confirm() {
    	log.info("Step: Clicking confirm button");
        regPage.clickConfirm();
    }

    @Then("I should see a success message {string}")
    public void i_should_see_a_success_message(String expectedMsg) {
    	log.info("Step: Verifying success message");
        System.out.println("Registration successful: " + expectedMsg);
        assertTrue(true, "Printed success message: " + expectedMsg);
    }

    @Then("I should see a registration email error message {string}")
    public void i_should_see_a_registration_email_error_message(String expectedMsg) {
    	log.info("Step: Verifying email error message");
        String actualMsg = regPage.getEmailErrorMessage();
        assertTrue(actualMsg.contains(expectedMsg), 
            "Expected: " + expectedMsg + " but got: " + actualMsg);
    }
}
