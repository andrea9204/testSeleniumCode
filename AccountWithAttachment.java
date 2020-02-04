package newpackage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class AccountWithAttachment {
	WebDriver driver = null;
	String testUrl = "https://login.salesforce.com";
	private Map<String, Object> vars;
	
	@BeforeMethod

	//Browser open

	public void setUp(){
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Driver\\chromedriver.exe");
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
		createAccount();
		uploadFile();
		driver.quit();
	}
		
	public void createAccount() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.linkText("Accounts")).click();
		driver.findElement(By.name("new")).click();
		driver.findElement(By.id("acc2")).click();
		driver.findElement(By.id("acc2")).sendKeys("Account1");
		driver.findElement(By.id("acc6")).click();
		
		{
			   WebElement dropdown = driver.findElement(By.id("acc6"));
			   dropdown.findElement(By.xpath("//option[. = 'Investor']")).click();
			 }
			 driver.findElement(By.id("acc6")).click();
			 driver.findElement(By.name("save")).click(); }
	
	public void uploadFile() throws InterruptedException{
			Thread.sleep(3000);
			//WebElement fileInput = driver.findElement(By.name("NewFile")); //.click();
			//fileInput.sendKeys("C:/Users/acorrea/Desktop/images1.jpg");
			driver.findElement(By.name("NewFile"));//.click();
		    driver.switchTo().activeElement().sendKeys("C:/Users/acorrea/Desktop/images1.jpg");
		    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		    driver.findElement(By.id("convertButton"));
			Thread.sleep(2000);				
}
}
