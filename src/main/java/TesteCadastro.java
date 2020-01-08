import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\WDA Tecnologia\\Documents\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza()
	{
		driver.quit();
	}
	
	@Test
	public void deveRealizarCadastroComSucesso() {
		dsl.escreve("elementosForm:nome", "Fabiana");
		dsl.escreve("elementosForm:sobrenome", "Angelo");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.ObterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.ObterTexto("descNome").endsWith("Fabiana"));
		Assert.assertEquals("Sobrenome: Angelo", dsl.ObterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.ObterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.ObterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.ObterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao Corrida", dsl.ObterTexto("descEsportes"));
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {	
		dsl.escreve("elementosForm:nome", "Fabiana");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		dsl.escreve("elementosForm:nome", "Fabiana");
		dsl.escreve("elementosForm:sobrenome", "Angelo");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fabiana");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Angelo");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fabiana");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Angelo");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
	}
}
