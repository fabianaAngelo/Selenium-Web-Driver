

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	@Test
	public void testeTextField() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		//driver.manage().window().setPosition(new Point(10, 10));
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}
}
