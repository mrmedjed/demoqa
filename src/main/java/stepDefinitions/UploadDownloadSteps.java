package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.UploadDownloadPage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static lib.utils.Constants.*;

public class UploadDownloadSteps {

        Map<String, String> context;
        UploadDownloadPage uploadDownloadPage;

        @Before
        public void init() {
            uploadDownloadPage = new UploadDownloadPage(WebDriverSteps.getDriver());
            context = new HashMap<>();
        }
        @After
        public void quit() {
            context.clear();
        }


        @Given("I upload a file type {string}")
        public void uploadFile(String fileType) {
            uploadDownloadPage.uploadFile.sendKeys(UploadDownloadPage.getFilePathByFormat(fileType));
        }

        @Then("appropriate message is displayed")
    public void message() {
            uploadDownloadPage.uploadedFilePath.isDisplayed();
        }

        @Given("I download a file")
    public void downloadFile() throws InterruptedException {
            uploadDownloadPage.deleteFileIfExists(FILE_NAME_JPEG);
            uploadDownloadPage.waitForElementToBeVisible(uploadDownloadPage.downloadButton);
            uploadDownloadPage.downloadButton.click();
            TimeUnit.MILLISECONDS.sleep(2000);
        }

        @Then("verify that file is downloaded")
    public void verifyFileIsDownloaded() {
            Assert.assertTrue(uploadDownloadPage.isFilePresent(FILE_NAME_JPEG));
        }


    }
