package bddTest.pages.login;

import bddTest.CommonUtils;
import org.openqa.selenium.By;

public class LoginPage extends CommonUtils {
    private static final By USERNAME = By.name("username");
    private static final By PASSWORD = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector(".orangehrm-login-button");

    public void waitForLoginButtonToLoad() {
        waitTillElementVisible(LOGIN_BUTTON, 30);
    }
    public void waitForUserNameToLoad() {
        waitForElementToBeClickable(USERNAME, 30);
    }

    public boolean isUserNamePresent() {
        return isPresent(USERNAME);
    }

    public void enterUserNameFromConfig(){
        enterText(USERNAME,getUserNameFromConfig());
    }

    public boolean isPasswordPresent() {
        return isPresent(PASSWORD);
    }

    public void enterPasswordFromConfig(){
        enterText(PASSWORD,getPasswordFromConfig());
    }
    public boolean isLoginButtonPresent() {
        return isPresent(LOGIN_BUTTON);
    }
    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    public String getUserNameFromConfig(){
        return configFileReader.getProperty("application.username");
    }
    public String getPasswordFromConfig(){
        return configFileReader.getProperty("application.password");
    }

}
