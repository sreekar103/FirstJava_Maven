package com.first.group.com.first.artifact.testscripts;

import io.testsmith.support.listeners.*;
import com.first.group.com.first.artifact.utils.BrowserUtil;
import com.first.group.com.first.artifact.utils.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotListener.class)
public abstract class TestBase {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass
    public void setup() {
        final String browser = System.getProperty("browser", "chrome");
        WebDriver originalDriver = BrowserUtil.createDriver(browser);
        driver.set(new EventFiringDecorator(
                new WebDriverLoggingListener(),
                new SavePageSourceOnExceptionListener(originalDriver, "target/log/pagesources"),
                new SaveScreenshotOnExceptionListener(originalDriver, "target/log/screenshots"),
                new HighlightElementsListener()
        ).decorate(originalDriver));
    }

    @AfterClass
    public void teardown() {
        getDriver().quit();
    }

}
