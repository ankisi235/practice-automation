package com.automationpractice.utilities;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.util.List;

public class PageUtils {

    public static WebDriver driver;
    public static String currentDirectory = System.getProperty("user.dir");

    /**
     * This method initializes webdriver
     *
     * @param browserType - Name of the browsers Chrome, Firefox
     */
    public static void initDriver(String browserType) {
        try {
            if (browserType.contentEquals("Chrome")) {
                System.setProperty("webdriver.chrome.driver", currentDirectory + "/SeleniumWebDrivers/chromedriver");
                driver = new ChromeDriver();
                //ChromeOptions options = new ChromeOptions();
               // options.addArguments("--disable-extensions");
               // driver = new ChromeDriver(options);
            } else {
                //initialize the other driver here
            }
        } catch (Exception e) {
           System.out.println("Driver not found or init failed");
        }
    }

    /**
     * Select option by visible text from Select dropdown
     *
     * @param element dropdown webelement
     * @param option  visible text to be selected
     */
    public static void selectFromDropdown(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    /**
     * This method launches url and maximizes the screen
     *
     * @param url - page url
     * @param pageName - name of the page to be loaded
     * @return title of the page
     */
    public static String launchURL(String url, String pageName) {
        try {
            driver.get(url);
            driver.manage().window().maximize();
            ExtentReportNG.logTest(LogStatus.INFO, "application launch", pageName);
            return driver.getTitle();
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.FAIL, "Issue in launching the application", "LaunchError");
            return null;
        }
    }

    /**
     * wait and finds webelement of the web page by locators
     *
     * @param locator: locator of the element to be clicked
     * @return WebElement
     */
    public static WebElement find(By locator) {
        try {
            Wait<WebDriver>  wait = new FluentWait(driver).withTimeout(20, SECONDS)
                    .pollingEvery(2, SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NullPointerException.class)
                    .ignoring(ElementNotVisibleException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator);
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in finding the element" + e.getMessage());
        }
        return null;
    }

    /**
     * finds the webelement in the table cell using row and target element's locator
     * and clicks on it
     *
     * @param row           locator of row
     * @param itemName      item against which the action has to be taken
     * @param targetLocator locator of the column
     */
    public static void clickElementInTable(By row, String itemName, By targetLocator) {
        try {
            List<WebElement> rows = driver.findElements(row);
            for (WebElement item : rows) {
                if (item.getText() != "" || item.getText().contains(itemName)) {
                    click(find(targetLocator));
                }
            }
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in clicking the element in the table" + e.getMessage());
        }
    }

    /**
     * clicks on the element and handles exceptions
     *
     * @param webElement: webelement to be clicked
     * @return same webElement
     */
    public static WebElement click(WebElement webElement) {
        try {
            Wait<WebDriver> wait = new FluentWait(driver).withTimeout(20, SECONDS)
                    .pollingEvery(2, SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(WebDriverException.class)
                    .ignoring(NullPointerException.class);

            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            return webElement;
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in clicking the element" + e.getMessage());
        }
        return null;
    }

    /**
     * gets text of the webelement
     *
     * @return Text of the webelement
     * @param- locator: of the webelement which text has to be extracted
     */
    public static String getText(By locator) {
        try {
            Wait<WebDriver> wait = new FluentWait(driver).withTimeout(20, SECONDS)
                    .pollingEvery(2, SECONDS)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return find(locator).getText();
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in getting text " + e.getMessage());
            return null;
        }
    }

    /**
     * clears text of the webelement and then enters input
     *
     * @param webElement webElement in which value has to be entered
     * @param value      text to be entered
     * @return same webElement
     */
    public static WebElement input(WebElement webElement, String value) {
        try {
            webElement.clear();
            webElement.sendKeys(value);
            return webElement;
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in inserting text" + e.getMessage());
            return null;
        }
    }

    /**
     * mouse hovers over the element
     *
     * @param webElement
     */
    public static void mouseHover(WebElement webElement) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(webElement).build().perform();

        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in mouse hover" + e.getMessage());
        }
    }


    /**
     * closes browser windows
     */
    public static void closeDriver() {
        driver.close();
        driver.quit();
    }

    /**
     * kills driver instance in task manager
     *
     * @author Ankita Singh
     */
    public static void killDriver() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
