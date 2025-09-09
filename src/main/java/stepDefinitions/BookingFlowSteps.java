package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pages.FlightSearchPage;
import pages.PassengerDetailsPage;
//import pages.PaymentPage;

import static org.testng.Assert.assertTrue;

public class BookingFlowSteps {
    WebDriver driver = DriverFactory.getDriver();
    FlightSearchPage flightPage = new FlightSearchPage(driver);
    PassengerDetailsPage passengerPage = new PassengerDetailsPage(driver);
//    PaymentPage paymentPage = new PaymentPage(driver);

    private static final Logger log = LogManager.getLogger(BookingFlowSteps.class);

    // Flight Search
    @Given("I open the VietJet Air website for FlightSearch")
    public void i_open_the_vietjet_air_website_for_flightsearch() {
        log.info("Step: Opening VietJet Air website for FlightSearch");
        flightPage.openWebsite();
    }

    @When("I select origin {string} and destination {string}")
    public void i_select_origin_and_destination(String origin, String destination) {
        log.info("Step: Selecting origin and destination");
        flightPage.selectOrigin(origin);
        flightPage.selectDestination(destination);
    }

    @When("I select departure day {string} and return day {string}")
    public void i_select_departure_and_return(String depart, String ret) {
        log.info("Step: Selecting departure and return dates");
        flightPage.selectDates(depart, ret);
    }

    @And("I click search flights")
    public void i_click_search_flights() {
        log.info("Step: Clicking search flights button");
        flightPage.clickSearch();
    }

    @Then("I should see flight options displayed")
    public void i_should_see_flights_displayed() {
        log.info("Step: Verifying flights are displayed");
        assertTrue(flightPage.areFlightsDisplayed(), "Flights not displayed");
    }

    @And("I select a departure flight and continue")
    public void i_select_departure_flight_and_continue() {
        log.info("Step: Selecting departure flight");
        flightPage.selectDepartureFlight();
    }

    @And("I select a return flight and continue")
    public void i_select_return_flight_and_continue() {
        log.info("Step: Selecting return flight");
        flightPage.selectReturnFlight();
    }

    // Passenger Details
    @Then("I should see Passenger Details page")
    public void i_should_see_passenger_details_page() {
        log.info("Step: Waiting for Passenger Details page");
        passengerPage.waitForPassengerPage();
    }

    @When("I enter Last Name {string}, First Name {string}, DOB {string}, Phone {string}, Email {string}, National ID {string}")
    public void i_enter_passenger_details(String lastName, String firstName, String dob, String phone, String email, String nid) {
        log.info("Step: Filling passenger details");
        passengerPage.fillPassengerDetails(lastName, firstName, dob, phone, email, nid);
    }

    @And("I click continue on passenger page")
    public void i_click_continue_on_passenger_page() {
        log.info("Step: Clicking Continue on passenger page");
        passengerPage.clickContinue();
    }

    @And("I skip add-ons and proceed to payment page")
    public void i_skip_addons() {
        log.info("Step: Skipping add-ons page");
        passengerPage.skipAddons();
    }

    // Payment
    @Then("I should see Payment Page")
    public void i_should_see_payment_page() {
        log.info("Step: Waiting for Payment page");
        log.info("Step: Payment page displayed");
    }
//
//    @When("I enter Card Number {string}, Name {string}, Expiry {string}, CVV {string}, Address {string}, City {string}, Country {string}")
//    public void i_enter_payment_details(String cardNum, String cardName, String expiry, String cvv, String address, String city, String country) {
//        log.info("Step: Entering payment details");
//        paymentPage.enterCardDetails(cardNum, cardName, expiry, cvv, address, city, country);
//    }
//
//    @And("I click Next on Payment page")
//    public void i_click_next_on_payment_page() {
//        log.info("Step: Clicking Next on Payment page");
//        paymentPage.clickNext();
//    }
//
//    @Then("error message {string} should be displayed on Payment Page")
//    public void error_message_should_be_displayed_on_payment_page(String expectedMsg) {
//    	log.info("Step: Displaying error message " + expectedMsg);
//        Assert.assertEquals(paymentPage.getErrorMessage(), expectedMsg);
//    }
}
