package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}
	
	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//8
		SelectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","8");
		sleepInSecond(3);
		
		//16
		SelectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "16");
		sleepInSecond(3);

		// 5
		SelectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);

		// 10
		SelectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(3);
	}
	
	//1. Click vào dropdown
	//2. Chờ hiển thị hết item
	//3. Lưu nó vào 1 list
	//4. Lấy text của những item ra
	//5. KT = test cần tìm không (Nếu có thì thoát vòng, nếu không thì tìm tới item cuối)
	
	public void SelectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		//1. Click vào dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		//2. Chờ hiển thị hết item
		//explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		//3. Lưu nó vào 1 list
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));
		int allItemNumber = allItem.size();
		//4. Lấy text của những item ra
		for (int i = 0; i < allItemNumber; i++) {
			String actualValueItem = allItem.get(i).getText();
			//5. KT = test cần tìm không
			if (actualValueItem.equals(expectedValueItem)) {
				allItem.get(i).click();
				//Nếu có thì thoát vòng, nếu không thì tìm tới item cuối
				break;
			}
		}
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
