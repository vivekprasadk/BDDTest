package bddTest.steps.implementations.dashboard;

import bddTest.pages.dashboard.DashboardPage;
import org.junit.Assert;

public class DashboardImplementation {
    private DashboardPage dashboardPage = new DashboardPage();
    public  void verifyIfUserDisplayed() {
        dashboardPage.sleep(5000);
        Assert.assertTrue(dashboardPage.isTimeAtWorkPresent());
        Assert.assertTrue(dashboardPage.isMyActionsPresent());
        Assert.assertTrue(dashboardPage.isOnLeavePresent());
        Assert.assertTrue(dashboardPage.isBuzzPresent());
        Assert.assertTrue(dashboardPage.isQuickLaunchPresent());
        Assert.assertTrue(dashboardPage.isDistributionSubUnitPresent());
        Assert.assertTrue(dashboardPage.isDistributionLocationPresent());
    }

}
