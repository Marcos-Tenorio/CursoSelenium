import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestFrames {
	
	private WebDriver driver;
	
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
	public void testFrame() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
			
	}

	@Test
	public void testJanelaComTitulo() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().defaultContent();
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
	}
	
	@Test
	public void testJanelaComWindowHandler() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora");
		
	}
	
}
