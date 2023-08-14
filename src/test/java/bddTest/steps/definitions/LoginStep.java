package bddTest.steps.definitions;

import bddTest.DriverUtils;
import bddTest.Hooks;
import bddTest.TestRunner;
import bddTest.steps.implementations.login.LoginImplementation;
import io.cucumber.java.en.Given;

public class LoginStep {
    private DriverUtils driverUtil;
    private LoginImplementation login;

    public LoginStep() {
        driverUtil = Hooks.getDriverUtils();
        login = new LoginImplementation();
    }

    @Given("I launch the application and Login as preferred user")
    public void iLaunchTheApplicationAndLoginAsPreferredUser() {
        driverUtil.launchURL();
        login.waitForLoginButtonToLoad();
        login.enterUserName();
        login.enterPassword();
        login.clickLoginButton();
    }
}
