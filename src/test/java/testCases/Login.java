package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.Setuppage;
import testBase.BaseClass;

public class Login extends BaseClass {
		
	@BeforeClass
	public void testlogin() {
		try {
			
		logger.info("Login test started");
			
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Enter the username");
		lp.setusername(rb.getString("username"));
		
		logger.info("Enter the password");
		lp.setpassword(rb.getString("password"));
		
		logger.info("Click on login button");
		lp.clicklogin();
		
		logger.info("logged in successfully");
		logger.info("verify the Setup page");
		
		Setuppage sp= new Setuppage(driver);
		boolean targetpage=sp.verifysetuppage();
		Assert.assertEquals(targetpage, true);
		
		logger.info("Setup page and text displayed");
		logger.info("Login test finished");
		
		}
		catch(Exception e) {
		logger.info("Login Failed");
		Assert.fail();	
		}
	}
}
