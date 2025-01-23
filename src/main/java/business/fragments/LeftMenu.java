package business.fragments;

import business.pages.DynamicPropertiesPage;
import io.qameta.allure.Step;
import business.pages.TextBoxPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static core.driver.DriverManager.getDriver;

public class LeftMenu {
    Logger LOGGER = LoggerFactory.getLogger(LeftMenu.class.getName());
    WebDriver driver;

    @FindBy(xpath = "//ul/li/span[text()='Text Box']")
    private WebElement textBoxMenuItem;

    @FindBy(xpath = "//ul/li/span[text()='Dynamic Properties']")
    private WebElement dynamicPropertiesMenuItem;

    public LeftMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Go to Text Box page")
    public TextBoxPage goToTextBox() {
        textBoxMenuItem.click();
        return new TextBoxPage(driver);
    }

    @Step("Go to Dynamic Properties page")
    public DynamicPropertiesPage goDynamicProperties() {
        disableAds();
        dynamicPropertiesMenuItem.click();
        return new DynamicPropertiesPage(driver);
    }

    protected void disableAds(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("const elements = document.getElementsByClassName('pn abgf'); while (elements.length > 0) elements[0].remove()");
    }
}