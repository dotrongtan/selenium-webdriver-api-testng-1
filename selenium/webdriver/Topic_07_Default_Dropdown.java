package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.impl.dv.xs.MonthDV;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select_01;
	String firstname, lastname, email, password, company, date, month, year;

	By genderby = By.id("gender-male");
	By firstnameby = By.id("FirstName");
	By lastnameby = By.id("LastName");
	By emailby = By.id("Email");
	By companyby = By.id("Company");
	By dateby = By.name("DateOfBirthDay");
	By monthby = By.name("DateOfBirthMonth");
	By yearby = By.name("DateOfBirthYear");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstname = "Jame";
		lastname = "John";
		email = "jame" + generateEmail() + "@mailinator.com";
		password = "Admin123!";
		company = "PowerR";
		date = "5";
		month = "May";
		year = "1955";

	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(genderby).click();
		driver.findElement(firstnameby).sendKeys(firstname);
		driver.findElement(lastnameby).sendKeys(lastname);

		select_01 = new Select(driver.findElement(dateby));

		// verify dropdown is single dropdown
		Assert.assertFalse(select_01.isMultiple());

		// Choose day with text
		select_01.selectByVisibleText(date);

		// Verify item selected
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), date);

		// Count item in dropdown = ?
		Assert.assertEquals(select_01.getOptions().size(), 32);

		select_01 = new Select(driver.findElement(monthby));

		// choose month
		select_01.selectByVisibleText(month);
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select_01.getOptions().size(), 13);

		select_01 = new Select(driver.findElement(yearby));

		// choose year
		select_01.selectByVisibleText(year);
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select_01.getOptions().size(), 112);

		driver.findElement(emailby).sendKeys(email);
		driver.findElement(companyby).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"result\"]")).getText(),
				"Your registration completed");

		// click on My account link
		driver.findElement(By.className("ico-account")).click();

		Assert.assertTrue(driver.findElement(genderby).isSelected());

		Assert.assertEquals(driver.findElement(firstnameby).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(lastnameby).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(emailby).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyby).getAttribute("value"), company);

		select_01 = new Select(driver.findElement(dateby));
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), date);

		select_01 = new Select(driver.findElement(monthby));
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), month);

		select_01 = new Select(driver.findElement(yearby));
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), year);

	}

	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		select_01 = new Select(driver.findElement(By.name("user_job1")));
		Assert.assertFalse(select_01.isMultiple());

		select_01.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), "Mobile Testing");

		select_01.selectByValue("manual");
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), "Manual Testing");

		select_01.selectByIndex(9);
		Assert.assertEquals(select_01.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select_01.getOptions().size(), 10);

		select_01 = new Select(driver.findElement(By.name("user_job2")));
		Assert.assertTrue(select_01.isMultiple());

		select_01.selectByVisibleText("Automation");
		select_01.selectByVisibleText("Mobile");
		select_01.selectByVisibleText("Desktop");

		// creat list add item được selected vào
		List<WebElement> itemSelected = select_01.getAllSelectedOptions();

		// create arraylist mảng dong add text của item được selected vào
		List<String> itemSelectedText = new ArrayList<String>();

		// create arraylist mảng dong add text cần verify vào
		List<String> itemtext = new ArrayList<String>();

		itemtext.add("Automation");
		itemtext.add("Mobile");
		itemtext.add("Desktop");

		for (WebElement item : itemSelected) {
			itemSelectedText.add(item.getText());
			System.out.println(item.getText());

		}

		Assert.assertTrue(itemSelectedText.contains("Automation"));
		Assert.assertTrue(itemSelectedText.contains("Mobile"));
		Assert.assertTrue(itemSelectedText.contains("Desktop"));

		Assert.assertEquals(itemSelectedText, itemtext);

		select_01.deselectAll();

		List<WebElement> itemSelectedEmpty = select_01.getAllSelectedOptions();

		List<String> itemSelectedEmptyText = new ArrayList<String>();

		List<String> itemEmptyText = new ArrayList<String>();

		for (WebElement item : itemSelectedEmpty) {
			itemSelectedEmptyText.add(item.getText());
			System.out.println(item.getText());
		}

		Assert.assertEquals(itemSelectedEmptyText, itemEmptyText);
	}

	public int generateEmail() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
