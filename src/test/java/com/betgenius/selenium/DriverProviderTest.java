package com.betgenius.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(MockitoJUnitRunner.class)
public class DriverProviderTest {

    @Mock
    DriverProvider.Configuration configurator;

    @Mock
    RemoteWebDriverFactory factory;

    @Mock
    RemoteWebDriver firefoxDriver, chromeDriver, safariDriver, ieDriver, phantomDriver, anyDriver;

    DriverProvider provider;

    @Before
    public void setup() {
        Mockito.when(configurator.getBrowser()).thenReturn(DriverProvider.Browser.ANY);
        Mockito.when(configurator.getPlatform()).thenReturn(Platform.ANY);
        Mockito.when(configurator.getBrowserVersion()).thenReturn(null);
        Mockito.when(factory.getAnyDriver()).thenCallRealMethod();
        Mockito.when(factory.getChromeDriver()).thenReturn(chromeDriver);
        Mockito.when(factory.getFirefoxDriver()).thenReturn(firefoxDriver);
        Mockito.when(factory.getInternetExplorerDriver()).thenReturn(ieDriver);
        Mockito.when(factory.getSafariDriver()).thenReturn(safariDriver);
        Mockito.when(factory.getPhantomDriver()).thenReturn(phantomDriver);
        provider = new DriverProvider(factory, configurator);
    }

    @Test
    public void testGetAnyDriver() {
        Mockito.when(factory.getAnyDriver()).thenReturn(anyDriver);
        Assert.assertEquals(anyDriver, provider.getRemoteWebDriver());
    }

    @Test
    public void testGetFirefoxDriver() {
        Mockito.when(configurator.getBrowser()).thenReturn(DriverProvider.Browser.FIREFOX);
        Assert.assertEquals(firefoxDriver, provider.getRemoteWebDriver());
    }

    @Test
    public void testGetChromeDriver() {
        Mockito.when(configurator.getBrowser()).thenReturn(DriverProvider.Browser.CHROME);
        Assert.assertEquals(chromeDriver, provider.getRemoteWebDriver());
    }

    @Test
    public void testGetIEDriver() {
        Mockito.when(configurator.getBrowser()).thenReturn(DriverProvider.Browser.IE);
        Assert.assertEquals(ieDriver, provider.getRemoteWebDriver());
    }

    @Test
    public void testGetSafariDriver() {
        Mockito.when(configurator.getBrowser()).thenReturn(DriverProvider.Browser.SAFARI);
        Assert.assertEquals(safariDriver, provider.getRemoteWebDriver());
    }

    @Test
    public void testGetPhantomDriver() {
        Mockito.when(configurator.getBrowser()).thenReturn(DriverProvider.Browser.PHANTOMJS);
        Assert.assertEquals(phantomDriver, provider.getRemoteWebDriver());
    }

    @Test
    public void testGetAnyDriverFallback() {
        Mockito.when(factory.getFirefoxDriver()).thenThrow(new RuntimeException("failure!"));
        Assert.assertEquals(chromeDriver, provider.getRemoteWebDriver());

        Mockito.when(factory.getChromeDriver()).thenThrow(new RuntimeException("failure!"));
        Assert.assertEquals(ieDriver, provider.getRemoteWebDriver());

        Mockito.when(factory.getInternetExplorerDriver()).thenThrow(new RuntimeException("failure!"));
        Assert.assertEquals(safariDriver, provider.getRemoteWebDriver());

        Mockito.when(factory.getSafariDriver()).thenThrow(new RuntimeException("failure!"));
        Assert.assertEquals(phantomDriver, provider.getRemoteWebDriver());
    }

    @Test(expected = RuntimeException.class)
    public void testNoBrowsersFound() {
        Mockito.when(factory.getPhantomDriver()).thenThrow(new RuntimeException("failure!"));
        Mockito.when(factory.getSafariDriver()).thenThrow(new RuntimeException("failure!"));
        Mockito.when(factory.getInternetExplorerDriver()).thenThrow(new RuntimeException("failure!"));
        Mockito.when(factory.getChromeDriver()).thenThrow(new RuntimeException("failure!"));
        Mockito.when(factory.getFirefoxDriver()).thenThrow(new RuntimeException("failure!"));
        Assert.assertEquals(null, provider.getRemoteWebDriver());
    }

}