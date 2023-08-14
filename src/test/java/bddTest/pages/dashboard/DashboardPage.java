package bddTest.pages.dashboard;

import bddTest.CommonUtils;
import org.openqa.selenium.By;

public class DashboardPage extends CommonUtils {
    By TIME_AT_WORK = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='Time at Work']");
    By MY_ACTIONS = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='My Actions']");
    By QUICK_LAUNCH = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='Quick Launch']");
    By BUZZ = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='Buzz Latest Posts']");
    By ON_LEAVE = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='Employees on Leave Today']");
    By DISTRIBUTION_SUB_UNIT = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='Employee Distribution by Sub Unit']");
    By DISTRIBUTION_LOCATION = By.xpath("//*[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']//p[text()='Employee Distribution by Location']");


    public boolean isTimeAtWorkPresent() {
        return isPresent(TIME_AT_WORK);
    }
    public boolean isMyActionsPresent() {
        return isPresent(MY_ACTIONS);
    }
    public boolean isQuickLaunchPresent() {
        return isPresent(QUICK_LAUNCH);
    }
    public boolean isBuzzPresent() {
        return isPresent(BUZZ);
    }
    public boolean isOnLeavePresent() {
        return isPresent(ON_LEAVE);
    }
    public boolean isDistributionSubUnitPresent() {
        return isPresent(DISTRIBUTION_SUB_UNIT);
    }
    public boolean isDistributionLocationPresent() {
        return isPresent(DISTRIBUTION_LOCATION);
    }

}
