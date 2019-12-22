import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	@Test
	public void testeTextField() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();
		
	}
	@Test
	public void deveInteragirComTextArea() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.quit();
		
	}
	@Test
	public void deveInteragirComCheckbox() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComCombo() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau incompleto");
		
		Assert.assertEquals("2o grau incompleto", combo.getFirstSelectedOption().getText());
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.getOptions();
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		driver.quit();
	}
}
