package com.betgenius.selenium;


import org.openqa.selenium.Platform;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class SystemDriverConfiguration implements DriverProvider.Configuration {

    public static final String
            MODE = "driverProvider.mode",
            GRID_URL = "driverProvider.gridUrl",
            BROWSER = "driverProvider.browser",
            PLATFORM = "driverProvider.platform",
            VERSION = "driverProvider.version";

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

    public DriverProvider.Browser getBrowser() {
        return findValue(System.getProperty(BROWSER), DriverProvider.Browser.values(), DriverProvider.Browser.ANY);
    }

    public Platform getPlatform() {
        return findValue(System.getProperty(PLATFORM), Platform.values(), Platform.ANY);
    }

    public String getBrowserVersion() {
        return System.getProperty(VERSION, null);
    }

    private <T extends Enum> T findValue(String query, T[] enums, T defaultValue) {
        if (query != null) {
            return Arrays.asList(enums)
                    .stream()
                    .filter(m -> m.name().equalsIgnoreCase(query))
                    .findFirst().orElse(defaultValue);
        } else {
            return defaultValue;
        }
    }

}


