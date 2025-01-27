package tests.web;

import business.ui.BaseTest;
import business.ui.pages.DynamicPropertiesPage;
import business.ui.pages.MainPage;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static core.driver.DriverManager.getDriver;

public class WorkWithWaitsAndJSTests extends BaseTest {
    Logger LOGGER = LoggerFactory.getLogger(WorkWithWaitsAndJSTests.class);

    @Test
    @DisplayName("Check element is enabled after 5 seconds")
    @Description("Element should become enabled after 5 seconds since navigation to page")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yevhen Kravchenko")
    @Link(name="Website", url="https://google.com")
    @Issue("JIRA-1235")
    @TmsLink("TMS-457")
    void testCheckElementIsEnabled() {
        MainPage mainPage = new MainPage(getDriver());
        DynamicPropertiesPage dynamicPropertiesPage = mainPage.goToElements().getMenu().goDynamicProperties();
        Assertions.assertThat(dynamicPropertiesPage.isButtonEnabledAfterTimeout(Duration.ofSeconds(5))).isTrue();
    }

    @Test
    @DisplayName("Check element is visible after 5 seconds")
    @Description("Element should become visible after 5 seconds since navigation to page")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yevhen Kravchenko")
    @Link(name="Website", url="https://google.com")
    @Issue("JIRA-1235")
    @TmsLink("TMS-457")
    void testCheckButtonIsVisibleAfterTimeout() {
        MainPage mainPage = new MainPage(getDriver());
        DynamicPropertiesPage dynamicPropertiesPage = mainPage.goToElements().getMenu().goDynamicProperties();
        Assertions.assertThat(dynamicPropertiesPage.isButtonVisibleAfterWait(Duration.ofSeconds(5))).isTrue();
    }

    @Test
    @DisplayName("Check element is visible after 5 seconds with usage of JavaScript")
    @Description("Element should become visible after 5 seconds since navigation to page")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yevhen Kravchenko")
    @Link(name="Website", url="https://google.com")
    @Issue("JIRA-1235")
    @TmsLink("TMS-457")
    void testCheckButtonPropertyBeforeAndAfterTimeoutUsingJavaScript() {
        MainPage mainPage = new MainPage(getDriver());
        DynamicPropertiesPage dynamicPropertiesPage = mainPage.goToElements().getMenu().goDynamicProperties();
        LOGGER.info("Property disabled = {}", dynamicPropertiesPage.getPropertyDisabledFromButton());
        Assertions.assertThat(dynamicPropertiesPage.isButtonVisibleAfterWait(Duration.ofSeconds(5))).isTrue();
        LOGGER.info("Property disabled = {}", dynamicPropertiesPage.getPropertyDisabledFromButton());
    }
}
