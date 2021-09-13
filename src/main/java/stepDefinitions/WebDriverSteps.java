package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import lib.utils.ConfigProperties;
import lib.webdriver.webdriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static lib.utils.Constants.*;

public class WebDriverSteps {

    private static WebDriver driver;

    @Before(order = 1)
    public void init() {
        driver = webdriverFactory.getDriver("pcBrowser");
    }

    @BeforeStep
    public void waitBeforeStep() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
    }

    @After
    public void quit() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(15000);
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
            default:
        }

    }

    public static WebDriver getDriver() {
        return driver;
    }


}
