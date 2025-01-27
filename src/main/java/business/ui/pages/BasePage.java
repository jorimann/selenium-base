package business.ui.pages;

import business.ui.fragments.LeftMenu;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage<T> {
    WebDriver driver;
    Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected LeftMenu menu;

    protected T sendKeys(WebElement element, String text) {
        element.sendKeys(text);
        return (T) this;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        menu = new LeftMenu(driver);
        removeIframes();
    }

    protected String getText(WebElement element) {
        return element.getText();
    }

    public LeftMenu getMenu(){
        return menu;
    }

    protected Boolean isElementEnabledAfterWait(WebElement element, Duration duration) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, duration);
            return wait.until(driver -> element.isEnabled());
        } catch (TimeoutException e) {
            LOGGER.error("Element did not become enabled after wait", e);
            return false;
        }
    }

    protected void disableAds(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("const elements = document.getElementsByClassName('pn abgf'); while (elements.length > 0) elements[0].remove()");
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    protected void removeIframes(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("Array.from(document.getElementsByTagName('iframe')).forEach(iframe => iframe.parentNode.removeChild(iframe));");
    }

    protected Boolean isElementVisibleAfterWait(WebElement element, Duration duration) {
        try{
            Wait<WebDriver> wait = new FluentWait(driver)
                    .withTimeout(duration)
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            return wait.until(driver -> element.isDisplayed());
        } catch(Exception e){
            LOGGER.error("Element did not become visible after wait", e);
            return false;
        }
    }
}
