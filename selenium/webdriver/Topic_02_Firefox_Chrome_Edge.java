package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Firefox_Chrome_Edge {
	WebDriver driver;
	
	@Test
	public void TC_01_Run_On_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}
	
	@Test
	public void TC_03_Run_On_Edge_Chromium() {
		System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}
	
	@Test
	public void TC_04_Run_On_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://www.youtube.com/");
		driver.quit();
	}
}
