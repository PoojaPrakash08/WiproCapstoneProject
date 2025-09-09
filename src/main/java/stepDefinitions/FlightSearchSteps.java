package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.FlightSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.Assert.assertTrue;

public class FlightSearchSteps {
    WebDriver driver = DriverFactory.initDriver();
    FlightSearchPage flightPage = new FlightSearchPage(driver);
    private static final Logger log = LogManager.getLogger(FlightSearchSteps.class);


    @Given("I open the VietJet Air website for FlightSearch process")
    public void i_open_the_vietjet_air_website_for_flightsearch_process() {
    	log.info("Step: Opening VietJet Air website");
        flightPage.openWebsite();
    }

    @When("I select origin {string} and destination {string} in flightsearch")
    public void i_select_origin_and_destination_in_flightsearch(String origin, String destination) {
    	log.info("Step: Selecting origin: " + origin + " and destination: " + destination);
        flightPage.selectOrigin(origin);
        flightPage.selectDestination(destination);
    }

    @When("I select departure day {string} and return day {string} for flight")
    public void i_select_departure_day_and_return_day_for_flight(String depart, String ret) {
    	log.info("Step: Selecting departure date: " + depart + " and return date: " + ret);
        flightPage.selectDates(depart, ret);
    }

    @And("I click on lets go button")
    public void i_click_on_lets_go_button() {
    	log.info("Step: Clicking search flights button");
        flightPage.clickSearch();
    }

    @Then("I should see a flight search error message {string}")
    public void i_should_see_a_flight_search_error_message(String expectedMsg) {
    	log.info("Step: Verifying error message: " + expectedMsg);
        String actualMsg = flightPage.getErrorMessage();
        assertTrue(actualMsg.contains(expectedMsg), "Expected: " + expectedMsg + " but found: " + actualMsg);
    }
}

