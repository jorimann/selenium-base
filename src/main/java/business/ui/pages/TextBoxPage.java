package business.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage extends BasePage<TextBoxPage> {

    @FindBy(id = "userName")
    @CacheLookup
    private WebElement userName;

    @FindBy(id = "userEmail")
    @CacheLookup
    private WebElement email;

    @FindBy(id = "currentAddress")
    @CacheLookup
    private WebElement currentAddress;

    @FindBy(id = "permanentAddress")
    @CacheLookup
    private WebElement permanentAddress;

    @FindBy(id = "submit")
    @CacheLookup
    private WebElement submit;

    @FindBy(id = "output")
    @CacheLookup
    private WebElement output;

    public TextBoxPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Submit Form")
    public TextBoxPage submit(){
        scrollToElement(submit);
        submit.click();
        return this;
    }

    @Step("populate UserName with: ")
    public TextBoxPage fillUserName(final String userName) {
        sendKeys(this.userName, userName);
        return this;
    }

    @Step("populate Email with: ")
    public TextBoxPage fillEmail(final String email) {
        sendKeys(this.email, email);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + email + "')", output);
        return this;
    }

    @Step("populate Current Address")
    public TextBoxPage fillCurrentAddress(final String currentAddress) {
        sendKeys(this.currentAddress, currentAddress);
        return this;
    }

    @Step("populate Permanent Address")
    public TextBoxPage fillPermanentAddress(final String permanentAddress) {
        sendKeys(this.permanentAddress, permanentAddress);
        return this;
    }

    public String getOutput() {
        return getText(output);
    }
}
