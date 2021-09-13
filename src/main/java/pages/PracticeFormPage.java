package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PracticeFormPage extends BasePage {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public String DATE_DAY_SELECTION_XPATH = "//div[contains(@class, 'datepicker__day')][text() = '%s']";
    public String DATE_MONTH_SELECTION_XPATH = "//select[contains(@class, 'month-select')]/option[text() = '%s']";
    public String DATE_YEAR_SELECTION_XPATH = "//select[contains(@class, 'year-select')]/option[@value = '%s']";

    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    @FindBy(id = "userEmail")
    public WebElement emailField;

    @FindBy(id = "gender-radio-1")
    public WebElement genderRadioButtonMale;

    @FindBy(id = "gender-radio-2")
    public WebElement genderRadioButtonFemale;

    @FindBy(id = "gender-radio-3")
    public WebElement genderRadioButtonOther;

    @FindBy(id = "userNumber")
    public WebElement phoneNumberField;

    @FindBy(id = "dateOfBirthInput")
    public WebElement dateOfBirthField;

    @FindBy(id = "subjectsContainer")
    public WebElement subjectsField;

    @FindBy(xpath = "//div[contains(@class, 'multi-value__label')]")
    public WebElement autoCompleteValue;

    @FindBy(xpath = "//div[contains(@class, 'multi-value__remove')]")
    public WebElement autoCompleteRemoveValue;

    @FindBy(xpath = "//div[contains(@class, 'subjects-auto-complete__clear-indicator')]")
    public WebElement autoCompleteRemoveAll;

    @FindBy(id = "hobbies-checkbox-1")
    public WebElement hobbiesCheckboxSports;

    @FindBy(id = "hobbies-checkbox-2")
    public WebElement hobbiesCheckboxReading;

    @FindBy(id = "hobbies-checkbox-3")
    public WebElement hobbiesCheckboxMusic;

    @FindBy(id = "uploadPicture")
    public WebElement uploadPictureButton;

    @FindBy(id = "currentAddress")
    public WebElement currentAddressField;

    @FindBy(id = "state")
    public WebElement stateDropdown;

    @FindBy(id = "city")
    public WebElement cityDropdown;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(id = "example-modal-sizes-title-lg")
    public WebElement submitModalTitle;

    @FindBy(id = "//td[1]")
    public List<WebElement> submittedFieldLabels;

    @FindBy(id = "//td[2]")
    public List<WebElement> submittedFieldValues;

    @FindBy(id = "closeLargeModal")
    public WebElement closeModalButton;
}
