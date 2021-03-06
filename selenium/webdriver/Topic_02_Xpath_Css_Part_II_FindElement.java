package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.text.normalizer.CharTrie.FriendAgent;

public class Topic_02_Xpath_Css_Part_II_FindElement {
	WebDriver driver;
	Random rand;
	String email;
	String firstname;
	String lastname;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		rand = new Random();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/");
		
		email = "automation" + rand.nextInt(99999) +"@mailinator.com";
		firstname ="Tan";
		lastname ="Do";
		
	}
	
	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//nhập email and password
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		
		//click vào button login
		driver.findElement(By.name("send")).click();
		
		//kiểm tra error message tại email textbox
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		
		//kiểm tra error message tại password textbox
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02_Login_Invalid_Email() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//nhập email and password
		driver.findElement(By.id("email")).sendKeys("123123@12312.1233");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		//click vào button login
		driver.findElement(By.name("send")).click();
		
		//kiểm tra error message tại email textbox
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//nhập email and password
		driver.findElement(By.id("email")).sendKeys("tan@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		
		//click vào button login
		driver.findElement(By.name("send")).click();
		
		//kiểm tra error message tại password textbox
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Login_Invalid_Password() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//nhập email and password
		driver.findElement(By.id("email")).sendKeys("tan@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456789");
		
		//click vào button login
		driver.findElement(By.name("send")).click();
		
		//kiểm tra error message tại password textbox
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Login_Valid_Email_And_Password() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//nhập email and password
		driver.findElement(By.id("email")).sendKeys("tan.do@mailinator.com");
		driver.findElement(By.name("login[password]")).sendKeys("Admin123!");
		
		//click vào button login
		driver.findElement(By.name("send")).click();
		
		//kiểm tra title
		Assert.assertEquals(driver.findElement(By.xpath("//div[1]//h1")).getText(), "MY DASHBOARD");
		
		//kiểm tra username
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, tan Do!");
				
//		//kiểm tra fullname
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='My Dashboard']")).getText(), "MY DASHBOARD");
//		
//		//kiểm tra email
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='My Dashboard']")).getText(), "MY DASHBOARD");
		
//		//Click account link on header
//		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[contains(text(),'ACCOUNT')]")).click();
//		
//		//Click Logout on header
//		driver.findElement(By.xpath("//div[@class='skip-content skip-active']//a[text()='Log Out']")).click();	
	}
	
	@Test
	public void TC_06_Register() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//Click on Create my account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Input info
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("Admin123!");
		driver.findElement(By.id("confirmation")).sendKeys("Admin123!");
		
		//click vào button Register
				driver.findElement(By.xpath("//button[@title='Register']")).click();
				
		//Check text
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		String info = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		
		Assert.assertTrue(info.contains(firstname));
		Assert.assertTrue(info.contains(lastname));
		Assert.assertTrue(info.contains(email));
		
		//log out
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@class='links']//a[text()='Log Out']")).click();
		
		//check homepage
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
					
	}
	
	@Test
	public void TC_07_Login_Valid_Email_And_Password() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//nhập email and password
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.name("login[password]")).sendKeys("Admin123!");
		
		//click vào button login
		driver.findElement(By.name("send")).click();
				
		//Verify wellcome message
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + firstname + " " + lastname + "!");
			
					
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
