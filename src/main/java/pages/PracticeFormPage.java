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

    @FindBy(xpath = "//input[@id = 'gender-radio-1']/following-sibling::label")
    public WebElement genderRadioButtonMale;

    @FindBy(xpath = "//input[@id = 'gender-radio-2']/following-sibling::label")
    public WebElement genderRadioButtonFemale;

    @FindBy(xpath = "//input[@id = 'gender-radio-3']/following-sibling::label")
    public WebElement genderRadioButtonOther;

    @FindBy(id = "userNumber")
    public WebElement phoneNumberField;

    @FindBy(id = "dateOfBirthInput")
    public WebElement dateOfBirthField;

    @FindBy(id = "subjectsInput")
    public WebElement subjectsField;

    @FindBy(xpath = "//div[contains(@class, 'multi-value__label')]")
    public WebElement autoCompleteValue;

    @FindBy(xpath = "//div[contains(@class, 'multi-value__remove')]")
    public WebElement autoCompleteRemoveValue;

    @FindBy(xpath = "//div[contains(@class, 'subjects-auto-complete__clear-indicator')]")
    public WebElement autoCompleteRemoveAll;

    @FindBy(xpath = "//input[@id='hobbies-checkbox-1']/following-sibling::label")
    public WebElement hobbiesCheckboxSports;

    @FindBy(xpath = "//input[@id='hobbies-checkbox-2']/following-sibling::label")
    public WebElement hobbiesCheckboxReading;

    @FindBy(xpath = "//input[@id='hobbies-checkbox-3']/following-sibling::label")
    public WebElement hobbiesCheckboxMusic;

    @FindBy(id = "uploadPicture")
    public WebElement uploadPictureButton;

    @FindBy(id = "currentAddress")
    public WebElement currentAddressField;

    @FindBy(id = "state")
    public WebElement stateDropdown;

    @FindBy(xpath = "//div[contains(@class, 'menu')]")
    public WebElement stateDropdownnn;

    @FindBy(id = "city")
    public WebElement cityDropdown;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(id = "example-modal-sizes-title-lg")
    public WebElement submitModalTitle;

    @FindBy(xpath = "//td[1]")
    public List<WebElement> submittedFieldLabels;

    @FindBy(xpath = "//td[2]")
    public List<WebElement> submittedFieldValues;

    @FindBy(id = "closeLargeModal")
    public WebElement closeModalButton;
}
