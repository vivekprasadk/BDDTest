package bddTest;

import com.google.common.io.Files;

import com.vimalselvam.cucumber.listener.Reporter;
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
    public static WebDriver driver = null;
    public static Scenario scenario = null;
    public static void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }
    public void refreshPage() {
        driver.navigate().refresh();
    }
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public WebElement locateElement(By identifier) {
        WebElement element = driver.findElement(identifier);
        highlightElement(element);
        return element;
    }
    public List<WebElement> locateElements(By identifier) {
        return driver.findElements(identifier);
    }

    public void scrollToElement(By identifier) {
        Actions actions = new Actions(driver);
        actions.moveToElement(locateElement(identifier));
        actions.build().perform();
    }
    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }
    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            // do nothing
        }
    }

    public void highlightElement(WebElement element) {
        if (configFileReader.getProperty("highlight.element").equalsIgnoreCase("true")) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            for (int count = 0; count < 1; count++) {
                javascriptExecutor.executeScript("arguments[0].style.border='4px groove FireBrick'", element);
                sleep(100);
                javascriptExecutor.executeScript("arguments[0].style.border=''", element);
                sleep(100);
            }
        }
    }
    public static void takeScreenshot() {
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

            // This attach the specified screenshot to the test
            Reporter.addScreenCaptureFromPath(destinationPath.toString());
        } catch (IOException e) {

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
        setSpecificImplicitWait(100);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(identifier));
        setSpecificImplicitWait(0);
    }
    public void setSpecificImplicitWait(int time){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }
    public void waitForElementToBeClickable(By identifier, int timeout) {
        setSpecificImplicitWait(100);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(identifier));
        setSpecificImplicitWait(0);
    }
    public void waitTillElementVisible(By identifier, int timeout) {
        setSpecificImplicitWait(100);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
        setSpecificImplicitWait(0);
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






}
