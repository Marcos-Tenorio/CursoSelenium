import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.*;

public class TestAlert {
	
	@Test
	public void testAlertSimples() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String textoAlert = alert.getText();
		
		Assert.assertEquals("Alert Simples",textoAlert);
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlert);
	}
	
	@Test
	@Ignore
	public void testAlertConfirm() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();		
		Assert.assertEquals("Confirm Simples", alerta.getText());		
		alerta.accept();		
		Assert.assertEquals("Confirmado", alerta.getText());		
		alerta.accept();		
		
		driver.findElement(By.id("confirm")).click();	
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());		
		alerta.dismiss();
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Negado", alerta.getText());		
		alerta.dismiss();
		
	}
	
	@Test
	@Ignore
	public void testAlertPrompt() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/SAMSUNG/eclipse-workspace/CursoSelenium/src/main/resources/componentes.html");
		
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		
		
		
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		
				
	}
}
