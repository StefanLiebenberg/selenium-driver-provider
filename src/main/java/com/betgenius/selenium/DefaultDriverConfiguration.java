package com.betgenius.selenium;


import org.openqa.selenium.Platform;

import java.net.URL;

public class DefaultDriverConfiguration implements DriverProvider.Configuration {


    URL gridUrl;

    DriverProvider.Mode mode;

    DriverProvider.Browser browser;

    Platform platform;

    String browserVersion = null;

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
