import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	@Test
	public void deveInteragirComAlertSimples() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
		driver.quit();
	}
}
