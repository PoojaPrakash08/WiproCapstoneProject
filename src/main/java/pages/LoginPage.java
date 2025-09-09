package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ElementUtils elementUtils;
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // Locators
    private By acceptCookies = By.xpath("//button//h5[text()='Accept']");
    private By signInBtn = By.xpath("//span[normalize-space()='Sign in']");
    private By phoneInput = By.xpath("//input[@id='phoneNumber']");
    private By submitBtn = By.xpath("//input[@id='submit-button']");
    private By message = By.xpath("//div[contains(text(),'Account does not exist yet')]");

    private By loginIframe = By.cssSelector("iframe[src*='skyjoy-authen.vietjetair.com']");
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public void switchToRegistrationForm() {
        log.info("Switching to login iframe");
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(loginIframe));
        driver.switchTo().frame(iframe);
    }

    // Navigate
    public void openWebsite() {
        driver.get("https://www.vietjetair.com/en");
        log.info("Opening the VietJetAir Website");
        try {
        	elementUtils.click(acceptCookies);
            log.info("Clicked Accept Cookies button");
        } catch (TimeoutException e) {
        	log.warn("Accept Cookies button not displayed, skipping...");
        }
        
        log.info("Clicking Sign in button");
        elementUtils.click(signInBtn);
        switchToRegistrationForm();
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        log.info("Phone input field displayed");
    }

    // Actions
    public void enterPhone(String phone) {
    	log.info("Entered phone number: " + phone);
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickSignIn() {
    	elementUtils.click(submitBtn);
        log.info("Clicked Sign in button");
    }

    public String getErrorMessage() {
    	String errorText = elementUtils.getText(message);
        log.info("Fetched error message: " + errorText);
        return errorText;
    }
}
