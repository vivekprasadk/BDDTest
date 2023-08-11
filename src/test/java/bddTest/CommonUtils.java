package bddTest;


import com.google.common.io.Files;


import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigFileReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonUtils {
    public static ConfigFileReader configFileReader = new ConfigFileReader();
    static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void removeDriver() {
        driverThreadLocal.remove();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static Scenario scenario = null;

    public static void scrollToTop() {
        WebDriver driver = getDriver();
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public void refreshPage() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.navigate().refresh();
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public String getCurrentURL() {
        WebDriver driver = getDriver();
        if (driver != null) {
            return driver.getCurrentUrl();
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public void switchToWindow(String windowHandle) {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.switchTo().window(windowHandle);
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public Set<String> getWindowHandles() {
        WebDriver driver = getDriver();
        if (driver != null) {
            return driver.getWindowHandles();
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public static String getCurrentWindowHandle() {
        WebDriver driver = getDriver();
        if (driver != null) {
            return driver.getWindowHandle();
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public WebElement locateElement(By identifier) {
        WebDriver driver = getDriver();
        if (driver != null) {
            WebElement element = driver.findElement(identifier);
            highlightElement(element);
            return element;
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public List<WebElement> locateElements(By identifier) {
        WebDriver driver = getDriver();
        if (driver != null) {
            return driver.findElements(identifier);
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public void scrollToElement(By identifier) {
        WebDriver driver = getDriver();
        if (driver != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(locateElement(identifier));
            actions.build().perform();
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public void scrollToElement(WebElement element) {
        WebDriver driver = getDriver();
        if (driver != null) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            // do nothing
        }
    }

    public void highlightElement(WebElement element) {
        WebDriver driver = getDriver();
        if (driver != null) {
        if (configFileReader.getProperty("highlight.element").equalsIgnoreCase("true")) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            for (int count = 0; count < 1; count++) {
                javascriptExecutor.executeScript("arguments[0].style.border='4px groove FireBrick'", element);
                sleep(100);
                javascriptExecutor.executeScript("arguments[0].style.border=''", element);
                sleep(100);
            }
        }
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public static void takeScreenshot() {
        WebDriver driver = getDriver();
        if (driver != null) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                File screenshotFolder = new File(
                        System.getProperty("user.dir") + configFileReader.getProperty("screenshot.path"));
                if (!screenshotFolder.exists()) {
                    if (screenshotFolder.mkdir()) {
                        System.out.println("Screenshot folder created");
                    } else {
                        System.out.println("Screenshot folder not created");
                    }
                }
                File destinationPath = new File(System.getProperty("user.dir")
                        + configFileReader.getProperty("screenshot.path") + screenshotName + dateName + ".png");

                // Copy taken screenshot from source location to destination location
                Files.copy(sourcePath, destinationPath);

                // Attach the specified screenshot to the test
                scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", screenshotName + dateName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public int getSize(By identifier) {
        return locateElements(identifier).size();
    }

    public boolean isPresent(By identifier) {
        return getSize(identifier) > 0;
    }

    public void click(By identifier) {
        scrollToElement(identifier);
        locateElement(identifier).click();
    }

    public void click(WebElement webElement) {
        scrollToElement(webElement);
        webElement.click();
    }

    public void clearText(By identifier) {
        locateElement(identifier).clear();
    }

    public void enterText(By identifier, String inputText) {
        locateElement(identifier).sendKeys(inputText);
    }

    public void selectByValue(By identifier, String valueText) {
        new Select(locateElement(identifier)).selectByValue(valueText);
    }

    public void selectByVisibleText(By identifier, String visibleText) {
        new Select(locateElement(identifier)).selectByVisibleText(visibleText);
    }

    public void selectByIndex(By identifier, int indexValue) {
        new Select(locateElement(identifier)).selectByIndex(indexValue);
    }

    public void waitTillElementNotVisible(By identifier, int timeout) {
        WebDriver driver = getDriver();
        if (driver != null) {
            setSpecificImplicitWait(driver, 100);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(identifier));
            setSpecificImplicitWait(driver, 0);
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    private void setSpecificImplicitWait(WebDriver driver, long milliseconds) {
        driver.manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);
    }



        public void waitForElementToBeClickable(By identifier, int timeout) {
            WebDriver driver = CommonUtils.getDriver();
            if (driver != null) {
                setSpecificImplicitWait(driver, 100);
                WebDriverWait wait = new WebDriverWait(driver, timeout);
                wait.until(ExpectedConditions.elementToBeClickable(identifier));
                setSpecificImplicitWait(driver, 0);
            } else {
                throw new IllegalStateException("WebDriver instance not found for this thread");
            }
        }


    public void waitTillElementVisible(By identifier, int timeout) {
        WebDriver driver = CommonUtils.getDriver();
        if (driver != null) {
            setSpecificImplicitWait(driver, 100);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            setSpecificImplicitWait(driver, 0);
        } else {
            throw new IllegalStateException("WebDriver instance not found for this thread");
        }
    }

    public String getText(By identifier) {
        scrollToElement(identifier);
        return locateElement(identifier).getText().trim();

    }

    public String getText(WebElement element) {
        scrollToElement(element);
        return (element).getText().trim();

    }

    public boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    public boolean isSelected(By identifier) {
        return locateElement(identifier).isSelected();
    }

    public String getTextAttributeValue(By identifier) {
        return locateElement(identifier).getAttribute("value");
    }


    public static void printInReport(String output) {
        scenario.log(output);
    }

}
