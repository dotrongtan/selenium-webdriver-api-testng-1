package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Excersie {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// TODO Auto-generated method stub
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com");
	}
	
	@Test
	public void TC_01_getUrl() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		//get login page URL
		String loginpageurl = driver.getCurrentUrl();
		Assert.assertEquals(loginpageurl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//click on create account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String register = driver.getCurrentUrl();
		Assert.assertEquals(register, "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Title() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//get title
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//click on create account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//get title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//click on create account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//get url page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		// back page
		driver.navigate().back();
		
		//get url page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		// forward page
		driver.navigate().forward();
		
		//get title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_PageSource() {
		//Click vào My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//get page source
		String loginpagesource = driver.getPageSource();
		Assert.assertTrue(loginpagesource.contains("Login or Create an Account"));
		
		//click on create account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//get page source
		String registerpagesource = driver.getPageSource();
		Assert.assertTrue(registerpagesource.contains("Create an Account"));
		
	}
	
	@AfterClass
	public void afterClass() {
		// TODO Auto-generated method stub
		driver.quit();
	}
	
}
