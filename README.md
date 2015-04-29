
# DriverProvider

The standard usage is to create a driver instance from system properties.

    DriverProvider provider = DriverProvider.getSystemConfigDriverProvider();
    RemoteWebDriver driver = provider.getRemoteWebDriver();
    driver.get("http://example.com");
    ... // do stuff with driver
    driver.close();
    
The driver can be controlled through these system properties ( using -D from command line )

| System Property          | Values                                      | Default | Description                                                      |
|--------------------------|---------------------------------------------|---------|------------------------------------------------------------------|
| driverProvider.mode      | grid, local                                 | local   | Provides a driver from the grid                                  |
| driverProvider.platform  | linux, windows, any                         | any     | Specifies a required platform, only used with grid mode          |
| driverProvider.browser   | firefox, chrome, safari, ie, phantomjs, any | any     | Specifies the required browser                                   |  
| driverProvider.browserVersion   | <anything>                                  |         | Specifies the required browser version, only used with grid mode |


If you wanted to have more control over configuration you could specify your own configurator:

    DriverProvider.Configuration configuration = new DefaultDriverConfiguration();
    configuration.setMode(Mode.Grid);
    DriverProvider provider = DriverProvider.getProvider(configuration);
    
If you wanted complete control over driver creation (perhaps you want to specify different browsers in one test) you could just use the factories directly:

    RemoteWebDriverFactory factory = new GridRemoteWebDriverFactory(configuration);
    factory.getFirefoxDriver();
    
    
    