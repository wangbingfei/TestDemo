package com.asg.uitest.Demo;

import java.awt.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class NewTest 
{
	//AppiumDriver<WebElement> driver;
	AndroidDriver<WebElement> androidDriver;
	IOSDriver<WebElement> iosDriver;
	private static final String URL ="jdbc:mysql://192.168.22.251:3306/mstar_integration";
	private static final String UNAME = "root";
	private static final String PWD = "123456";
	private static Connection conn = null;
	
	@BeforeClass
	public void setUp() throws Exception
	{
		setUpAndroid();
		//setUpIos();       
	}
	
    @AfterClass
    public void tearDown() throws Exception 
    {
    		androidDriver.quit();
    }
    @Test
	public void Test() throws InterruptedException, ClassNotFoundException, SQLException 
    {	
    		
    		androidTest();
    		//iosTest();
	}
  
    
    public void swipeToLeft(int duration) throws InterruptedException 
    {
    	
		Thread.sleep(1000);
		
    		for (int i = 0; i < 2; i++)
    		{
    			
    			try 
    			{
    				int width = androidDriver.manage().window().getSize().width;
    	    			int height = androidDriver.manage().window().getSize().height;
    	    			androidDriver.swipe(width*7/8, height/2, width*1/8, height/2, duration);
    				Thread.sleep(1000);

			} 
    			catch (Exception e) 
    			{
				System.out.println("引导页滑动失败："+e);
			}
    			
    		}
    		
    	
	}
    
    public void setUpAndroid() throws MalformedURLException
    {
    		//手机版本配置
		DesiredCapabilities capabilities = new DesiredCapabilities();	
		capabilities.setCapability("deviceName", "Clone - Samsung Galaxy S6 - 5.0.0 - API 21 - 1440x2560");
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "5.0");
		capabilities.setCapability("appium-version", "1.6.5");
		//APP配置
		capabilities.setCapability("appPackage", "com.iShangGang.iShangGang");
		capabilities.setCapability("appActivity", "com.asg.act.WelcomeAct");
		capabilities.setCapability("sessionOverride", true);    //每次启动覆盖session
		capabilities.setCapability("unicodeKeyboard", true);    //启动键盘 
		capabilities.setCapability("resetKeyboard", false);		
		androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		androidDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        
    }
    
    public  void setUpIos() throws MalformedURLException
    {
    		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName", "Test");
        capabilities.setCapability("udid", "ad497b716a5a614b9657955be51e31289676534d");
        capabilities.setCapability("platformVersion", "9.3.4");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.APP, "com.iShangGang.iShangGang");
        capabilities.setCapability(MobileCapabilityType.UDID, "ad497b716a5a614b9657955be51e31289676534d");
        iosDriver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4727/wd/hub"), capabilities);
    }
    
    public void androidTest() throws InterruptedException
    {
    		swipeToLeft(800);
		//立即体验
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/guide_now")).click();
		//我的模块
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/main_self")).click();
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/self_username")).click();
		
		//选择账号密码登陆
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/left_rb")).click();
		String account = androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/login_account_psw")).toString();
		if (account != null)
		{
			androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/login_account_psw")).clear();
		}
		else 
		{
			androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/login_account_psw")).click();
		}
		//输入账号
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/login_account_psw")).sendKeys("13162865863");
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/login_psw_ed")).sendKeys("123456");
		androidDriver.findElement(By.id("com.iShangGang.iShangGang:id/login_submit")).click();
    }
    
    public void iosTest() throws InterruptedException
    {
    		swipeToLeft(800);
    		iosDriver.findElementByAccessibilityId("我的").click();
    		
    }
	
}

