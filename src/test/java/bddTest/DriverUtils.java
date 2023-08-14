package bddTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static bddTest.CommonUtils.configFileReader;

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
        WebDriver driver = CommonUtils.getDriver();
        if (driver != null) {
            driver.quit();
            CommonUtils.removeDriver(); // Remove driver instance from ThreadLocal after quitting
        }
    }

    /**
     * Launches the browser based on the value from the config.properties
     *
     */
    public void createDriver() {
        String browser = configFileReader.getProperty("webDriver.driver");
        WebDriver driver;
        WebDriverManager wdm = WebDriverManager.getInstance(browser);

        switch (browser) {
            case "firefox":
                if (getOperatingSystem().contains("mac")) {
                    wdm.operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    wdm.setup();
                }
                driver = new FirefoxDriver();
                break;
            case "edge":
                if (getOperatingSystem().contains("mac")) {
                    wdm.operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    wdm.setup();
                }
                driver = new EdgeDriver();
                break;
            case "chromeHeadless":
                if (getOperatingSystem().contains("mac")) {
                    wdm.operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    wdm.setup();
                }
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1300,800");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefoxHeadless":
                if (getOperatingSystem().contains("mac")) {
                    wdm.operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    wdm.setup();
                }
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                options.addArguments("--window-size=1200,600");
                driver = new FirefoxDriver(options);
                break;
            case "chrome":
            default:
                if (getOperatingSystem().contains("mac")) {
                    wdm.operatingSystem(OperatingSystem.MAC).setup();
                } else {
                    wdm.setup();
                }
                driver = new ChromeDriver();
                break;
        }

        CommonUtils.setDriver(driver); // Set the driver instance in ThreadLocal
    }



    /**
     * Launch the URL from the config.properties file
     */
    public void launchURL() {
        WebDriver driver = CommonUtils.getDriver();
        if (driver != null) {
            String protocol = configFileReader.getProperty("application.protocol");
            String url = configFileReader.getProperty("application.url");
            String launchUrl = protocol + url;
            driver.get(launchUrl);
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    /**
     * Resize the browser window to specific size as per the configuration in config.properties file
     *
     */
    public void resizeWindow() {
        WebDriver driver = CommonUtils.getDriver();
        if (driver != null) {
            if (configFileReader.getProperty("window.maximize").equalsIgnoreCase("true")) {
                driver.manage().window().maximize();
            } else {
                int width = Integer.parseInt(configFileReader.getProperty("window.width"));
                int height = Integer.parseInt(configFileReader.getProperty("window.height"));
                Dimension d = new Dimension(width, height);
                driver.manage().window().setSize(d);
            }
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }
}
