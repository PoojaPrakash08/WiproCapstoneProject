//package stepDefinitions;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import io.cucumber.java.en.*;
//import pages.PaymentPage;
//import base.DriverFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class PaymentSteps {
//    WebDriver driver = DriverFactory.getDriver();
//    PaymentPage paymentPage = new PaymentPage(driver);
//    private static final Logger log = LogManager.getLogger(PaymentSteps.class);
//
//    @Given("user is on Payment Method page")
//    public void user_is_on_payment_method_page() {
//    	log.info("Step: User is on the Payment Page method");
//        // Already navigated from booking flow
//        paymentPage.clickPayNow();
//    }
//
//    @When("user enters invalid card details {string}, {string}, {string}, {string}, {string}, {string}, {string}")
//    public void user_enters_invalid_card_details(String cardNum, String name, String expiry, String cvv, String addr, String city, String country) {
//    	log.info("Step: Entering Card Details: cardNum" + cardNum + " Name " + name + "expiryDate " + expiry + " cvv " + cvv + " address " + addr + " city " + city + " contry " + country);
//        paymentPage.enterCardDetails(cardNum, name, expiry, cvv, addr, city, country);
//        paymentPage.clickNext();
//    }
//
//    @Then("error message {string} should be displayed on Payment Page after invalid details")
//    public void error_message_should_be_displayed_on_payment_page_after_invalid_details(String expectedMsg) {
//    	log.info("Step: Displaying error message " + expectedMsg);
//        Assert.assertEquals(paymentPage.getErrorMessage(), expectedMsg);
//    }
//}
