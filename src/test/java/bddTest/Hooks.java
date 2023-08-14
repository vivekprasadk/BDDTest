package bddTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
    private static ThreadLocal<DriverUtils> driverUtilThreadLocal;
    @Before
    public void beforeFeature(){
        driverUtilThreadLocal = new ThreadLocal<>();
        driverUtilThreadLocal.set(new DriverUtils());
        driverUtilThreadLocal.get().createDriver();
        driverUtilThreadLocal.get().resizeWindow();
    }

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        CommonUtils.scenario = scenario;
    }

    @After
    public void afterFeature(){
        driverUtilThreadLocal.get().closeBrowser();
    }

    @After
    public void afterScenario(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            System.out.println("The scenario failed in the execution of one of the steps");
            CommonUtils.takeScreenshot();
        }
    }

    public static DriverUtils getDriverUtils() {
        return driverUtilThreadLocal.get();
    }

}
