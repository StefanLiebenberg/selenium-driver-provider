package org.slieb.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.function.Supplier;

import static org.junit.Assume.assumeNoException;

public class LocalRemoteWebDriverFactoryIntegrationTest {

    private static final String URL = "http://example.com";

    private LocalRemoteWebDriverFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new LocalRemoteWebDriverFactory();
    }

    @Test
    public void startSafariTest() throws InterruptedException {
        testGenericBrowser(() -> factory.getSafariDriver());
    }

    @Test
    public void startFirefoxTest() throws InterruptedException {
        testGenericBrowser(() -> factory.getFirefoxDriver());
    }

    @Test
    public void startChromeTest() throws InterruptedException {
        testGenericBrowser(() -> factory.getChromeDriver());
    }

    @Test
    public void startIETest() throws InterruptedException {
        testGenericBrowser(() -> factory.getInternetExplorerDriver());
    }

    @Test
    public void startPhantomjsTest() throws InterruptedException {
        testGenericBrowser(() -> factory.getPhantomDriver());
    }

    public void testGenericBrowser(Supplier<RemoteWebDriver> supplier) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            remoteWebDriver = supplier.get();
            remoteWebDriver.get(URL);
            remoteWebDriver.close();
        } catch (BrowserInstantiationException ignored) {
            assumeNoException(ignored);
        } finally {
            if (remoteWebDriver != null) {
                remoteWebDriver.quit();
            }
        }
    }
}
