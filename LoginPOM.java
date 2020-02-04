package bmc.com;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

 

public class LoginPOM {

//All web element which are used on the Login page

WebElement usernameBox;

WebElement passwordBox;

WebElement cookiesCheckBox;

WebElement loginButton;

WebElement verifyButton;

public LoginPOM(WebDriver driver) {

//for maximize the window size

driver.manage().window().maximize();

//Find the web element by id

usernameBox = driver.findElement(By.id("username"));

passwordBox = driver.findElement(By.id("password"));

cookiesCheckBox =driver.findElement(By.id("rememberUn"));

loginButton = driver.findElement(By.id("Login"));

}

//Login with cookies method

public void LoginwithCookies(String username, String password) {

usernameBox.sendKeys(username);

passwordBox.sendKeys(password);

//cookiesCheckBox.click();

loginButton.click();

}

// Login without cookies



}