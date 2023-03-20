import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;




public class TestCadastro {

	@Test
	public void cadastroForm() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Tenorio");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Marcos"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Tenorio"));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Pizza"));
		Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("mestrado"));
		Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Natacao"));
					
	}
	

	@Test
	public void validaNomeObrigatorio() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void validaSobrenomeObrigatorio() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void validaSexoObrigatorio() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Tenorio");
		driver.findElement(By.id("elementosForm:cadastrar")).click(); 
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void validaComidaVegetarianaComCarne() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
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
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
