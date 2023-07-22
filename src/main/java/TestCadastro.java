import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;




public class TestCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializaDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");	
	}
	
	@Ignore
	@After
	public void driverQuit(){
		driver.quit();		
	}
	
	
	@Test
	public void cadastroForm() {
		dsl.escreve("elementosForm:nome", "Marcos");
		dsl.escreve("elementosForm:sobrenome", "Tenorio");
		dsl.clicarRadioButton("elementosForm:sexo:0");
		dsl.clicarRadioButton("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Marcos"));
		Assert.assertTrue(dsl.obterTexto("descSobrenome").endsWith("Tenorio"));
		Assert.assertTrue(dsl.obterTexto("descSexo").endsWith("Masculino"));
		Assert.assertTrue(dsl.obterTexto("descComida").endsWith("Pizza"));
		Assert.assertTrue(dsl.obterTexto("descEscolaridade").endsWith("mestrado"));
		Assert.assertTrue(dsl.obterTexto("descEsportes").endsWith("Natacao"));
					
	}
	

	@Test
	public void validaNomeObrigatorio() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void validaSobrenomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void validaSexoObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Tenorio");
		driver.findElement(By.id("elementosForm:cadastrar")).click(); 
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void validaComidaVegetarianaComCarne() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Tenorio");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidafavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidafavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click(); 
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		
	}
	
	@Test
	public void validaEsportistaIndeciso() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Tenorio");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidafavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click(); 
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		
	}
	
	ff
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
