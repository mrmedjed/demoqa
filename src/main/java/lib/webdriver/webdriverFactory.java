package lib.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lib.utils.ConfigProperties;
import lib.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class webdriverFactory {

    public static WebDriver getDriver(String resolution) {

        WebDriver driver = null;
        String browser = ConfigProperties.getBrowser();
        String downloadFilepath = Constants.DOWNLOAD_PATH;


        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap< >();
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.default_directory", downloadFilepath);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-gpu");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            options.merge(cap);
            if (resolution.equals("pcBrowser") && ConfigProperties.getHeadless()) {
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--headless");
            } else if (resolution.equals("pcBrowser") && !ConfigProperties.getHeadless()) {
                options.addArguments("--start-maximized");
            }
            driver = new ChromeDriver(options);
        }
        assert driver != null;
        driver.manage().deleteAllCookies();
        return driver;
    }
}
