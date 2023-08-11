package bddTest.pages.dashboard;

import bddTest.CommonUtils;
import org.openqa.selenium.By;

public class DashboardPage extends CommonUtils {
    By USER = By.name("username");
    public boolean isUserPresent() {
        return isPresent(USER);
    }

}
