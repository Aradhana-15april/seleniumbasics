package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumBasics {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driverfiles\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("Edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driverfiles\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid Browser");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @BeforeMethod
    public void setUp() {
        //pre condition method
        testInitialize("Chrome");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void TC_001_verifyObsquraTitle() {
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Obsqura Testing ";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }

    @Test
    public void TC_002_verifySingleInputMessage() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement messagefield = driver.findElement(By.id("single-input-field"));
        messagefield.sendKeys("Test");
        WebElement showmessage = driver.findElement(By.id("button-one"));
        showmessage.click();
        WebElement message = driver.findElement(By.id("message-one"));
        String actualMessage = message.getText();
        String expectedMessage = "Your Message : Test";
        Assert.assertEquals(actualMessage, expectedMessage, "Invalid message");
    }
    @Test
    public void TC_003_verifyTwoInputMessage(){
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement entervalue = driver.findElement(By.id("value-a"));
        entervalue.sendKeys("1");
        WebElement nextvalue = driver.findElement(By.id("value-b"));
        nextvalue.sendKeys("2");
        WebElement gettotal = driver.findElement(By.id("button-two"));
        gettotal.click();
        WebElement text = driver.findElement(By.id("message-two"));
        String actualtotal = text.getText();
        String expectedTotal = "Total A + B : 3";
        Assert.assertEquals(actualtotal,expectedTotal,"Invalid total");
    }
}
