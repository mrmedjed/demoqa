package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.asserts.SoftAssert;
import pages.PracticeFormPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static lib.utils.Constants.*;
import static pages.UploadDownloadPage.getFilePathByFormat;

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
                context.put(MOBILE_NUMBER, value);
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
            case "Male":
                practiceFormPage.genderRadioButtonMale.click();
                context.put(GENDER, gender);
            case "Female":
                practiceFormPage.genderRadioButtonFemale.click();
                context.put(GENDER, gender);
            case "Other":
                practiceFormPage.genderRadioButtonOther.click();
                context.put(GENDER, gender);
            default:
        }
    }

    @When("I pick a date")
    public void pickDate(DataTable table) {
        practiceFormPage.dateOfBirthField.click();
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        WebDriverSteps.getDriver().findElement(By.xpath(String.format(practiceFormPage.DATE_YEAR_SELECTION_XPATH, rows.get(0).get("year"))))
                .click();
        WebDriverSteps.getDriver().findElement(By.xpath(String.format(practiceFormPage.DATE_MONTH_SELECTION_XPATH, rows.get(0).get("month"))))
                .click();
        WebDriverSteps.getDriver().findElement(By.xpath(String.format(practiceFormPage.DATE_DAY_SELECTION_XPATH, rows.get(0).get("day"))))
                .click();

        context.put(DATE_OF_BIRTH, String.format("%s %s,%s", rows.get(0).get("day"), rows.get(0).get("month"), rows.get(0).get("year")));
    }

    @When("I enter following subjects:")
    public void enterSubjects(DataTable table) {
        List<String> listOfSubjects = table.asList();
        StringBuilder subjects = new StringBuilder();

        for (String subject : listOfSubjects) {
            practiceFormPage.subjectsField.click();
            practiceFormPage.subjectsField.sendKeys(subject);
            practiceFormPage.subjectsField.sendKeys(Keys.RETURN);
            subjects.append(String.format("%s, ", subject));
        }
        context.put(SUBJECTS, subjects.substring(0, subjects.length() - 2));
    }

    @When("I select following hobbies:")
    public void selectHobbies(DataTable table) {
        List<String> listOfHobbies = table.asList();
        StringBuilder hobbies = new StringBuilder();

        for (String hobbie : listOfHobbies) {
            hobbies.append(String.format("%s, ", hobbie));
            if (hobbie.equals(practiceFormPage.hobbiesCheckboxMusic.getText())) {
                practiceFormPage.hobbiesCheckboxMusic.click();
            } else if (hobbie.equals(practiceFormPage.hobbiesCheckboxReading.getText())) {
                practiceFormPage.hobbiesCheckboxReading.click();
            } else {
                practiceFormPage.hobbiesCheckboxSports.click();
            }
        }
        context.put(HOBBIES, hobbies.substring(0, hobbies.length() - 2));
    }

    @When("I upload a picture")
    public void uploadPicture() {
        practiceFormPage.uploadPictureButton.sendKeys(getFilePathByFormat(".jpeg"));
        context.put(PICTURE, FILE_NAME_JPEG);
    }

    @When("I select state {string} and city {string}")
    public void selectStateAndCity(String state, String city) {
        //practiceFormPage.stateDropdown.click();
        //practiceFormPage.stateDropdownnn.sendKeys(Keys.RETURN);
    }

    @When("I submit the form")
    public void clickSubmit() {
        practiceFormPage.scrollToElement(practiceFormPage.submitButton);
        practiceFormPage.submitButton.click();
    }

    @Then("form is submitted successfully")
    public void isFormSubmitted() {
        Assert.assertTrue(practiceFormPage.submitModalTitle.isDisplayed());
    }

    @Then("form is not submitted")
    public void notFormSubmitted() {
        Assert.assertTrue(practiceFormPage.submittedFieldLabels.isEmpty());
    }

    @Then("correct values are displayed on the form modal")
    public void verifySubmittedValues() {
        context.put(NAME, String.format("%s %s", context.get(FIRST_NAME), context.get(LAST_NAME)));
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < 10; i++) {
            if (context.containsKey(practiceFormPage.submittedFieldLabels.get(i).getText().toLowerCase())) {
                softAssert.assertEquals(practiceFormPage.submittedFieldValues.get(i).getText()
                        , context.get(practiceFormPage.submittedFieldLabels.get(i).getText().toLowerCase()));
            } else softAssert.assertEquals(practiceFormPage.submittedFieldValues.get(i).getText(), "");
        }
        softAssert.assertAll();
    }

    @When("I populate all missing mandatory fields")
    public void populateMandatoryFields(DataTable table) throws InterruptedException {

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        if (!context.containsKey(FIRST_NAME)) {
            populateTextField(rows.get(0).get(FIRST_NAME), "first name");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        if (!context.containsKey(LAST_NAME)) {
            populateTextField(rows.get(0).get(LAST_NAME), "last name");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        if (!context.containsKey(GENDER)) {
            selectGender(rows.get(0).get(GENDER));
            TimeUnit.MILLISECONDS.sleep(500);
        }
        if (!context.containsKey(MOBILE_NUMBER)) {
            populateTextField(rows.get(0).get(MOBILE_NUMBER), "mobile number");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    @When("I populate all mandatory fields except {string}")
    public void populateMandatoryFieldsExcept(String field) {
        switch (field) {
            case "first name":
                populateTextField("Smith", "last name");
                selectGender("Male");
                populateTextField("1234421123", "mobile number");
                break;
            case "last name":
                populateTextField("Carlos", "first name");
                selectGender("Male");
                populateTextField("1234421123", "mobile number");
                break;
            case "gender":
                populateTextField("Sheldon", "first name");
                populateTextField("Cooper", "last name");
                populateTextField("1234421123", "mobile number");
                break;
            case "mobile number":
                populateTextField("Lucifer", "first name");
                populateTextField("Morningstar", "last name");
                selectGender("Male");
                break;
            default:
        }
    }

    @When("I close the submission modal")
    public void closeModal() {
        practiceFormPage.scrollToElement(practiceFormPage.closeModalButton);
        practiceFormPage.closeModalButton.click();
        context.clear();
    }
}
