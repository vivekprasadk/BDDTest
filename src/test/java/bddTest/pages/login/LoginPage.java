package bddTest.pages.login;

import bddTest.CommonUtils;
import org.openqa.selenium.By;

public class LoginPage extends CommonUtils {
    By login = By.id("Test");

    public void clickLogin(){
        driver.findElement(login).click();
    }
}
