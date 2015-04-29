package com.betgenius.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;

import java.util.Arrays;

import static org.junit.Assert.*;


public class SystemDriverConfigurationTest {

    SystemDriverConfiguration config;

    @Before
    public void setup() {
        System.clearProperty(SystemDriverConfiguration.GRID_URL);
        System.clearProperty(SystemDriverConfiguration.BROWSER);
        System.clearProperty(SystemDriverConfiguration.PLATFORM);
        config = new SystemDriverConfiguration();
    }

    @Test
    public void testGetMode() throws Exception {
        assertEquals(DriverProvider.Mode.LOCAL, config.getMode());
    }

    @Test
    public void testGetGridUrl() throws Exception {
        assertNotNull(config.getGridUrl());
        assertEquals(SystemDriverConfiguration.DEFAULT_GRID, config.getGridUrl().toString());
    }

    @Test(expected = RuntimeException.class)
    public void testGetGridUrlException() throws Exception {
        System.setProperty(SystemDriverConfiguration.GRID_URL, "domain.com");
        config.getGridUrl();
    }

    @Test
    public void testGetBrowser() throws Exception {
        assertEquals(DriverProvider.Browser.ANY, config.getBrowser());
        Arrays.asList(DriverProvider.Browser.values())
                .forEach(browser -> {
                    System.setProperty(SystemDriverConfiguration.BROWSER, browser.name());
                    assertEquals(browser, config.getBrowser());
                });

    }

    @Test
    public void testGetPlatform() throws Exception {
        assertEquals(Platform.ANY, config.getPlatform());
        Arrays.asList(Platform.values()).forEach(platform -> {
            System.setProperty(SystemDriverConfiguration.PLATFORM, platform.name());
            assertEquals(platform, config.getPlatform());
        });

    }

    @Test
    public void testGetBrowserVersion() throws Exception {
        assertNull(config.getBrowserVersion());
    }

}