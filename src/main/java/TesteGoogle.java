

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
private WebDriver driver;
	
	@Before
	public void inicializa()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\chromedriver.exe");
	}

	@After
	public void finaliza()
	{
		driver.quit();
	}

	@Test
	public void testeTextField() {
		WebDriver driver = new FirefoxDriver(); 
		//driver.manage().window().setPosition(new Point(10, 10));
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}
}
