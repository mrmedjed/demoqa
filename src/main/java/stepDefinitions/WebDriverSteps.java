package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import lib.utils.ConfigProperties;
import lib.webdriver.webdriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static lib.utils.Constants.*;

public class WebDriverSteps {

    private static WebDriver driver;

    @Before(order = 1)
    public void init() {
        driver = webdriverFactory.getDriver("pcBrowser");
    }

    /**
     * Added short sleep between each step because tests are running too fast.
     * Feel free to remove the sleep part if you want faster run.
     */
    @BeforeStep
    public void waitBeforeStep() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        closeGoogleAddIfPresent();
    }
    /**
     * Also added short sleep after each scenario for easier debugging.
     * Feel free to remove the sleep part if you want faster run.
     */
    @After
    public void quit() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.quit();
    }

    @Given("I am on {string} page")
    public void navigateToPage(String page) {
        switch (page) {
            case "Text Box":
                driver.get(ConfigProperties.getBaseURL() + TEXT_BOX_PAGE);
                break;
            case "Upload/Download":
                driver.get(ConfigProperties.getBaseURL() + UPLOAD_DOWNLOAD_PAGE);
                break;
            case "Practice Form":
                driver.get(ConfigProperties.getBaseURL() + PRACTICE_FORM_PAGE);
                break;
            case "Book Store":
                driver.get(ConfigProperties.getBaseURL() + BOOK_STORE_PAGE);
                BookStoreSteps.getInitialBooksList();
                break;
            default:
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void clickJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void closeGoogleAddIfPresent() {
        try {
            if (driver.findElement(By.id("close-fixedban")).isDisplayed()) {
                driver.findElement(By.id("close-fixedban")).click();
            }
        } catch (Exception ignored) {
        }
    }
}