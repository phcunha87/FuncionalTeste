package br.com.qa.tests;


import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Testesfuncionais {
	
	public WebDriver inicaliza() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.40:4444/wd/hub"), cap);
		//abre url
		driver.navigate().to("http://192.168.0.40:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	
	@Test
	public void naoDeveCadastrarSemDescricao() throws InterruptedException, MalformedURLException {
		WebDriver driver = inicaliza();
		try {
			Thread.sleep(1000);
			//clica no botão add todo
			driver.findElement(By.id("addTodo")).click();
			// insere data
			driver.findElement(By.id("dueDate")).sendKeys("07/07/2010");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//valida mensagem retorno
			String message =  driver.findElement(By.xpath("//p[@id='message']")).getText();
			assertEquals("Fill the task description", message);
			
		} finally {
			//fechar navegador
			driver.quit();
		}
		
		
		
	}
	
	
	@Test
	public void naoDeveSalvarcomDataPassada() throws InterruptedException, MalformedURLException {
		WebDriver driver = inicaliza();
		
		try {
			Thread.sleep(1000);
			//clica no botão add todo
			driver.findElement(By.id("addTodo")).click();
			//insere task
			driver.findElement(By.id("task")).sendKeys("Testes Automatizados QA");
			//insere data passada
			driver.findElement(By.id("dueDate")).sendKeys("07/07/2010");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//valida mensagem retorno
			String message =  driver.findElement(By.xpath("//p[@id='message']")).getText();
			assertEquals("Due date must not be in past", message);
			
		} finally  {
			//fechar navegador
			driver.quit();
		}
		
		
		
		
	}
	
	@Test
	public void deveCadastarComSucesso() throws InterruptedException, MalformedURLException {
		
		WebDriver driver = inicaliza();
		
				
		try {
			 Thread.sleep(1000);
			//clica no botão add todo
			WebElement  botao = driver.findElement(By.id("addTodo"));
		    botao.click();
			//insere task
			driver.findElement(By.id("task")).sendKeys("Testes Automatizados QA");
			//insere data atual
			driver.findElement(By.id("dueDate")).sendKeys("10/08/2030");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//valida mensagem retorno
			String message =  driver.findElement(By.xpath("//p[@id='message']")).getText();
			assertEquals("Success!", message);
			
		}finally{
			//fechar navegador
			driver.quit();
		}
		
		
	}
	
	@Test
	public void naoDeveCadastrarSemData() throws InterruptedException, MalformedURLException {
		WebDriver driver = inicaliza();
		try {
			Thread.sleep(1000);
			//clica no botão add todo
			driver.findElement(By.id("addTodo")).click();
			// insere task
			driver.findElement(By.id("task")).sendKeys("Testes Automatizados QA");
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			//valida mensagem retorno
			String message =  driver.findElement(By.xpath("//p[@id='message']")).getText();
			assertEquals("Fill the due date", message);
			
		} finally {
			//fechar navegador
			driver.quit();
		}
		
		
		
	}
	

}
