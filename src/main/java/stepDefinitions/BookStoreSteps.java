package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import pages.BooksPage;

import java.util.*;

import static lib.utils.Constants.*;

public class BookStoreSteps {
    Map<String, String> context = new HashMap<>();
    public static Map<String, List<String>> bookContext = new HashMap<>();
    static BooksPage booksPage;

    @Before
    public void init() {
        booksPage = new BooksPage(WebDriverSteps.getDriver());
    }

    @After
    public void quit() {
        context.clear();
    }

    @When("I enter {string} into search box")
    public void method(String search) {
        booksPage.searchField.sendKeys(search);
        context.put(SEARCH_VALUE, search.toLowerCase());
    }

    public static void getInitialBooksList() {
        List<String> bookNames = new ArrayList<>();
        List<String> bookAuthors = new ArrayList<>();
        List<String> bookPublishers = new ArrayList<>();

        for (int i = 0; i < booksPage.listOfDisplayedBooks.size(); i++) {
            bookNames.add(booksPage.listOfDisplayedBooks.get(i).getText().toLowerCase());
            bookAuthors.add(booksPage.listOfAuthorsForDisplayedBooks.get(i).getText().toLowerCase());
            bookPublishers.add(booksPage.listOfPublishersForDisplayedBooks.get(i).getText().toLowerCase());
        }
        bookContext.put(BOOKS, bookNames);
        bookContext.put(AUTHORS, bookAuthors);
        bookContext.put(PUBLISHERS, bookPublishers);
    }

    @Then("only books which meet the search criteria are displayed")
    public void verifyListOfBooksAfterSearch() {
        int bookCount = 0;

        for (int i = 0; i < bookContext.get(BOOKS).size(); i++) {
            if (bookContext.get(BOOKS).get(i).contains(context.get(SEARCH_VALUE))
            || bookContext.get(AUTHORS).get(i).contains(context.get(SEARCH_VALUE))
            || bookContext.get(PUBLISHERS).get(i).contains(context.get(SEARCH_VALUE)))  {
                bookCount += 1;
            }
        }
        Assert.assertEquals(bookCount, booksPage.listOfDisplayedBooks.size());
    }

    @When("I click on {string} column")
    public void clickOnColumnTitle(String title) {
        WebDriverSteps.getDriver().findElement(By.xpath(String.format(booksPage.HEADER_TITLE_XPATH, title))).click();
    }

    @Then("books are sorted by {string} in {string} order")
    public void verifyBooksAreSorted(String column, String order) {
        switch (String.format("%s %s", column, order)) {
            case "Title ascending":
                Collections.sort(bookContext.get(BOOKS));
                Assert.assertEquals(bookContext.get(BOOKS), booksPage.convert(booksPage.listOfDisplayedBooks));
                break;
            case "Title descending":
                bookContext.get(BOOKS).sort(Collections.reverseOrder());
                Assert.assertEquals(bookContext.get(BOOKS), booksPage.convert(booksPage.listOfDisplayedBooks));
                break;
            case "Author ascending":
                Collections.sort(bookContext.get(AUTHORS));
                Assert.assertEquals(bookContext.get(AUTHORS), booksPage.convert(booksPage.listOfAuthorsForDisplayedBooks));
                break;
            case "Author descending":
                bookContext.get(AUTHORS).sort(Collections.reverseOrder());
                Assert.assertEquals(bookContext.get(AUTHORS), booksPage.convert(booksPage.listOfAuthorsForDisplayedBooks));
                break;
            case "Publisher ascending":
                Collections.sort(bookContext.get(PUBLISHERS));
                Assert.assertEquals(bookContext.get(PUBLISHERS), booksPage.convert(booksPage.listOfPublishersForDisplayedBooks));
                break;
            case "Publisher descending":
                bookContext.get(PUBLISHERS).sort(Collections.reverseOrder());
                Assert.assertEquals(bookContext.get(PUBLISHERS), booksPage.convert(booksPage.listOfPublishersForDisplayedBooks));
                break;
            default:
        }
    }

    @When("I click on random book")
    public void clickOnRandomBook() {
        int random = (int) (Math.random() * ((booksPage.listOfDisplayedBooks.size() - 1))) + 1;

        context.put(BOOK_TITLE ,booksPage.listOfDisplayedBooks.get(random).getText());
        context.put(AUTHOR_NAME ,booksPage.listOfAuthorsForDisplayedBooks.get(random).getText());
        context.put(PUBLISHER_NAME ,booksPage.listOfPublishersForDisplayedBooks.get(random).getText());

        booksPage.scrollToElement(booksPage.listOfDisplayedBooks.get(random));
        booksPage.listOfDisplayedBooks.get(random).click();
    }

    @Then("appropriate book page is opened")
    public void appropriateBookPageIsOpened() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(booksPage.bookTitle.getText().contains(context.get(BOOK_TITLE)));
        soft.assertTrue(booksPage.bookAuthor.getText().contains(context.get(AUTHOR_NAME)));
        soft.assertTrue(booksPage.bookPublisher.getText().contains(context.get(PUBLISHER_NAME)));
        soft.assertTrue(booksPage.isbn.getText().contains(WebDriverSteps.getDriver().getCurrentUrl().split("=")[1]));

        soft.assertAll();
    }
}
