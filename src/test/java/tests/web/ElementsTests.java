package tests.web;

import business.ui.BaseTest;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import business.ui.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static core.driver.DriverManager.getDriver;

public class ElementsTests extends BaseTest {
    Logger LOGGER = LoggerFactory.getLogger(ElementsTests.class);

    @Test
    @DisplayName("Form Submission")
    @Description("This test verifies that user can send form")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yevhen Kravchenko")
    @Link(name="Website", url="https://google.com")
    @Issue("JIRA-1234")
    @TmsLink("TMS-456")
    void testSubmitForm() {
        MainPage mainPage = new MainPage(getDriver());
        String result = mainPage.goToElements()
                .getMenu().goToTextBox()
                .fillUserName("user")
                .fillCurrentAddress("address")
                .fillEmail("email@gmail.com")
                .fillPermanentAddress("permanent address")
                .submit()
                .getOutput();

        Assertions.assertThat(result).contains("Name:user");
        Assertions.assertThat(result).contains("Email:email@gmail.com");
        Assertions.assertThat(result).contains("Current Address :address");
        Assertions.assertThat(result).contains("Permananet Address :permanent address");
    }

    @Test
    @DisplayName("Form Submission with Fail")
    @Description("This test verifies that user can send form")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yevhen Kravchenko")
    @Link(name="Website", url="https://google.com")
    @Issue("JIRA-1234")
    @TmsLink("TMS-456")
    void testSubmitFormExpectedFailed() {
        MainPage mainPage = new MainPage(getDriver());
        String result = mainPage.goToElements()
                .getMenu().goToTextBox()
                .fillUserName("user")
                .fillCurrentAddress("address")
                .fillEmail("email@gmail.com")
                .fillPermanentAddress("permanent address")
                .submit()
                .getOutput();
        Assertions.assertThat(result).contains("Name:fail");
        Assertions.assertThat(result).contains("Email:email@gmail.com");
        Assertions.assertThat(result).contains("Current Address :address");
        Assertions.assertThat(result).contains("Permananet Address :permanent address");
    }
}
