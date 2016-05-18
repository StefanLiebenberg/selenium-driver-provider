package org.slieb.selenium;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;

import java.net.URL;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DefaultDriverConfigurationTest {

    DefaultDriverConfiguration configuration;

    @Before
    public void setup() {
        configuration = new DefaultDriverConfiguration();
    }

    @Test
    public void testGetMode() throws Exception {
        assertEquals(DriverProvider.Mode.LOCAL, configuration.getMode());
        configuration.setMode(null);
        assertEquals(DriverProvider.Mode.LOCAL, configuration.getMode());
        configuration.setMode(DriverProvider.Mode.LOCAL);
        assertEquals(DriverProvider.Mode.LOCAL, configuration.getMode());
        configuration.setMode(DriverProvider.Mode.GRID);
        assertEquals(DriverProvider.Mode.GRID, configuration.getMode());
    }

    @Test
    public void testGetGridUrl() throws Exception {
        assertNull(configuration.getGridUrl());
        configuration.setGridUrl(null);
        assertNull(configuration.getGridUrl());

        for (final URL url : ImmutableList.of(new URL("http://example.com"), new URL("http://domain.com"))) {
            configuration.setGridUrl(url);
            assertEquals(url, configuration.getGridUrl());
        }
    }

    @Test
    public void testGetBrowser() throws Exception {
        assertEquals(DriverProvider.Browser.ANY, configuration.getBrowser());
        configuration.setBrowser(null);
        assertEquals(DriverProvider.Browser.ANY, configuration.getBrowser());
        for (DriverProvider.Browser browser : asList(DriverProvider.Browser.values())) {
            configuration.setBrowser(browser);
            assertEquals(browser, configuration.getBrowser());
        }
    }

    @Test
    public void testGetPlatform() throws Exception {
        assertEquals(Platform.ANY, configuration.getPlatform());
        configuration.setPlatform(null);
        assertEquals(Platform.ANY, configuration.getPlatform());
        for (Platform platform : asList(Platform.values())) {
            configuration.setPlatform(platform);
            assertEquals(platform, configuration.getPlatform());
        }
    }

    @Test
    public void testSetBrowserVersion() throws Exception {
        configuration.setBrowserVersion(null);
        assertNull(configuration.getBrowserVersion());
        for (int i = 0; i < 10; i++) {
            final String version = String.valueOf(i);
            configuration.setBrowserVersion(version);
            assertEquals(version, configuration.getBrowserVersion());
        }
    }
}
