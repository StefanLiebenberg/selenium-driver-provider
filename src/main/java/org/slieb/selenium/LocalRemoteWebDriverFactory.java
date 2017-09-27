package org.slieb.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * A factory that creates RemoteWebDriver instances for locally installed browsers.
 * Useful for quick testing against browsers, when installing and running a
 * selenium server is too much effort.
 */
public class LocalRemoteWebDriverFactory extends RemoteWebDriverFactory {

    /**
     * Creates a instance of firefox driver.
     *
     * @return A firefox driver instance.
     */
    @Override
    public FirefoxDriver getFirefoxDriver() {
        try {
            return new FirefoxDriver(DesiredCapabilities.firefox());
        } catch (RuntimeException exception) {
            throw new BrowserInstantiationException("Failed to start firefox driver", exception);
        }
    }

    /**
     * Creates a instance of firefox driver.
     *
     * @return A firefox driver instance.
     */
    @Override
    public InternetExplorerDriver getInternetExplorerDriver() {
        try {
            return new InternetExplorerDriver(DesiredCapabilities.internetExplorer());
        } catch (RuntimeException exception) {
            throw new BrowserInstantiationException("Failed to start internet explorer driver", exception);
        }
    }

    /**
     * Creates a new instance of chrome driver.
     *
     * @return a ChromeDriver instance.
     */
    @Override
    public ChromeDriver getChromeDriver() {
        try {
            return new ChromeDriver(DesiredCapabilities.chrome());
        } catch (RuntimeException exception) {
            throw new BrowserInstantiationException("Failed to start chrome driver", exception);
        }
    }

    /**
     * Creates a new instance of chrome driver.
     *
     * @return a ChromeDriver instance.
     */
    @Override
    public ChromeDriver getChromeHeadlessDriver() {
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-gpu");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(  "chromeOptions", chromeOptions);

            return new ChromeDriver(capabilities);
        } catch (RuntimeException exception) {
            throw new BrowserInstantiationException("Failed to start chrome driver", exception);
        }
    }

    /**
     * Creates a new instance of safari web driver.
     *
     * @return a Safari Driver instance.
     */
    @Override
    public SafariDriver getSafariDriver() {
        try {
            return new SafariDriver(DesiredCapabilities.safari());
        } catch (RuntimeException exception) {
            throw new BrowserInstantiationException("Failed to start safari browser", exception);
        }
    }

    /**
     * Creates a new instance of phantomjs driver.
     *
     * @return a phantomjs instance.
     */
    @Override
    public PhantomJSDriver getPhantomDriver() {
        try {
            return new PhantomJSDriver(DesiredCapabilities.phantomjs());
        } catch (RuntimeException exception) {
            throw new BrowserInstantiationException("Failed to start phantomjs browser", exception);
        }
    }
}
