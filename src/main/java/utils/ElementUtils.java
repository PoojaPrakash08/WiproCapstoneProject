package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(ElementUtils.class);

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Click element with wait
    public void click(By locator) {
        try {
            log.info("Clicking on element: " + locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            log.error("Failed to click element: " + locator, e);
            throw e;
        }
    }

    // Send keys with wait
    public void sendKeys(By locator, String text) {
        try {
            log.info("Typing '" + text + "' into element: " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            log.error("Failed to type into element: " + locator, e);
            throw e;
        }
    }

    // Get text
    public String getText(By locator) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            log.info("Fetched text from element " + locator + ": " + text);
            return text;
        } catch (Exception e) {
            log.error("Failed to get text from element: " + locator, e);
            throw e;
        }
    }

    // Check element visibility
    public boolean isDisplayed(By locator) {
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            log.info("Element " + locator + " displayed: " + visible);
            return visible;
        } catch (Exception e) {
            log.warn("Element not visible: " + locator);
            return false;
        }
    }
    
 // Click element and handle optional popup (if present)
    public void clickAndHandlePopup(By mainBtn, By popupCloseBtn) {
        try {
            log.info("Clicking main button: " + mainBtn);
            wait.until(ExpectedConditions.elementToBeClickable(mainBtn)).click();

            // Try closing popup if it appears
            try {
                WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popupCloseBtn));
                popup.click();
                log.info("Closed popup: " + popupCloseBtn);
            } catch (TimeoutException te) {
                log.info("No popup appeared after clicking button: " + mainBtn);
            }
        } catch (Exception e) {
            log.error("Failed to click element: " + mainBtn, e);
            throw e;
        }
    }
    
 // Select dropdown by visible text
    public void selectByVisibleText(By locator, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            new Select(element).selectByVisibleText(value);
            log.info("Selected '" + value + "' from dropdown: " + locator);
        } catch (Exception e) {
            log.error("Failed to select '" + value + "' from dropdown: " + locator, e);
            throw e;
        }
    }

    
 // Check if element is visible
    public boolean isElementVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean visible = element.isDisplayed();
            log.info("Element visible: " + locator);
            return visible;
        } catch (TimeoutException e) {
            log.warn("Element not visible: " + locator);
            return false;
        }
    }

    // Click element and then wait for another element
    public void clickAndWait(By clickLocator, By waitForLocator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickLocator)).click();
            log.info("Clicked on: " + clickLocator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(waitForLocator));
            log.info("Waited for element after click: " + waitForLocator);
        } catch (Exception e) {
            log.error("Failed to click and wait. Click: " + clickLocator + " WaitFor: " + waitForLocator, e);
            throw e;
        }
    }


}
