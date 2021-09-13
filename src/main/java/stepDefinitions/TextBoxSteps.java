package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.TextboxPage;
import java.util.HashMap;
import java.util.Map;

import static lib.utils.Constants.*;

public class TextBoxSteps {
    Map<String, String> context;
    TextboxPage textboxPage;

    @Before
    public void init() {
        textboxPage = new TextboxPage(WebDriverSteps.getDriver());
        context = new HashMap<>();
    }

    @After
    public void quit() {
        context.clear();
    }


    @Given("I enter {string} into {string} field")
    public void openTextBoxUrl(String name, String field) {
        switch (field) {
            case "name":
                textboxPage.waitForElementToBeVisible(textboxPage.usernameField);
                textboxPage.usernameField.sendKeys(name);
                context.put(NAME, name);
                break;
            case "email":
                textboxPage.waitForElementToBeVisible(textboxPage.emailField);
                textboxPage.emailField.sendKeys(name);
                context.put(EMAIL, name);
                break;
            case "current address":
                textboxPage.waitForElementToBeVisible(textboxPage.currendAddressField);
                textboxPage.currendAddressField.sendKeys(name);
                context.put(CURRENT_ADDRESS, name);
                break;
            case "permanent address":
                textboxPage.waitForElementToBeVisible(textboxPage.permanentAddressField);
                textboxPage.permanentAddressField.sendKeys(name);
                context.put(PERMANENT_ADDRES, name);
                break;
            default:
                break;
        }
    }

    @When("I click on submit button")
    public void clickSubmitButton() {
        textboxPage.scrollToElement(textboxPage.submitButton);
        textboxPage.submitButton.click();
    }

    @Then("following submitted fields are displayed: {string}")
    public void verifySubmittedFieldsAreDisplayed(String field) {
        switch (field) {
            case NAME:
                Assert.assertTrue(textboxPage.submittedName.getText().contains(context.get(NAME)));
                break;
            case EMAIL:
                Assert.assertTrue(textboxPage.submittedEmail.getText().contains(context.get(EMAIL)));
                break;
            case CURRENT_ADDRESS:
                Assert.assertTrue(textboxPage.submittedCurrentAddress.getText().contains(context.get(CURRENT_ADDRESS)));
                break;
            case PERMANENT_ADDRES:
                Assert.assertTrue(textboxPage.submittedPermanentAddress.getText().contains(context.get(PERMANENT_ADDRES)));
                break;
            case "all":
                Assert.assertTrue(textboxPage.submittedName.getText().contains(context.get(NAME)));
                Assert.assertTrue(textboxPage.submittedEmail.getText().contains(context.get(EMAIL)));
                Assert.assertTrue(textboxPage.submittedCurrentAddress.getText().contains(context.get(CURRENT_ADDRESS)));
                Assert.assertTrue(textboxPage.submittedPermanentAddress.getText().contains(context.get(PERMANENT_ADDRES)));
                break;
        }
    }
}
