package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pageObjects.LoginPage;
import testComponents.BaseTest;

public class SignupUserTest extends BaseTest{
	

	@Test(groups = {"newUserSignup"}, retryAnalyzer= testComponents.Retry.class)
	public void userSignupFlow() throws InterruptedException {
		
		loginPg.openCreateAccount();
		Thread.sleep(3000);
		Assert.assertTrue(true);
	}
}
