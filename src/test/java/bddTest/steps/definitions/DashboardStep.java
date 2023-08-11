package bddTest.steps.definitions;

import bddTest.DriverUtils;
import bddTest.Hooks;
import bddTest.TestRunner;
import bddTest.pages.dashboard.DashboardPage;
import bddTest.steps.implementations.dashboard.DashboardImplementation;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author vivekprasadk
 */
public class DashboardStep {
    private DriverUtils driverUtil;
    private DashboardImplementation dashboard;
    public DashboardStep(){
        driverUtil = TestRunner.getDriverUtils();
        dashboard = new DashboardImplementation();
    }

    @Given("I verify the dashboard")
    public void iVerifyTheDashboard() {
        dashboard.verifyIfUserDisplayed();
    }

    @Given("I Open Dashboard")
    public void iOpenDashboard() {

    }
}
