package org.slieb.selenium;

import org.openqa.selenium.Platform;

import java.net.MalformedURLException;
import java.net.URL;

public class SystemDriverConfiguration implements DriverProvider.Configuration {

    public static final String
            MODE = "driverProvider.mode",
            GRID_URL = "driverProvider.gridUrl",
            PLATFORM = "driverProvider.platform",
            BROWSER = "driverProvider.browser",
            BROWSER_NAME = "driverProvider.browserName",
            BROWSER_VERSION = "driverProvider.browserVersion";

    public static final String DEFAULT_GRID = "http://localhost:4444/wd/hub";

    public DriverProvider.Mode getMode() {
        return findValue(System.getProperty(MODE), DriverProvider.Mode.values(), DriverProvider.Mode.LOCAL);
    }

    public URL getGridUrl() {
        try {
            return new URL(System.getProperty(GRID_URL, DEFAULT_GRID));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] parts() {
        String browser = System.getProperty(BROWSER);
        if (browser != null) {
            return browser.split(":");
        } else {
            return null;
        }
    }

    @Override
    public DriverProvider.Browser getBrowser() {
        String browserName = System.getProperty(BROWSER_NAME);

        if (browserName == null) {
            String[] browserParts = parts();
            if (browserParts != null && browserParts.length > 0) {
                browserName = browserParts[0];
            }
        }

        return findValue(browserName, DriverProvider.Browser.values(), DriverProvider.Browser.ANY);
    }

    public Platform getPlatform() {
        return findValue(System.getProperty(PLATFORM), Platform.values(), Platform.ANY);
    }

    public String getBrowserVersion() {
        String browserVersion = System.getProperty(BROWSER_VERSION);

        if (browserVersion == null) {
            String[] browserParts = parts();
            if (browserParts != null && browserParts.length > 1) {
                browserVersion = browserParts[1];
            }
        }

        return browserVersion;
    }

    private <T extends Enum> T findValue(String query, T[] enums, T defaultValue) {
        if (query != null) {
            for (final T anEnum : enums) {
                if (query.equalsIgnoreCase(anEnum.name())) {
                    return anEnum;
                }
            }
        }
        return defaultValue;
    }
}


