package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends Basepage  {

	//here we were invoking the parent class constructor.
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='username']")
	WebElement txtusername;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='Login']")
	WebElement btnlogin;
	

	
	//Action methods
	
	public void setusername(String username) {
		txtusername.sendKeys(username);
	}
	
	public void setpassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	public void clicklogin() {
		btnlogin.click();
	}
	

}
