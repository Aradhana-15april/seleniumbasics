package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SeleniumDemo {


    WebDriver driver;
    public void testInitialize(String browser) {
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driverfiles\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driverfiles\\msedgedriver.exe");
            driver = new EdgeDriver();


        }else{
            throw new RuntimeException("Invalid Browser");

        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        }
        @BeforeMethod
        public void setUp(){
        testInitialize("Chrome");
        testInitialize("edge");
        }
        @AfterMethod
    public void tearDown(){
        //driver.close();
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
    public void verifyValuesInDropDown() {
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        List<String> expDropDownList = new ArrayList<>();
        expDropDownList.add("ALBANIA");
        expDropDownList.add("ALGERIA");
        expDropDownList.add("AMERICAN SAMOA");
        expDropDownList.add("ANDORRA");
        List<String> actDropDownList = new ArrayList<>();
        WebElement countryDropDown = driver.findElement(By.xpath("//select[@name='country']"));
        Select select = new Select(countryDropDown);
        List<WebElement> dropDownOption = select.getOptions();
        System.out.println(dropDownOption.size());
        for (int i = 0; i < 4; i++) {
            //System.out.println(dropDownOption.get(i).getText());
            actDropDownList.add(dropDownOption.get(i).getText());
        }
        System.out.println(actDropDownList);
        Assert.assertEquals(actDropDownList, expDropDownList, "DropDown values not found");
        //select.selectByVisibleText("INDIA");
        //select.selectByIndex(23);
        select.selectByValue("INDIA");
        select.getAllSelectedOptions();
    }




    }
