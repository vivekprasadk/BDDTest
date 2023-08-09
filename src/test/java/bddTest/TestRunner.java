package bddTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "com.cucumber.listener.ExtentCucumberFormatter:reports/report.html", "pretty",
        "html:reports/test-report" }, features = "src/test/resources/features/", tags = "@Challenge")
public class TestRunner {


}
