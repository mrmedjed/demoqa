package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.File;
import java.util.Arrays;

import static lib.utils.Constants.*;

public class UploadDownloadPage extends BasePage{

    public UploadDownloadPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "downloadButton")
    public WebElement downloadButton;

    @FindBy(id = "uploadFile")
    public WebElement uploadFile;

    @FindBy(id = "uploadedFilePath")
    public WebElement uploadedFilePath;

    public String getFilePathByFormat(String fileExtension) {
        File file = new File(UPLOAD_PATH);
        File[] listOfFiles = file.listFiles();
        return Arrays.stream(listOfFiles).filter(f -> f.getName().contains(fileExtension))
                .findFirst().get().getAbsolutePath();
    }

    public boolean isFilePresent(String fileName) {
        File file = new File(String.format("%s/%s", DOWNLOAD_PATH, fileName));
        return file.exists();
    }

    public void deleteFileIfExists(String fileName) {
        File file = new File(String.format("%s/%s", DOWNLOAD_PATH, fileName));
        if (file.exists()) {
            file.delete();
        }
    }

}
