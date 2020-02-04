package bmc.com;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

public class ContractNOrder {

	WebDriver driver = null;

	String testUrl = "https://login.salesforce.com";

	private Map<String, Object> vars;
	
	@BeforeMethod

	//Browser open

	public void setUp(){

		//Download the ChromeDriver. To download click here
	
		//Set the system property to open the chrome browser.
	
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumAutomation\\RemedyforceAutoServices\\chromedriver_win32\\chromedriver.exe");
	
		driver = new ChromeDriver();
	
		driver.get(testUrl);
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public String waitForWindow(int timeout) {
		 try {
		   Thread.sleep(timeout);
		 } catch (InterruptedException e) {
		   e.printStackTrace();
		 }
		 Set<String> whNow = driver.getWindowHandles();
		 Set<String> whThen = (Set<String>) vars.get("window_handles");
		 if (whNow.size() > whThen.size()) {
		   whNow.removeAll(whThen);
		 }
		 return whNow.iterator().next();
		}

	@Test

	public void validLogin() throws InterruptedException {

		LoginPOM loginpage= new LoginPOM(driver);
	
		loginpage.LoginwithCookies("sforg@sf.com", "newuser@1");
	
		Thread.sleep(10000);
	
		if(driver.findElement(By.id("tryLexDialogX")).isDisplayed()){
			driver.findElement(By.id("tryLexDialogX")).click();
		}
		createContract();
		driver.quit();
	}
	
	public void createContract() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.linkText("Contracts")).click();
		driver.findElement(By.name("new")).click();
		
		driver.findElement(By.id("ctrc7")).click();
		driver.findElement(By.id("ctrc7")).sendKeys("Garcia Inc");
		driver.findElement(By.className("dateFormat")).click();
		driver.findElement(By.id("ctrc40")).sendKeys("1");
		
		
		driver.findElement(By.name("save")).click();
		
		WebElement ele = driver.findElement(By.id("ctrc2_ileinner"));
		System.out.println("Contract Number :: " + ele.getText());
		String contNum = ele.getText();
		
		driver.findElement(By.linkText("Orders")).click();
		driver.findElement(By.name("new")).click();
		
		driver.findElement(By.id("accid")).sendKeys("Garcia Inc");
		driver.findElement(By.id("Contract")).sendKeys(contNum);
		driver.findElement(By.className("dateFormat")).click();
		driver.findElement(By.name("save")).click();
		

	}
	
}
