package core.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static core.configs.ConfigurationManager.config;
import static core.driver.DriverManager.getDriver;

public class DriverFactory {
    static Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory(){};

    public static WebDriver getInstance(String browser) throws MalformedURLException {
        if (null == browser) {
            LOGGER.error("browser parameter is null");
            throw new IllegalArgumentException("browser parameter is null");
        } else {
            LOGGER.info("browser parameter is: {}", browser);
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();

        WebDriver driver = switch (browser) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.merge(capabilities);
                yield new FirefoxDriver(options);
            }
            case "ie" -> {
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.merge(capabilities);
                yield new InternetExplorerDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.merge(capabilities);
                yield new ChromeDriver(options);
            }
            case "hub" -> {
                capabilities.setCapability(CapabilityType.BROWSER_NAME, config().hubBrowser());
                capabilities.setCapability(CapabilityType.BROWSER_VERSION, config().hubBrowserVersion());
                yield new RemoteWebDriver(new URL(config().hubUrl()), capabilities, true);
            }
            default -> {
                LOGGER.error("unsupported browser: {}", browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        };

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config().implicitWaitInSeconds()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config().pageLoadTimeoutInSeconds()));

        try {
            LOGGER.info("Preparing driver...");
            Dimension screenSize = new Dimension(config().browserWidth(), config().browserHeight());
            driver.manage().window().setSize(screenSize);
            LOGGER.info("set window size: {}", screenSize);
        } catch (NullPointerException e) {
            driver.manage().window().maximize();
            LOGGER.info("exact window size configs were not found, though maximize window");
        }
        return driver;
    }
}
