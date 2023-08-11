package bddTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        CommonUtils.scenario = scenario;
    }



    @After
    public void afterScenario(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            System.out.println("The scenario failed in the execution of one of the steps");
            CommonUtils.takeScreenshot();
        }
    }


}
