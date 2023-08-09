package bddTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenarioName) throws Exception {
        CommonUtils.scenario = scenarioName;
    }

    @After
    public void afterScenario(Scenario scenarioName) throws InterruptedException {
        if (scenarioName.isFailed()) {
            System.out.println("The scenario failed in the execution of one of the steps");
            CommonUtils.takeScreenshot();
        }
    }

}
