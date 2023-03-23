import org.junit.Test;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;


public class TestesBasicos{

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializaDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");	
		dsl = new DSL(driver);
	}
	
	@Ignore
	@After
	public void driverQuit(){
		driver.quit();		
	}
	
	@Test
	public void testTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");	
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));	
	}	
	
	@Test
	public void testTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste de escrita no text area");		
		Assert.assertEquals("Teste de escrita no text area",dsl.obterValorCampo("elementosForm:sugestoes"));				
	}
	
	@Test
	public void testRadioButton() {
		dsl.clicarRadioButton("elementosForm:sexo:0");		
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());				
	}
	
	@Test
	public void testCheckBox() {
		dsl.clicarRadioButton("elementosForm:comidaFavorita2");		
		Assert.assertTrue(dsl.checkRadioMarcado("elementosForm:comidaFavorita2"));				
	}
	
	@Test
	public void testIndexCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo",dsl.obterValorCampo("elementosForm:escolaridade"));			
	}
	
	@Test
	public void testValoresCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByVisibleText("2o grau completo");
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
	}
	
	@Test
	public void testValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
				
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
	
		combo.deselectByVisibleText("Natacao");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());	
	}
	
	@Test
	public void testButton() {
		dsl.clicarBotao("buttonSimple");
		WebElement botao = driver.findElement(By.id("buttonSimple"));		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));		
	}
	
	@Test
	public void testLink() {
		dsl.clicarLink("Voltar");		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));		
	}
	
	@Test
	public void testTextByTagEClass() {
		Assert.assertEquals("Campo de Treinamento",dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",dsl.obterTexto(By.className("facilAchar")));
	//Sem o getText ele me retornaria um WebElement e daria erro
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	