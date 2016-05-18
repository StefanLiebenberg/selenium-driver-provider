package org.slieb.selenium;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;

public class LocalRemoteWebDriverFactoryIntegrationTest {

    private static final String URL = "http://example.com";

    private LocalRemoteWebDriverFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new LocalRemoteWebDriverFactory();
    }

    @Test
    public void startSafariTest() throws InterruptedException {
        try {
            final SafariDriver safari = factory.getSafariDriver();
            safari.get(URL);
            safari.close();
        } catch (BrowserInstantiationException ignored) {
            Assume.assumeNoException(ignored);
        }
    }

    @Test
    public void startFirefoxTest() throws InterruptedException {
        try {
            final FirefoxDriver firefox = factory.getFirefoxDriver();
            firefox.get(URL);
            firefox.close();
        } catch (BrowserInstantiationException ignored) {
            Assume.assumeNoException(ignored);
        }
    }

    @Test
    public void startChromeTest() throws InterruptedException {
        try {
            final ChromeDriver chrome = factory.getChromeDriver();
            chrome.get(URL);
            chrome.close();
        } catch (BrowserInstantiationException ignored) {
            Assume.assumeNoException(ignored);
        }
    }

    @Test
    public void startIETest() throws InterruptedException {
        try {
            final InternetExplorerDriver ie = factory.getInternetExplorerDriver();
            ie.get(URL);
            ie.close();
        } catch (BrowserInstantiationException ignored) {
            Assume.assumeNoException(ignored);
        }
    }

    @Test
    public void startPhantomjsTest() throws InterruptedException {
        try {
            final PhantomJSDriver phantomjs = factory.getPhantomDriver();
            phantomjs.get(URL);
            phantomjs.close();
        } catch (BrowserInstantiationException ignored) {
            Assume.assumeNoException(ignored);
        }
    }
}
