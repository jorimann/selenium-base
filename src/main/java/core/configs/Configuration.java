package core.configs;

import org.aeonbits.owner.Config;
import org.checkerframework.checker.units.qual.K;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties","classpath:config-default.properties", "classpath:allure.properties"})
public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("pageLoadTimeoutInSeconds")
    int pageLoadTimeoutInSeconds();

    @Key("implicitWaitInSeconds")
    int implicitWaitInSeconds();

    @Key("url")
    String url();

    @Key("browser.height")
    int browserHeight();

    @Key("browser.width")
    int browserWidth();

    @Key("hub.url")
    String hubUrl();

    @Key("hub.browser")
    String hubBrowser();

    @Key("hub.browser.version")
    String hubBrowserVersion();

    @Key("db.jdbc.url")
    String dbJdbcUrl();

    @Key("db.username")
    String dbUsername();

    @Key("db.password")
    String dbPassword();

    @Key("api.baseUrl")
    String apiBaseUrl();
}
