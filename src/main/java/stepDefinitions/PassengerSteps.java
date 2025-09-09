package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.PassengerDetailsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.Assert.assertTrue;

public class PassengerSteps {
    WebDriver driver = DriverFactory.initDriver();
    PassengerDetailsPage passengerPage = new PassengerDetailsPage(driver);
    private static final Logger log = LogManager.getLogger(PassengerSteps.class);

    @Given("I am on the Passenger Details page")
    public void i_am_on_the_passenger_details_page() {
    	log.info("Step: Currently at the Passenger Details page");
        passengerPage.waitForPassengerPage();
    }

    @When("I enter DOB {string}, Phone {string}, Email {string}, National ID {string}")
    public void i_enter_passenger_details_in_the_form(String dob, String phone, String email, String nid) {
    	log.info("Step: Entering Passenger Details: dob " + dob + " phone " + phone + " Email " + email + " NID " + nid);
        passengerPage.leaveNameBlank(dob, phone, email, nid);
    }

    @When("I leave Last Name and First Name blank")
    public void i_leave_last_name_and_first_name_blank(String dob, String phone, String email, String nid) {
    	log.info("Step: Leaving Last name and First name blank: dob " + dob + " phone " + phone + " Email " + email + " NID " + nid);
        passengerPage.leaveNameBlank(dob, phone, email, nid);
    }

    @And("I click continue button on passenger page")
    public void i_click_continue_on_passenger_page() {
    	log.info("Step: Clicking continue button");
        passengerPage.clickContinue();
    }

    @Then("I should see a passenger page error message {string}")
    public void i_should_see_a_passenger_page_error_message(String expectedMsg) {
    	log.info("Step: Verifying error message: " + expectedMsg);
        String actualMsg = passengerPage.getErrorMessage();
        assertTrue(actualMsg.contains(expectedMsg), "Expected: " + expectedMsg + " but got: " + actualMsg);
    }
}
