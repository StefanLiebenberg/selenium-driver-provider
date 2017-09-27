package org.slieb.selenium;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridRemoteWebDriverFactory extends RemoteWebDriverFactory {

    private final DriverProvider.Configuration configurator;

    public GridRemoteWebDriverFactory(DriverProvider.Configuration configurator) {
        this.configurator = configurator;
    }

    public RemoteWebDriver getRemoteDriver(DesiredCapabilities d) {

        String browserVersion = configurator.getBrowserVersion();
        if (browserVersion != null) {
            d.setVersion(browserVersion);
        }

        d.setPlatform(configurator.getPlatform());
        return new RemoteWebDriver(configurator.getGridUrl(), d);
    }

    public RemoteWebDriver getFirefoxDriver() {
        return getRemoteDriver(DesiredCapabilities.firefox());
    }

    public RemoteWebDriver getChromeDriver() {
        return getRemoteDriver(DesiredCapabilities.chrome());
    }

    @Override
    public RemoteWebDriver getChromeHeadlessDriver() {
        throw new UnsupportedOperationException("Headless not available in the grid mode.");
    }

    public RemoteWebDriver getPhantomDriver() {
        return getRemoteDriver(DesiredCapabilities.phantomjs());
    }

    public RemoteWebDriver getSafariDriver() {
        return getRemoteDriver(DesiredCapabilities.safari());
    }

    public RemoteWebDriver getInternetExplorerDriver() {
        return getRemoteDriver(DesiredCapabilities.internetExplorer());
    }

}
