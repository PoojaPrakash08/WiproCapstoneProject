package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ElementUtils elementUtils;
    private static final Logger log = LogManager.getLogger(RegistrationPage.class);

    // Locators
    private By acceptCookies = By.xpath("//button//h5[text()='Accept']");
    private By signUpBtn = By.xpath("//span[normalize-space()='Sign up']");
    private By familyName = By.id("last-name");
    private By givenName = By.id("first-name");
    private By phoneNumber = By.id("phoneNumber");
    private By birthday = By.id("dob");
    private By email = By.id("email");
    private By confirmBtn = By.id("kc-login");
    private By emailerrorMessage = By.id("validate-email");
    
    private By registrationIframe = By.cssSelector("iframe[src*='skyjoy-authen.vietjetair.com']");
    
    // Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void switchToRegistrationForm() {
        log.info("Switching to registration iframe");
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(registrationIframe));
        driver.switchTo().frame(iframe);
    }

    public void openWebsite() {
    	log.info("Opening VietJetAir website");
        driver.get("https://www.vietjetair.com/en");
        log.info("Accepting Cookies");
        wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookies)).click();
        log.info("Clicking Sign up button");
        elementUtils.click(signUpBtn);
        switchToRegistrationForm();
        wait.until(ExpectedConditions.visibilityOfElementLocated(familyName));
    }
    
    public void enterFamilyName(String fname) {
    	elementUtils.sendKeys(familyName, fname);
    }

    public void enterGivenName(String gname) {
    	elementUtils.sendKeys(givenName, gname);
    }

    public void enterPhone(String phone) {
    	elementUtils.sendKeys(phoneNumber, phone);
    }

    public void enterDOB(String dob) {
    	elementUtils.sendKeys(birthday, dob);
    }

    public void enterEmail(String mail) {
    	elementUtils.sendKeys(email, mail);
    }

    public void clickConfirm() {
    	elementUtils.click(confirmBtn);
    }

    // For NEGATIVE scenario -> fetch error msg from website
    public String getEmailErrorMessage() {
    	log.info("Fetching email error message");
        return elementUtils.getText(emailerrorMessage);
    }
}
