package com.excilys.ui;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class InterfaceTest {
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void init() {
		webDriver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void close() {
		webDriver.close();
	}
	
	@Before
	public void initFirstPageAtDashboard() {
		webDriver.get("http://localhost:8080/computer-database/dashboard");
	}
	
	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheNameIsNotPresent() {
		//GIVEN
		//WHEN
		webDriver.findElement(By.id("addComputer")).click();
		webDriver.findElement(By.tagName("form")).submit();
		String errorMessage = webDriver.findElement(By.id("errorName")).getText();
		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("Name is required");
	}

	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheDateFormatOfIntroducedDateIsInvalid() {
		//GIVEN
		//WHEN
		webDriver.findElement(By.id("addComputer")).click();
		webDriver.findElement(By.id("computerName"))
		.sendKeys("testSelenium");
		webDriver.findElement(By.id("introduced"))
		.sendKeys("bad format");
		webDriver.findElement(By.tagName("form")).submit();
		String errorMessage = webDriver.findElement(By.id("errorIntroduced")).getText();
		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("Invalid format (yyyy-mm-dd hh:mm:ss)");
	}
	
	@Test
	public void addComputerShouldShowAnErrorMessageWhenTheDateFormatOfDiscontinuedDateIsInvalid() {
		//GIVEN
		//WHEN
		webDriver.findElement(By.id("addComputer")).click();
		webDriver.findElement(By.id("computerName"))
		.sendKeys("testSelenium");
		webDriver.findElement(By.id("introduced"))
		.sendKeys("2012-12-12 12:12:12");
		webDriver.findElement(By.id("discontinued"))
		.sendKeys("bad format");
		webDriver.findElement(By.tagName("form")).submit();
		String errorMessage = webDriver.findElement(By.id("errorDiscontinued")).getText();
		//THEN
		Assertions.assertThat(errorMessage).isEqualTo("Invalid format (yyyy-mm-dd hh:mm:ss)");
	}

}