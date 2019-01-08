package com.tal.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FacebookLogin {
    public static void main(String [] args){
        String url = "https://he-il.facebook.com/";
        System.setProperty("webdriver.chrome.driver","/Users/hakmon/Documents/chromedriver/chromedriver");

        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get(url);

        try{
            driver.findElement(By.id("email")).sendKeys("your user name");
            driver.findElement(By.id("pass")).sendKeys("your password");
            driver.findElement(By.id("loginbutton")).click();
            driver.wait(1000);


        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}
