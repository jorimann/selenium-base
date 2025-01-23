package core.reporting;

import io.qameta.allure.Attachment;
import core.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureReportManager {

    @Attachment(value="Screenshot", type="image/png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
