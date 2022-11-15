package com.crm.qa.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FreeCrmTest {
    public static WebDriver driver;
    static JavascriptExecutor js;

    @BeforeMethod
    public void setup() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get("https://www.freecrm.com/index.html");
    }


    @Test
    public void freeCrmTitleTest() throws InterruptedException, IOException {
        String title = driver.getTitle();
        System.out.println("title is: " + title);
        getRunTimeInfoMessage("info", title);

        if (title.equals("Free CRM software in the cloud powers sales and customer serviceQQQQ")) {
            getRunTimeInfoMessage("info", "title is correct!! YAY!!!");
            Assert.assertTrue(true);
        } else {
            getRunTimeInfoMessage("error", "title is not correct!! BUG BUG BUG!!!");
            takeScreenshot("freecrmloginpage");
            Assert.assertTrue(false);
        }

    }

    public static void getRunTimeInfoMessage(String messageType, String message) throws InterruptedException {
        js.executeScript("if (!window.jQuery) {"
                + "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
                + "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
                + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
        Thread.sleep(5000);

        js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

        js.executeScript("$('head').append('<link rel=\"stylesheet\" "
                + "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
        Thread.sleep(5000);
        js.executeScript("$.growl({ title: 'GET', message: '/' });");

        if(messageType.equals("error")){
            js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
        }else if(messageType.equals("info")){
            js.executeScript("$.growl.notice({ title: 'Notice', message: '"+message+"' });");
        }else if(messageType.equals("warning")){
            js.executeScript("$.growl.warning({ title: 'Warning!', message: '"+message+"' });");
        }

    }

    public static void takeScreenshot(String fileName) throws IOException{
        // Take screenshot and store as a file format
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // now copy the screenshot to desired location using copyFile //method
        FileUtils.copyFile(src,
                new File("C:\\Users\\HP\\screenshots" + fileName +".png"));

    }

}