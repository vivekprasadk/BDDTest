package bddTest.steps.definitions;

import bddTest.DriverUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;

import static bddTest.CommonUtils.driver;

public class LoginStep {
    private DriverUtils driverUtil = new DriverUtils();
    @Given("I launch the application and Login as preferred user")
    public void iLaunchTheApplicationAndLoginAsPreferredUser() {
        driverUtil.launchBrowser();
        driverUtil.resizeWindow();
        driverUtil.launchURL();
        driver.findElement(By.id("Test")).click();
    }

    @And("I logout and close the browser")
    public void iLogoutAndCloseTheBrowser() {
    }


}
