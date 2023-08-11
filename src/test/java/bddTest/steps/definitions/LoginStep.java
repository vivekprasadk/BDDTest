package bddTest.steps.definitions;

import bddTest.DriverUtils;
import bddTest.steps.implementations.login.LoginImplementation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginStep {
    private DriverUtils driverUtil = new DriverUtils();
    private LoginImplementation login = new LoginImplementation();

    @Given("I launch the application and Login as preferred user")
    public void iLaunchTheApplicationAndLoginAsPreferredUser() {
        driverUtil.launchBrowser();
        driverUtil.resizeWindow();
        driverUtil.launchURL();
        login.waitForLoginButtonTobeDisplayed();
        login.enterUserName();
        login.enterPassword();
        login.clickLoginButton();
    }

    @And("I logout and close the browser")
    public void iLogoutAndCloseTheBrowser() {
        driverUtil.closeBrowser();
    }


}
