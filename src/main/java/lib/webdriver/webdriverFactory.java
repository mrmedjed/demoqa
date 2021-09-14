package lib.webdriver;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import lib.utils.ConfigProperties;
import lib.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class webdriverFactory {

    public static WebDriver getDriver(String resolution) {

        WebDriver driver = null;
        String browser = ConfigProperties.getBrowser();
        String downloadFilepath = Constants.DOWNLOAD_PATH;
        /**
         * Setup for chrome driver
         */
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
            driver.manage().deleteAllCookies();
        }
        /**
         * Setup for safari driver
         */
        if (browser.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            driver = new SafariDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
        /**
         * Setup for edge driver
         */
        if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "/src/main/java/resources/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
        /**
         * Setup for firefox driver
         */
        if (browser.equalsIgnoreCase("firefox")) {
            String firefoxProfile = "default";
            System.setProperty("webdriver.firefox.profile", firefoxProfile);

            FirefoxProfile profile = new ProfilesIni().getProfile(firefoxProfile);

            String version = System.getProperty("webdriver.firefox.driver.version");
            if (StringUtils.isNotBlank(version)) {
                FirefoxDriverManager.firefoxdriver().version(version).setup();
            } else {
                FirefoxDriverManager.firefoxdriver().setup();
            }

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL);
            firefoxOptions.setCapability(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, true);
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxOptions.setProfile(profile);
            String ffBinaryPath = System.getProperty("webdriver.firefox.bin");
            if (StringUtils.isNotBlank(ffBinaryPath)) {
                firefoxOptions.setBinary(ffBinaryPath);
            }
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        assert driver != null;
        return driver;
    }
}
