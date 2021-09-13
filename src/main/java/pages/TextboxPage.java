package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextboxPage extends BasePage {

    public TextboxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    public WebElement usernameField;

    @FindBy(id = "userEmail")
    public WebElement emailField;

    @FindBy(id = "currentAddress")
    public WebElement currendAddressField;

    @FindBy(id = "permanentAddress")
    public WebElement permanentAddressField;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(id = "name")
    public WebElement submittedName;

    @FindBy(id = "email")
    public WebElement submittedEmail;

    @FindBy(xpath = "//p[@id='currentAddress']")
    public WebElement submittedCurrentAddress;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    public WebElement submittedPermanentAddress;

}
