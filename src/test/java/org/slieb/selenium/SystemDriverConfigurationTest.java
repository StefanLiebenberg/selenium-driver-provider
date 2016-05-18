package org.slieb.selenium;

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
        System.clearProperty(SystemDriverConfiguration.BROWSER_NAME);
        System.clearProperty(SystemDriverConfiguration.BROWSER_VERSION);
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
    public void testGetBrowserVersionAndName() throws Exception {
        System.setProperty(SystemDriverConfiguration.BROWSER, "ie:8");
        assertEquals(DriverProvider.Browser.IE, config.getBrowser());
        assertEquals("8", config.getBrowserVersion());
    }

    @Test
    public void testGetBrowser() throws Exception {
        assertEquals(DriverProvider.Browser.ANY, config.getBrowser());
        for (DriverProvider.Browser browser : Arrays.asList(DriverProvider.Browser.values())) {
            System.setProperty(SystemDriverConfiguration.BROWSER_NAME, browser.name());
            assertEquals(browser, config.getBrowser());
        }
    }

    @Test
    public void testGetPlatform() throws Exception {
        assertEquals(Platform.ANY, config.getPlatform());
        for (Platform platform : Arrays.asList(Platform.values())) {
            System.setProperty(SystemDriverConfiguration.PLATFORM, platform.name());
            assertEquals(platform, config.getPlatform());
        }
    }

    @Test
    public void testGetBrowserVersion() throws Exception {
        assertNull(config.getBrowserVersion());
    }
}
