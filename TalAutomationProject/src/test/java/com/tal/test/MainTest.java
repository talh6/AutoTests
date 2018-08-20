package com.tal.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class MainTest {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
			
			String searchWord = "תל אביב";
			String type = "Android";
			
			searchWordYadTwo(type,searchWord);
			
			

	}
	
	public static void searchWordYadTwo(String type,String searchWord) throws MalformedURLException, InterruptedException {
		if(type.equals("Android")) {
			AndroidDriver <AndroidElement> driver; 
			File appDir = new File ("src");
			File app= new File("yad2_v3.1_apkpure.com.apk");
			
			DesiredCapabilities capabilities= new DesiredCapabilities();
			
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X API 28");
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
			//click on the category
			driver.findElementsByClassName("android.view.View").get(0).click();
			
			//search the search word
			driver.findElementById("city").sendKeys(searchWord);
			
			//click on the search button
			driver.findElementByClassName("android.widget.Spinner").click();
		}
		
		else if(type.equals("WEB"))
		{
			  String url = "http://www.yad2.co.il/"; 
			  System.setProperty("webdriver.chrome.driver", "/Users/user/SeleniumDriver/chromedriver");

			  WebDriver driver = new ChromeDriver();
			  driver.get(url);
			  Thread.sleep(5000);  
			  
			  //search for the search word
			  WebElement searchBox = driver.findElement(By.name("q"));
			  searchBox.sendKeys(searchWord);
			  searchBox.submit();
			  Thread.sleep(5000); 
			  
			  driver.quit();
		}
		
	}

}
