package org.slieb.selenium;

import org.openqa.selenium.Platform;

import java.net.URL;

@SuppressWarnings("WeakerAccess")
public class DefaultDriverConfiguration implements DriverProvider.Configuration {

    private URL gridUrl;

    private DriverProvider.Mode mode;

    private DriverProvider.Browser browser;

    private Platform platform;

    private String browserVersion = null;

    @Override
    public DriverProvider.Mode getMode() {
        if (mode != null) {
            return mode;
        } else {
            return DriverProvider.Mode.LOCAL;
        }
    }

    public void setMode(DriverProvider.Mode mode) {
        this.mode = mode;
    }

    @Override
    public URL getGridUrl() {
        return gridUrl;
    }

    public void setGridUrl(URL gridUrl) {
        this.gridUrl = gridUrl;
    }

    @Override
    public DriverProvider.Browser getBrowser() {
        if (browser != null) {
            return browser;
        } else {
            return DriverProvider.Browser.ANY;
        }
    }

    public void setBrowser(DriverProvider.Browser browser) {
        this.browser = browser;
    }

    @Override
    public Platform getPlatform() {
        if (platform != null) {
            return platform;
        } else {
            return Platform.ANY;
        }
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }
}
