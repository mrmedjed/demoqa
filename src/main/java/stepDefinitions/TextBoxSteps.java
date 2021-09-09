package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lib.utils.Constants;
import lib.webdriver.webdriverFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.TextboxPage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TextBoxSteps {
    Map<String, String> context;
    WebDriver driver;
    TextboxPage textboxPage;

    @Before
    public void init() {
        driver = webdriverFactory.getDriver("demoqa", "pcBrowser");
        textboxPage = new TextboxPage(driver);
        context = new HashMap<>();
    }

    @After
    public void quit() {
        context.clear();
        driver.quit();
    }
    @BeforeStep
    public void waitBeforeStep() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
    }



    @Given("I enter {string} into name field")
    public void openTextBoxUrl(String name) {
        textboxPage.waitForElementToBeVisible(textboxPage.usernameField);
        textboxPage.usernameField.sendKeys(name);
        context.put(Constants.NAME, name);
    }

    @When("I click on submit button")
    public void clickSubmitButton() {
        textboxPage.submitButton.click();
    }

    @Then("previously entered name is displayed")
    public void verifyNameIsDisplayed() {
        Assert.assertTrue(textboxPage.submittedName.getText().contains(context.get("name")));
    }

}
