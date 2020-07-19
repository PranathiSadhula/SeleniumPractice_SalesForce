package testcases;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import seleniumactions.SeleniumActions;

public class TLSDET_Testcase extends SeleniumActions {
	
	long snapNum = (long) (Math.floor(Math.random() * 90000000L) + 10000000L);
//js.executeScript("window.scrollBy(0,window.innerHeight)", "");
	
	// @Parameters({"browser"})
	@BeforeSuite
	public void setUpBrowser(/* String browser */) {
		launchBrowser("chrome");
	}

	// @Parameters({"url"})
	@BeforeClass
	public void launchWebApplication(/* String url */) {
		String url = "https://login.salesforce.com/";
		launchWebApplication(url);

	}
	
	@Test
	public void TC001_SalesForce() throws WebDriverException, IOException {
		try {
			driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("India@123");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("Login")));
			driver.findElement(By.id("Login")).click();
			wait.until(ExpectedConditions.titleIs("Home | Salesforce"));
			WebElement imageIcon = driver.findElement(By.className("uiImage"));
			actionBuilder.moveToElement(imageIcon).build().perform();
			wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("tooltip-invisible")), "View profile"));
			verifyTextContent(driver.findElement(By.className("tooltip-invisible")).getText().equalsIgnoreCase("View profile"), "View Profile text is not visible");
			
			driver.findElement(By.id("add")).click();
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("globalCreateMenuList"))));
			WebElement globalActions = driver.findElement(By.xpath("//*[@class='globalCreateMenuList']//*[text()='Global Actions']"));
			actionBuilder.moveToElement(globalActions).click().build().perform();
			
			driver.findElement(By.xpath("//*[text()='New Lead']"));
			
			driver.findElement(By.className("select")).click();
			actionBuilder.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).build().perform();
			
			driver.findElement(By.xpath("//*[contains(@class,'firstName')]")).sendKeys("Pranathi");
			driver.findElement(By.xpath("//*[contains(@class,'lastName')]")).sendKeys("Sadhula");
			driver.findElement(By.xpath("//*[text()='Company']/following::input")).sendKeys("TestLeaf-Alumi");
			
			driver.findElement(By.xpath("//button[@data-aura-class='uiButton']//*[text()='Save']")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("forceActionLink")));
			System.out.println(driver.findElement(By.className("forceActionLink")).getText());
			
			driver.findElement(By.xpath("//*[contains(@class,'appLauncher')]")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("slds-input")));
			driver.findElement(By.className("slds-input")).sendKeys("view All");
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("slds-button"))));
			driver.findElement(By.className("slds-button")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("slds-input")));
			driver.findElement(By.className("slds-input")).sendKeys("Sales");
			
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@src='https://ap16.salesforce.com/logos/Salesforce/SalesCloud/logo.png']//ancestor::div/following-sibling::div[contains(@class,'slds-app-launcher')]")));
			driver.findElement(By.xpath("//*[@src='https://ap16.salesforce.com/logos/Salesforce/SalesCloud/logo.png']//ancestor::div/following-sibling::div[contains(@class,'slds-app-launcher')]")).click();

			verifyTextContent(driver.findElement(By.xpath("//*[@title='Sales' and text()='Sales']")).equals("Sales"), "Sales Tab is not displayed");
			
			String openUSDValue = driver.findElement(By.xpath("//*[contains(text(),'Open')]/following-sibling::*[@data-aura-class='uiOutputText']")).getText().replaceAll("\\D", "");
			System.out.println("Open usd value is :"+openUSDValue);
			
			
			driver.findElement(By.linkText("Leads")).click();
			
			driver.findElement(By.xpath("//*[@title='Name' and text()='Name']")).click();
			
			List<WebElement> namesList = driver.findElements(By.xpath("//th//*[@data-refid='recordId']"));
			
			//to do sorting and confirm
			
			
			//driver.findElement(By.name("Lead-search-input")).sendKeys("pranathi");
			actionBuilder.sendKeys(driver.findElement(By.name("Lead-search-input")), "sadhula").sendKeys(Keys.ENTER).build().perform();
			
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='slds-spinner_container slds-grid']"))));
			
			List<WebElement> lastNameList = driver.findElements(By.xpath("//th//*[@data-refid='recordId' and contains(text(),'sadhula')]"));
			if(lastNameList.size() == 0) {
				System.out.println("Your Last Name is not found in search");
			}
			driver.findElement(By.xpath("//td[@role='gridcell']/following::*[@class='slds-checkbox']")).click();
			
			
			driver.findElement(By.xpath("//*[@title='Change Status']")).click();
			
			
			driver.findElement(By.xpath("//*[@class='select']")).click();
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Working - Contacted']"))));
			driver.findElement(By.xpath("//*[text()='Working - Contacted']")).click();
			
			driver.findElement(By.xpath("//button[@data-aura-class='uiButton']//*[text()='Save']")).click();
			
			String leadUrl = driver.findElement(By.xpath("//th//*[@data-refid='recordId' and contains(text(),'sadhula')]")).getAttribute("href");
			
			
			//to do new tab
			
			
			
		} catch (Exception wbe) {
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + this.getClass().getSimpleName() + "/" + snapNum + ".jpeg"));
			} catch (WebDriverException e) {
				System.out.println("Webdriver exception occured while taking screenshot");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO exception occured while copying screenshot to location : ./reports/images/"+ this.getClass().getSimpleName());
				e.printStackTrace();
			}


			throw new RuntimeException("FAILED " + wbe.getMessage());
		} 
		
		
	}
	

	public void verifyPageTitle(Boolean condition, String message) throws Exception {
		try {
			assertTrue(condition, message);
		} catch (Throwable e) {
			throw new RuntimeException(message);
		}
	}

	public void verifyTextContent(Boolean condition, String message) throws Exception {
		try {
			assertTrue(condition, message);
		} catch (Throwable e) {
			throw new RuntimeException(message);
		}
	}

}
