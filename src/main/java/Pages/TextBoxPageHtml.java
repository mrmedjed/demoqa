package Pages;


import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;
import org.openqa.selenium.WebElement;

public interface TextBoxPageHtml{

    @Description("Full Name text box")
    @FindBy("//input[contains(@id, 'userName')]")
    WebElement fullNameBox();

}
