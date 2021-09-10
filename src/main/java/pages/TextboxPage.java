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

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(id = "name")
    public WebElement submittedName;

}
