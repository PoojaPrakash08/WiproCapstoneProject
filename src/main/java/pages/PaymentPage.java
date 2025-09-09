//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import utils.ElementUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.time.Duration;
//
//public class PaymentPage {
//    WebDriver driver;
//    WebDriverWait wait;
//    private ElementUtils elementUtils;
//    private static final Logger log = LogManager.getLogger(PaymentPage.class);
//
//    // Locators
//    private By payNowBtn = By.xpath("//button[.//h4[normalize-space()='Pay now']]");
//    private By cardNumberField = By.xpath("//input[@name='CardNumber']");
//    private By cardHolderField = By.xpath("//input[@name='CardHolderName']");
//    private By expiryField = By.xpath("//input[@name='CardExpireDate']");
//    private By cvvField = By.xpath("//input[@name='CardVerificationValue']");
//    private By addressField = By.xpath("//input[@name='Address']");
//    private By cityField = By.xpath("//input[@name='City']");
//    private By countryDropdownBtn = By.xpath("//*[@id=\"app-page\"]/div/div[2]/div[2]/form/div[3]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/button"); // button-like div
//
//    private By countryOption(String country) {
//        return By.xpath("/html/body/div[3]/div[3]/div/div[2]/div[2]/div[2]/div");
//    }
//
//    private By nextBtn = By.xpath("//button[normalize-space()='Next']");
//    private By errorMessage = By.xpath("//*[@id=\"cc06d86c2cee42faa0b8d2ea9d0b637b-helper-text\"]");
//    private By paymentiframe = By.id("galaxy-pay-upc-iframe");
//
//    public PaymentPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//        this.elementUtils = new ElementUtils(driver);
//    }
//
//    public void switchToPaymentForm() {
//        log.info("Switching to payment iframe");
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentiframe));
//    }
//
//    public void clickPayNow() {
//        log.info("Clicking PayNow button");
//        wait.until(ExpectedConditions.elementToBeClickable(payNowBtn)).click();
//    }
//
//    public void enterCardDetails(String cardNum, String name, String expiry, String cvv, String addr, String city, String country) {
//        log.info("Entering Card Details");
//        switchToPaymentForm();
//        elementUtils.sendKeys(cardNumberField, cardNum);
//        elementUtils.sendKeys(cardHolderField, name);
//        elementUtils.sendKeys(expiryField, expiry);
//        elementUtils.sendKeys(cvvField, cvv);
//        elementUtils.sendKeys(addressField, addr);
//        elementUtils.sendKeys(cityField, city);
//
//        // Handle custom dropdown
//        elementUtils.click(countryDropdownBtn);
//        wait.until(ExpectedConditions.elementToBeClickable(countryOption(country))).click();
//    }
//
//    public void clickNext() {
//        log.info("Clicking Next button");
//        elementUtils.click(nextBtn);
//    }
//
//    public String getErrorMessage() {
//    	log.info("Displaying Error message");
//    	driver.switchTo().defaultContent(); 
//        switchToPaymentForm(); 
//    	((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)");
//        return elementUtils.getText(errorMessage);    
//    }
//
//}
