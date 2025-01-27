package business.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.driver.DriverManager.getDriver;

public class MainPage extends BasePage<MainPage> {

    @FindBy(xpath = "//h5[text()='Elements']")
    @CacheLookup
    private WebElement elements;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step ("Go to Elements section")
    public ElementsPage goToElements() {
        disableAds();
        scrollToElement(elements);
        elements.click();
        return new ElementsPage(getDriver());
    }
}
