package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;


import pageObjects.LoginPage;
import testComponents.BaseTest;

public class LoginAppTest extends BaseTest{

	@Test(dependsOnMethods = {"validateInvalidUser"}, groups = {"loginScenarios"})
	public void loginToApplication() throws IOException {
		
		loginPg.loginApplication("frankcole@yopmail.com", "Test1234");
	}
	
	
	@Test(groups = {"loginScenarios"}, dataProvider = "getUserData2")
	public void validateInvalidUser(HashMap<String, String> mapping) {
		loginPg.loginApplication(mapping.get("email"), mapping.get("password"));
		String actualValidation = loginPg.getValidationText();
		Assert.assertEquals(actualValidation, "The email address or password you entered is incorrect");
	}
	

	
	@DataProvider
	public Object[][] getUserData2() throws StreamReadException, DatabindException, IOException{
		/*
		Map<String, String> map = new HashMap<>();
		map.put("userEmail", "Testing101@yopmail.com");
		map.put("userPassword", "Testing2345");
		
		Map<String, String> map1 = new HashMap<>();
		map1.put("userEmail", "Testing102@yopmail.com");
		map1.put("userPassword", "Testing546456");
		

		Map<String, String> map2 = new HashMap<>();
		map2.put("userEmail", "Testing103@yopmail.com");
		map2.put("userPassword", "Testing6574");
		*/
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\testData.json");
		Object[][] testMap = {{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}};
		
		return testMap ;
		
	}
	
}
