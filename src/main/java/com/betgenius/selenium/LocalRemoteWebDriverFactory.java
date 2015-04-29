package com.betgenius.selenium;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * A factory that creates RemoteWebDriver instances for locally installed browsers.
 * Usefull for quick testing against browsers, when installing and running a
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
        return new FirefoxDriver(DesiredCapabilities.firefox());
    }

    /**
     * Creates a instance of firefox driver.
     *
     * @return A firefox driver instance.
     */
    @Override
    public InternetExplorerDriver getInternetExplorerDriver() {
        return new InternetExplorerDriver(DesiredCapabilities.internetExplorer());
    }

    /**
     * Creates a new instance of chrome driver.
     *
     * @return a ChromeDriver instance.
     */
    @Override
    public ChromeDriver getChromeDriver() {
        return new ChromeDriver(DesiredCapabilities.chrome());
    }

    /**
     * Creates a new instance of safari webdriver.
     *
     * @return a Safari Driver instance.
     */
    @Override
    public SafariDriver getSafariDriver() {
        return new SafariDriver(DesiredCapabilities.safari());
    }

    /**
     * Creates a new instance of phantomjs driver.
     *
     * @return a phantomjs instance.
     */
    @Override
    public PhantomJSDriver getPhantomDriver() {
        return new PhantomJSDriver(DesiredCapabilities.phantomjs());
    }

}
