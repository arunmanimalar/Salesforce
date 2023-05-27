package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.SalesHomepage;
import pageObjects.Setuppage;
import testBase.BaseClass;

public class LaunchingSales extends BaseClass{
	
		
		
		@BeforeMethod
		public void testlaunchingsales() {
			try {
						
			logger.info("Launching the setup");
			
			//Click on the app launcher
			Setuppage Setup=new Setuppage(driver);
			Setup.clickapplauncher();
			
			logger.info("Click on App launcher");
			
			//type sales in search term
			Setup.Setsearchterm(rb.getString("searchtext"));
			logger.info("Entered the text sales");
			
			//Click on Sales text
			Setup.clicksalesfromsuggestion();
			logger.info("Clicked on sales text");
			
			logger.info("Sales home page is loading");
			
			//Verifying the sales page logo
			SalesHomepage Sales=new SalesHomepage(driver);
			Sales.verifySaleslogo();
			logger.info("Sales home page and logo displays");
			}
			catch(Exception e) {
			logger.info("Sales home page is not displayed");
			Assert.fail();	
			}	
		}
}
