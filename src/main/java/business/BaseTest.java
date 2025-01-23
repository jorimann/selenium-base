package business;

import core.driver.DriverFactory;
import core.reporting.ScreenshotExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static core.configs.ConfigurationManager.config;
import static core.driver.DriverManager.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {
    Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    protected void beforeAll() throws IOException {
        setDriver(DriverFactory.getInstance(config().browser()));
    }

    @BeforeEach
    protected void beforeEach() throws IOException {
        getDriver().get(config().url());
    }

    @AfterEach
    protected void afterEach() throws IOException {
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().refresh();
    }

    @AfterAll
    protected void afterAll() {
        closeDriver();
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
