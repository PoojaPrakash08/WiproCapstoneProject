package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;
import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ElementUtils elementUtils;
    private static final Logger log = LogManager.getLogger(FlightSearchPage.class);

    // Locators
    private By acceptCookies = By.xpath("//button//h5[text()='Accept']");
    private By fromField = By.xpath("//label[text()='From']/following::input[1]");
    private By toField = By.xpath("//label[text()='To']/following::input[1]");
    private By cityBlocks = By.xpath("//div[contains(@class,'MuiExpansionPanelDetails-root')]//div[contains(@class,'MuiBox-root')]");
    private By departureDate = By.xpath("//p[text()='Departure date']");
    private By returnDate = By.xpath("//p[text()='Return Date']");
    private By calendarDays = By.xpath("//button[contains(@class,'rdrDay') and not(contains(@class,'rdrDayDisabled'))]");
    private By searchBtn = By.xpath("//button[.//span[text()=\"Let's go\"]]");
    private By adPopupClose = By.xpath("//button[@aria-label='close']");
    private By flightsList = By.xpath("(//div[contains(@class,'MuiBox-root') and descendant::div[contains(@class,'MuiTabs-root')]])[1]");
    private By firstDepartureFlight = By.xpath("(//p[contains(@class,'MuiTypography-h4') and text()='3.42'])[1]");
    private By firstReturnFlight = By.xpath("(//p[contains(@class,'MuiTypography-h4') and text()='3.42'])[2]");
    private By continueBtn = By.xpath("//button[.//span[text()='Continue']]");
    private By errorMsg = By.xpath("//h5[contains(text(),'Please fill in')]");

    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.elementUtils = new ElementUtils(driver);
    }

    public void openWebsite() {
        log.info("Opening VietJet Air website");
        driver.get("https://www.vietjetair.com/en");

        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(acceptCookies));
            cookieBtn.click();
            log.info("Accepted cookies successfully");
        } catch (Exception e) {
            log.warn("Cookie button not found or not clickable. Skipping...");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(fromField));
    }

    public void selectOrigin(String originInput) {
        log.info("Typing origin input: " + originInput);
        if (originInput == null || originInput.trim().isEmpty()) {
            log.warn("Origin input is blank, skipping...");
            return;
        }

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(fromField));
        input.clear();
        input.sendKeys(originInput);
        input.click(); // Trigger dropdown

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cityBlocks));
        selectCityBlockWithMouse(originInput);
    }

    public void selectDestination(String destinationInput) {
        log.info("Typing destination input: " + destinationInput);
        if (destinationInput == null || destinationInput.trim().isEmpty()) {
            log.warn("Destination input is blank, skipping...");
            return;
        }

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(toField));
        input.clear();
        input.sendKeys(destinationInput);
        input.click(); // Trigger dropdown

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cityBlocks));
        selectCityBlockWithMouse(destinationInput);
    }

    private void selectCityBlockWithMouse(String inputText) {
        List<WebElement> blocks = driver.findElements(cityBlocks);
        Actions actions = new Actions(driver);

        for (WebElement block : blocks) {
            try {
                WebElement match = block.findElement(By.xpath(".//div[contains(text(),'" + inputText + "')]"));
                actions.moveToElement(block).click().perform();
                log.info("Mouse-clicked city block containing: " + inputText);
                return;
            } catch (NoSuchElementException e) {
            }
        }

        log.warn("No matching city block found for input: " + inputText);
    }

    public void selectDates(String departDay, String returnDay) {
        log.info("Selecting departure date: " + departDay + ", return date: " + returnDay);

        driver.findElement(departureDate).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarDays));
        selectCalendarDay(departDay);

        driver.findElement(returnDate).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarDays));
        selectCalendarDay(returnDay);
    }

    private void selectCalendarDay(String dayText) {
        for (WebElement day : driver.findElements(calendarDays)) {
            if (day.getText().trim().equals(dayText)) {
                day.click();
                log.info("Selected calendar day: " + dayText);
                return;
            }
        }
        log.warn("Date not found in calendar: " + dayText);
    }

    public void clickSearch() {
        log.info("Attempting to click 'Let's go' button");

        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                log.info("Clicked 'Let's go' button successfully");
            } else {
                log.warn("'Let's go' button not interactable, using JS fallback");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            }
        } catch (ElementClickInterceptedException e) {
            log.warn("Click intercepted, using JS fallback");
            WebElement btn = driver.findElement(searchBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        } catch (Exception e) {
            log.error("Failed to click 'Let's go' button", e);
        }
    }

    public boolean areFlightsDisplayed() {
        log.info("Checking if flights are displayed");
        return elementUtils.isElementVisible(flightsList);
    }

    public void selectDepartureFlight() {
        log.info("Selecting departure flight");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100)");
        wait.until(ExpectedConditions.elementToBeClickable(firstDepartureFlight)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void selectReturnFlight() {
        log.info("Selecting return flight");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100)");
        elementUtils.clickAndWait(firstReturnFlight, continueBtn);
        elementUtils.click(continueBtn);
    }

    public String getErrorMessage() {
        log.info("Fetching error message");
        return elementUtils.getText(errorMsg);
    }
}