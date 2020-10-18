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


public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String email, username, password, loginPageURL, customerID;
	String name, dob, dob_check, addr, city, state, pinno, telephoneno, emailid, password_regis;
	String editaddr, editcity, editstate, editpinno, edittelephoneno, editemail;
	
	By nameby = By.name("name");
	By dobby = By.name("dob");
	By addrby = By.name("addr");
	By cityby = By.name("city");
	By stateby = By.name("state");
	By pinnoby = By.name("pinno");
	By telephonenoby = By.name("telephoneno");
	By emailidby = By.name("emailid");
	By passwordby = By.name("password");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		email = "Automaition@gmail.com";
		loginPageURL = driver.getCurrentUrl();
		
		name = "Tan";
		dob = "07-01-1988";
		dob_check = "1988-01-07";
		addr = "123 CaliAdress";
		city = "Los Angeles";
		state = "California";
		pinno = "900010";
		telephoneno = "1234567890";
		emailid = generateEmail();
		password_regis = "123456";
		
		editaddr = "456 CaliAdress";
		editcity = "Alabama City";
		editstate = "Florida";
		editpinno = "900099";
		edittelephoneno = "9234567890";
		editemail = generateEmail();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageURL);
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}
	
	
	@Test
	public void TC_01_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameby).sendKeys(name);
		driver.findElement(dobby).sendKeys(dob);
		driver.findElement(addrby).sendKeys(addr);
		driver.findElement(cityby).sendKeys(city);
		driver.findElement(stateby).sendKeys(state);
		driver.findElement(pinnoby).sendKeys(pinno);
		driver.findElement(telephonenoby).sendKeys(telephoneno);
		driver.findElement(emailidby).sendKeys(emailid);
		driver.findElement(passwordby).sendKeys(password_regis);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob_check);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), telephoneno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailid);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	
	}
	
	@Test
	public void TC_02_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify value
		Assert.assertEquals(driver.findElement(nameby).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobby).getAttribute("value"), dob_check);
		Assert.assertEquals(driver.findElement(addrby).getText(), addr);
		Assert.assertEquals(driver.findElement(cityby).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateby).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinnoby).getAttribute("value"), pinno);
		Assert.assertEquals(driver.findElement(telephonenoby).getAttribute("value"), telephoneno);
		Assert.assertEquals(driver.findElement(emailidby).getAttribute("value"), emailid);
		
		//Edit
		driver.findElement(addrby).clear();
		driver.findElement(addrby).sendKeys(editaddr);
		driver.findElement(cityby).clear();
		driver.findElement(cityby).sendKeys(editcity);
		driver.findElement(stateby).clear();
		driver.findElement(stateby).sendKeys(editstate);
		driver.findElement(pinnoby).clear();
		driver.findElement(pinnoby).sendKeys(editpinno);
		driver.findElement(telephonenoby).clear();
		driver.findElement(telephonenoby).sendKeys(edittelephoneno);
		driver.findElement(emailidby).clear();
		driver.findElement(emailidby).sendKeys(editemail);
		driver.findElement(By.name("sub")).click();
		
		//Verify eidt
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob_check);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editaddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editcity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editstate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editpinno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), edittelephoneno);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editemail);
	
	}
	
	public String generateEmail() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return "tan.do" + rand.nextInt(99999) + "@mailinator.com";
	}
	
	@AfterClass
	public void afterClass() {
		// TODO Auto-generated method stub
		driver.quit();
		
	}
	
}
