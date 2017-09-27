package org.slieb.selenium;


import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * An abstract class for remoteWebDriverFactory.
 */
public abstract class RemoteWebDriverFactory {

    /**
     * @return the firefox driver.
     */
    public abstract RemoteWebDriver getFirefoxDriver();

    public abstract RemoteWebDriver getInternetExplorerDriver();

    public abstract RemoteWebDriver getChromeDriver();

    public abstract RemoteWebDriver getChromeHeadlessDriver();

    public abstract RemoteWebDriver getSafariDriver();

    public abstract RemoteWebDriver getPhantomDriver();

    public RemoteWebDriver getAnyDriver() {
        try {
            return getFirefoxDriver();
        } catch (Exception ignored) {
        }

        try {
            return getChromeDriver();
        } catch (Exception ignored) {
        }

        try {
            return getChromeHeadlessDriver();
        } catch (Exception ignored) {
        }

        try {
            return getInternetExplorerDriver();
        } catch (Exception ignored) {
        }

        try {
            return getSafariDriver();
        } catch (Exception ignored) {
        }

        try {
            return getPhantomDriver();
        } catch (Exception ignored) {
        }

        throw new RuntimeException("No browsers found!");
    }


}
