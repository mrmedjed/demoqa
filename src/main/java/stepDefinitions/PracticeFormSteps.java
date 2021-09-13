package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.PracticeFormPage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lib.utils.Constants.*;

public class PracticeFormSteps {

    Map<String, String> context;
    PracticeFormPage practiceFormPage;

    @Before
    public void init() {
        practiceFormPage = new PracticeFormPage(WebDriverSteps.getDriver());
        context = new HashMap<>();
    }
    @After
    public void quit() {
        context.clear();
    }

    @When("I enter {string} in {string} field")
    public void populateTextField(String value, String field) {
        switch (field) {
            case "first name":
                practiceFormPage.waitForElementToBeVisible(practiceFormPage.firstNameField);
                practiceFormPage.firstNameField.sendKeys(value);
                context.put(FIRST_NAME, value);
                break;
            case "last name":
                practiceFormPage.waitForElementToBeVisible(practiceFormPage.lastNameField);
                practiceFormPage.lastNameField.sendKeys(value);
                context.put(LAST_NAME, value);
                break;
            case "email":
                practiceFormPage.waitForElementToBeVisible(practiceFormPage.emailField);
                practiceFormPage.emailField.sendKeys(value);
                context.put(EMAIL, value);
                break;
            case "mobile number":
                practiceFormPage.waitForElementToBeVisible(practiceFormPage.phoneNumberField);
                practiceFormPage.phoneNumberField.sendKeys(value);
                context.put(FIRST_NAME, value);
                break;
            case "current address":
                practiceFormPage.waitForElementToBeVisible(practiceFormPage.currentAddressField);
                practiceFormPage.currentAddressField.sendKeys(value);
                context.put(CURRENT_ADDRESS, value);
                break;
            default:
        }
    }

    @When("I select gender: {string}")
    public void selectGender(String gender) {
        switch (gender) {
            case "male":
                practiceFormPage.genderRadioButtonMale.click();
                context.put(GENDER, gender);
            case "female":
                practiceFormPage.genderRadioButtonFemale.click();
                context.put(GENDER, gender);
            case "other":
                practiceFormPage.genderRadioButtonOther.click();
                context.put(GENDER, gender);
            default:
        }
    }

    @When("I pick a date")
    public void pickDate (DataTable table) {
        practiceFormPage.dateOfBirthField.click();
        Map<String, String> dateValues = table.asMap(String.class, String.class);

        WebDriverSteps.getDriver().findElement(By.xpath(String.format(practiceFormPage.DATE_YEAR_SELECTION_XPATH, dateValues.get("year"))))
                .click();
        WebDriverSteps.getDriver().findElement(By.xpath(String.format(practiceFormPage.DATE_MONTH_SELECTION_XPATH, dateValues.get("month"))))
                .click();
        WebDriverSteps.getDriver().findElement(By.xpath(String.format(practiceFormPage.DATE_DAY_SELECTION_XPATH, dateValues.get("day"))))
                .click();

        context.put(DATE_OF_BIRTH, String.format("%s %s,%s", dateValues.get("day"), dateValues.get("month"), dateValues.get("year")));
    }

}
