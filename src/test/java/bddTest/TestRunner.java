package bddTest;


import com.vimalselvam.cucumber.listener.Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

import static bddTest.CommonUtils.configFileReader;
import static bddTest.CommonUtils.driver;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"com.cucumber.listener.ExtentCucumberFormatter:reports/report.html", "pretty",
        "html:reports/test-report"}, features = "src/test/resources/features/", tags = "@Challenge")
public class TestRunner {

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
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

    public static void writeExtentReport() {

        Reporter.loadXMLConfig(new File(configFileReader.getReportConfigPath()));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Operating System", System.getProperty("os.name"));
        Reporter.setSystemInfo("OS architecture", System.getProperty("os.arch"));
        Reporter.setSystemInfo("Browser", configFileReader.getProperty("webDriver.driver"));
        Reporter.setSystemInfo("Selenium", "3.141.59");
        Reporter.setSystemInfo("Cucumber", "7.13.0");
        Reporter.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @AfterClass
    public static void tearDownClass() {
        writeExtentReport();
        boolean hasQuit = driver.toString().contains("(null)");
        if (!hasQuit) {
            driver.quit();
        }
    }
}
