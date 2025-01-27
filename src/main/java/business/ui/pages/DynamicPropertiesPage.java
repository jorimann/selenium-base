package business.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static core.driver.DriverManager.getDriver;

public class DynamicPropertiesPage extends BasePage<DynamicPropertiesPage> {
    public DynamicPropertiesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id= "enableAfter")
    @CacheLookup
    private WebElement enableAfterButton;

    @FindBy(id= "visibleAfter")
    @CacheLookup
    private WebElement visibleAfterButton;

    @Step("check is Button enabled after timeout")
    public Boolean isButtonEnabledAfterTimeout(Duration duration) {
        return isElementEnabledAfterWait(enableAfterButton, duration);
    }

    @Step("check is Button visible after timeout")
    public Boolean isButtonVisibleAfterWait(Duration duration) {
        return isElementVisibleAfterWait(visibleAfterButton, duration);
    }

    @Step("check attribute Property invoking Java Script")
    private String getProperty(WebElement element, String property) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript("return arguments[0]." + property + ";", element).toString();
    }

    public String getPropertyDisabledFromButton(){
        return getProperty(enableAfterButton, "disabled");
    }
}
