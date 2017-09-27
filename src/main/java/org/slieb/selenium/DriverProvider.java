package org.slieb.selenium;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * The driver provider class reads configuration from System properties and provides a web driver.
 */
public class DriverProvider {

    private final RemoteWebDriverFactory factory;

    private final Configuration configurator;

    /**
     * @param factory      A RemoteWebDriver factory.
     * @param configurator A configurator that supplies config options.
     */
    public DriverProvider(RemoteWebDriverFactory factory, Configuration configurator) {
        this.factory = factory;
        this.configurator = configurator;
    }

    /**
     * @return A default instance of DriverProvider.
     */
    public static DriverProvider getSystemConfigDriverProvider() {
        return getDriverProvider(new SystemDriverConfiguration());
    }

    /**
     * @param configurator A configurator instance.
     * @return A instance of driver provider, setup in accordance with mode specified by configurator.
     */
    public static DriverProvider getDriverProvider(Configuration configurator) {
        return new DriverProvider(getFactory(configurator), configurator);
    }

    /**
     * if configurator.getMode() returns GRID, the this method returns a factory that creates remoteWebDriver
     * instances that point to some selenium grid.
     *
     * @param configurator The configurator
     * @return A factory instance.
     */
    protected static RemoteWebDriverFactory getFactory(Configuration configurator) {
        return configurator.getMode() == Mode.GRID ?
                new GridRemoteWebDriverFactory(configurator) : new LocalRemoteWebDriverFactory();
    }

    /**
     * Returns a remote driver instance.
     * <p>Command line options can affect this with</p>
     * <p>
     * -DdriverProvider.borwser=(firefox|chrome|chrome_headless|safari|ie|phantomjs)
     * -DdriverProvider.platform=(linux|windows)
     * -DdriverProvider.version=2.0
     * -DdriverProvider.mode=(grid|local)
     * </p>
     *
     * @return A remote driver instance.
     */
    public RemoteWebDriver getRemoteWebDriver() {
        switch (configurator.getBrowser()) {
            case FIREFOX:
                return factory.getFirefoxDriver();
            case CHROME:
                return factory.getChromeDriver();
            case CHROME_HEADLESS:
                return factory.getChromeHeadlessDriver();
            case IE:
                return factory.getInternetExplorerDriver();
            case SAFARI:
                return factory.getSafariDriver();
            case PHANTOMJS:
                return factory.getPhantomDriver();
            case ANY:
            default:
                return factory.getAnyDriver();
        }
    }

    public interface Configuration {

        Mode getMode();

        URL getGridUrl();

        Browser getBrowser();

        Platform getPlatform();

        String getBrowserVersion();
    }


    public enum Mode {
        LOCAL, GRID
    }

    public enum Browser {
        FIREFOX, CHROME, CHROME_HEADLESS, IE, SAFARI, PHANTOMJS, ANY
    }


}
