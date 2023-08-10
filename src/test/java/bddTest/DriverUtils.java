package bddTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static bddTest.CommonUtils.configFileReader;
import static bddTest.CommonUtils.driver;

/**
 * @author vivekprasadk
 * Methods to handle the driver and browser operations
 */
public class DriverUtils {

    /**
     * @return OS name
     */
    private String getOperatingSystem() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * closes the browser
     */
    public void closeBrowser(){
        driver.quit();
    }

    /**
     * Launches the browser based on the value from the config.properties
     *
     */
    public void launchBrowser() {
        String browser = configFileReader.getProperty("webDriver.driver");
        switch (browser) {
            case "firefox":
                if (getOperatingSystem().contains("mac")) {
                    WebDriverManager.firefoxdriver().operatingSystem(OperatingSystem.MAC).setup();
                } else
                    WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                if (getOperatingSystem().contains("mac")) {
                    WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
                } else
                    WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chromeHeadless":
                if (getOperatingSystem().contains("mac")) {
                    WebDriverManager.chromedriver().operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    WebDriverManager.chromedriver().setup();
                }
                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1300,800");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefoxHeadless":
                if (getOperatingSystem().contains("mac")) {
                    WebDriverManager.firefoxdriver().operatingSystem(OperatingSystem.MAC).setup();
                } else
                    WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                options.addArguments("--window-size=1200,600");
                driver = new FirefoxDriver(options);
                break;
            case "chrome":
            default:
                if (getOperatingSystem().contains("mac")) {
                    WebDriverManager.chromedriver().operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    WebDriverManager.chromedriver().setup();
                }
                driver = new ChromeDriver();
                break;
        }
    }

    /**
     * Launch the URL from the config.properties file
     */
    public void launchURL() {
        String protocol = configFileReader.getProperty("application.protocol");
        String url = configFileReader.getProperty("application.url");
        String launchUrl = protocol + url;
        driver.get(launchUrl);
    }

    /**
     * Resize the browser window to specific size as per the configuration in config.properties file
     *
     */
    public void resizeWindow() {
        if (configFileReader.getProperty("window.maximize").equalsIgnoreCase("true")) {
            driver.manage().window().maximize();
        } else {
            int width = Integer.parseInt(configFileReader.getProperty("window.width"));
            int height = Integer.parseInt(configFileReader.getProperty("window.height"));
            Dimension d = new Dimension(width, height);
            // Resize the current window to the given dimension
            driver.manage().window().setSize(d);
        }
    }
}
