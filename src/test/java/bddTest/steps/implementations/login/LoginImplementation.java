package bddTest.steps.implementations.login;

import bddTest.pages.login.LoginPage;
import org.junit.Assert;

public class LoginImplementation {
	private LoginPage login = new LoginPage();

	public void waitForLoginButtonToLoad() {
		login.waitForLoginButtonToLoad();
	}

	public void enterUserName() {
		if (login.isUserNamePresent()) {
			login.enterUserNameFromConfig();
		} else {
			Assert.fail("The UserName text Box is not present");
		}
	}

	public void enterPassword() {
		if (login.isPasswordPresent()) {
			login.enterPasswordFromConfig();
		} else {
			Assert.fail("The Password text Box is not present");
		}
	}

	public void clickLoginButton() {
		if (login.isLoginButtonPresent()) {
			login.clickLoginButton();
		} else {
			Assert.fail("The Login button is not found");
		}
	}
}
