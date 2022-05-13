package Pages;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;
import com.mongodb.MapReduceCommand.OutputType;

import Base.Base;

public class GSD extends Base{
	By email=By.xpath("//input[@type='email']");
	By next=By.xpath("//input[@type='submit']");
	By pass=By.name("passwd");
	By acc=By.id("user-name");
	By yes=By.xpath("//input[@value='Yes']");
	By search=By.id("searchbox");
	By icon=By.className("icomoon-search2");
	By gsd=By.xpath("(//span[text()='GSD'])[3]");
	By loc=By.xpath("//button[@id='menu4']");
	By canada=By.xpath("(//a[text()='Canada'])[2]");
	By loc1=By.xpath("(//span[@class='optionCountrySelected'])[2]");
	By h1=By.xpath("(//div[@class='col-sm-12 col-md-12 tile-inner-header'])[1]");
	By h2=By.xpath("(//div[@class='col-sm-12 col-md-12 tile-inner-header'])[2]");
	By h3=By.xpath("(//div[@class='col-sm-12 col-md-12 tile-inner-header'])[3]");
	By h4=By.xpath("(//div[@class='col-sm-12 col-md-12 tile-inner-header'])[4]");
	By item=By.xpath("//div[@class='p-1 flex-fill']/div");
	String data[]=new String[5];
	


	

	public void login() throws Exception {
		logger = report.createTest("Login into Becognizant.");
	
		data=ExcelRead.readExcelData("signin");
		wait(20,email);
		driver.findElement(email).sendKeys(data[0]);
		driver.findElement(next).click();
		wait(20,pass);
		driver.findElement(pass).sendKeys(data[1]);
		driver.findElement(next).click();
		Thread.sleep(30000);
		reportPass("Email and Password Verified sucessfully");

		if (driver.getTitle().contains("Be.Cognizant"))
			// Pass
			System.out.println("Page title contains Be.Cognizant\n");
		else
			// Fail
			System.out.println("Page title doesn't contains Be.Cognizant\n");
		String name=driver.findElement(acc).getText();
		System.out.println("The name for the Acoount is: "+name);
		
		Screenshot("Account");
		
		reportPass("Be.Cognizant Page is reached sucessfully");
		
		
	}
	
	public void getData() throws Exception {
		logger = report.createTest("Obtain the options from GSD.");
		
		driver.findElement(search).sendKeys(data[2]);
		driver.findElement(icon).click();
		wait(20,gsd);
		String currentHandle= driver.getWindowHandle();
		driver.findElement(gsd).click();
		reportPass("GSD Page is reached sucessfully");
		Set<String> handle1=driver.getWindowHandles();
		for(String actual: handle1) {
			if(!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);
			}
		}
		driver.switchTo().frame("appFrame");
		wait(20,loc);
		driver.findElement(loc).click();
		wait(20,canada);
		driver.findElement(canada).click();
		wait(20,loc1);
		String location=driver.findElement(loc1).getText();
		System.out.println("The country selected is: "+location);
		Thread.sleep(3000);

		Screenshot("GSD");
		
	
			System.out.println("\n************************************************");
			System.out.println("            "+driver.findElement(h1).getText());
			System.out.println("************************************************");
			List<WebElement> items1 = driver.findElements(item);
			for (int i = 0; i < 7; i++) {
				System.out.println(items1.get(i).getText());
				
			}
	
			System.out.println("************************************************");
			System.out.println("             "+driver.findElement(h2).getText());
			System.out.println("************************************************");
			List<WebElement> items2 = driver.findElements(item);
			for (int i = 7; i < 14; i++) {
			
				System.out.println(items2.get(i).getText());
			}
		
			System.out.println("************************************************");
			System.out.println("             "+driver.findElement(h3).getText());
			System.out.println("************************************************");
			List<WebElement> items3 = driver.findElements(item);
			for (int i = 14; i < 21; i++) {
				
				System.out.println(items3.get(i).getText());
			}
		
			System.out.println("************************************************");
			System.out.println("             "+driver.findElement(h4).getText());
			System.out.println("************************************************");
			List<WebElement> items4 = driver.findElements(item);
			for (int i = 21; i < 27; i++) {
			
				System.out.println(items4.get(i).getText());
			}
		

		reportPass("The options are obtained sucessfully");
		} 
	}
	
