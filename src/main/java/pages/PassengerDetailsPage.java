package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utils.ElementUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PassengerDetailsPage {
    private WebDriver driver;
    private ElementUtils elementUtils;
    private static final Logger log = LogManager.getLogger(PassengerDetailsPage.class);

    // Locators
    private By femaleTitle = By.xpath("//span[contains(@class,'MuiRadio-root')]//input[@value='Ms']/..");
    private By lastName = By.xpath("//input[@name='lastName']");
    private By firstName = By.xpath("//input[@name='firstName']");
    private By dob = By.xpath("//input[@name='dob']");
    private By phoneNumber = By.xpath("//input[@name='phoneNumber']");
    private By email = By.xpath("//input[@name='email']");
    private By nationalId = By.xpath("//input[@name='documentNumber']");
    private By continueBtn = By.xpath("//button[.//span[normalize-space(text())='Continue']]");
    private By errorMsg = By.xpath("//h5[normalize-space(text())='Please fill in all required information']");
    private By addonsContinueBtn = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div[1]/div/div[4]/div/div[3]/button");

    // Constructor
    public PassengerDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }

    public void waitForPassengerPage() {
        log.info("Waiting for Passenger Page after flight selection");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
            log.info("Passenger Page Displayed");
        } catch (TimeoutException e) {
            log.error("Passenger Page did not load", e);
            throw e;
        }
    }

    public void fillPassengerDetails(String lName, String fName, String birthDate, String phone, String mail, String nid) {
    	log.info("Entering Passenger Details: LastName=" + lName + ", FirstName=" + fName + ", DOB=" + birthDate + ", Phone=" + phone + ", Email=" + mail + ", NationalID=" + nid);
    	((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");
    	elementUtils.click(femaleTitle);
    	elementUtils.sendKeys(lastName, lName);
        elementUtils.sendKeys(firstName, fName);
        elementUtils.sendKeys(dob, birthDate);
        elementUtils.sendKeys(phoneNumber, phone);
        elementUtils.sendKeys(email, mail);
        elementUtils.sendKeys(nationalId, nid);
    }

    public void leaveNameBlank(String birthDate, String phone, String mail, String nid) {
    	log.info("Entering Passenger Details: DOB=" + birthDate + ", Phone=" + phone + ", Email=" + mail + ", NationalID=" + nid);
    	elementUtils.click(femaleTitle);
        elementUtils.sendKeys(dob, birthDate);
        elementUtils.sendKeys(phoneNumber, phone);
        elementUtils.sendKeys(email, mail);
        elementUtils.sendKeys(nationalId, nid);
    }

    public void clickContinue() {
    	log.info("Clicking Continue button");
    	elementUtils.click(continueBtn);
    }

    public String getErrorMessage() {
    	log.info("Fetching error message");
        return elementUtils.getText(errorMsg);
    }

    public void skipAddons() {
    	log.info("Skipping AddOn Page");
        elementUtils.click(addonsContinueBtn);
    }
}
