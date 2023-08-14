package bddTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static bddTest.CommonUtils.configFileReader;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty",
                "html:reports/test-report",
                "junit:target/cucumber-junit-report.xml",
                "json:target/cucumber-report.json" // JSON report for parallel execution
        },
        features = "src/test/resources/features/",
        tags = "@Challenge",
        glue = {"bddTest.steps.definitions", "bddTest"}
)

public class TestRunner {
    private static ExtentReports extent;


    @BeforeClass
    public static void setup() {
        new File("./reports").mkdirs();
        File file = new File("./reports");
        String[] myFiles;
        if (file.isDirectory()) {

            File file1 = new File("./reports/screenshots");
            myFiles = file1.list();
            if (file1.isDirectory()) {
                myFiles = file1.list();
                for (int i = 0; i < myFiles.length; i++) {
                    File myFile = new File(file1, myFiles[i]);
                    myFile.delete();
                }
            }
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
        writeExtentReport();
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

    public static void writeExtentReport() {
        extent = ExtentService.getInstance();
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("OS architecture", System.getProperty("os.arch"));
        extent.setSystemInfo("Browser", configFileReader.getProperty("webDriver.driver"));
        extent.setSystemInfo("Selenium", "3.141.59");
        extent.setSystemInfo("Cucumber", "7.13.0");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }


    @AfterClass
    public static void tearDownClass() {

        WebDriver driver = CommonUtils.getDriver();
        if (driver != null) {
            driver.quit();
            CommonUtils.removeDriver();
        }
    }

}
