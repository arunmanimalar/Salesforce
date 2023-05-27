package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.SalesHomepage;
import pageObjects.Setuppage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	public static WebDriver driver;
	public Logger logger; //for logging
	public ResourceBundle rb;
	
	@BeforeSuite
	@Parameters("browser")
	public void setup(String br) {
		
		//For logger
		logger= LogManager.getLogger(this.getClass());  
		
		//load the config.properties file
		rb=ResourceBundle.getBundle("config");
		
		//setting up the browser launch
		if(br.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}
		else if(br.equals("edge")){
			//WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else {
			//WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		//Wait statement
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//to maximize the window
		driver.manage().window().maximize();
		
		//passing the sales force URL
		driver.get(rb.getString("URL"));
		
		logger.info("Browser launched successfully");
	}
	

	@AfterSuite
	public void teardown() {
		logger.info("Closed the browser successfully");
		driver.quit();
	}
	
	@BeforeTest
	public void login() {
		try {
			
		logger.info("Login test started");
			
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Enter the username");
		lp.setusername(rb.getString("username"));
		
		logger.info("Enter the password");
		lp.setpassword(rb.getString("password"));
		
		logger.info("Click on login button");
		lp.clicklogin();
		
		
		logger.info("verify the Setup page");
		
		Setuppage sp= new Setuppage(driver);
		boolean targetpage=sp.verifysetuppage();
		Assert.assertEquals(targetpage, true);
		
		logger.info("Setup page and text displayed");
		logger.info("logged in successfully");
		
		}
		catch(Exception e) {
		logger.info("Login Failed");
		Assert.fail();	
		}
	}
	
	//method to logout
	@AfterTest
	public void logout() {
		try {
		LogoutPage logout=new LogoutPage(driver);
		
		Thread.sleep(500);
		logger.info("Click on the profile icon");
		logout.clickprofileicon();
		
		Thread.sleep(500);
		
		logger.info("Click on the logout label");
		logout.clicklogout();
		
		logger.info("Logged out successfully");
		}
		catch(Exception e){
			logger.info("Logout Failed");
		}
	}
	
	//launching sales portal from applauncher
	@BeforeMethod
	public void Salesportal() {
		try {
			
			logger.info("Launched the setup");
			
			Thread.sleep(500);
			//Click on the app launcher
			Setuppage Setup=new Setuppage(driver);
			Setup.clickapplauncher();
			
			logger.info("Click on App launcher");
			
			Thread.sleep(500);
			//type sales in search term
			Setup.Setsearchterm(rb.getString("searchtext"));
			logger.info("Entered the text sales");
			
			Thread.sleep(500);
			//Click on Sales text
			Setup.clicksalesfromsuggestion();
			logger.info("Clicked on sales text");
			
			logger.info("Sales home page is loading");
			
			//Verifying the sales page logo
			SalesHomepage Sales=new SalesHomepage(driver);
			Sales.verifySaleslogo();
			Thread.sleep(500);
			logger.info("Sales home page and logo displays");
			}
			catch(Exception e) {
			logger.info("Sales home page is not displayed");
			Assert.fail();	
			}	
	}
	
	@AfterMethod
	public void saleshomepage() {
		
		SalesHomepage Shp= new SalesHomepage(driver);
		Shp.clickhomeicon();
		logger.info("Click on home icon");
	}
	
	
	//method for generating the random string
	public String randomString() {
		
		String generateString= RandomStringUtils.randomAlphabetic(5);
		return (generateString);
	}
	
	//method for generating the random number
	public String randomNumber() {
		
		String generateNumeric= RandomStringUtils.randomNumeric(10);
		return (generateNumeric);
	}
	
	//method for generating the random alphanumeric
	public String randomAlphaNumeric() {
		
		String generateString= RandomStringUtils.randomAlphabetic(5);
		String generateNumeric= RandomStringUtils.randomNumeric(10);
		String generateAlphaNumeric= generateString+generateNumeric;
		return (generateAlphaNumeric);
	}
	
	//method for capturing the screenshot 
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
}
