package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BooksPage extends BasePage{

    public BooksPage(WebDriver driver) {
        super(driver);
    }
    public String HEADER_TITLE_XPATH = "//div[contains(@class, 'header-content')][text()='%s']";

    @FindBy(id = "searchBox")
    public WebElement searchField;

    @FindBy(xpath = "//span[contains(@id, 'see-book')]")
    public List<WebElement> listOfDisplayedBooks;

    @FindBy(xpath = "//div[contains(@class, 'rt-td')]/parent::div[not(contains(@class, '-padRow'))]//div[normalize-space(text())][1]")
    public List<WebElement> listOfAuthorsForDisplayedBooks;

    @FindBy(xpath = "//div[contains(@class, 'rt-td')]/parent::div[not(contains(@class, '-padRow'))]//div[normalize-space(text())][2]")
    public List<WebElement> listOfPublishersForDisplayedBooks;

    @FindBy(xpath = "//div[@id = 'title-wrapper']")
    public WebElement bookTitle;

    @FindBy(xpath = "//div[@id = 'author-wrapper']")
    public WebElement bookAuthor;

    @FindBy(xpath = "//div[@id = 'publisher-wrapper']")
    public WebElement bookPublisher;

    @FindBy(xpath = "//div[@id = 'ISBN-wrapper']")
    public WebElement isbn;

    public List<String> convert(List<WebElement> listOfElements) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : listOfElements) {
            stringList.add(element.getText().toLowerCase());
        }
        return stringList;
    }
}
