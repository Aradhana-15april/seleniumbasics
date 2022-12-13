package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours {
    public static void main(String[] args) {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driverfiles\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://demo.guru99.com/test/newtours/");
        WebElement register = driver.findElement(By.linkText("REGISTER"));
        register.click();
        WebElement name = driver.findElement(By.name("firstName"));
        name.sendKeys("Aradhana");
        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Abraham");
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("8590984992");
        WebElement email = driver.findElement(By.id("userName"));
        email.sendKeys("aradhanaabraham680@gmail.com");
        WebElement address = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
        address.sendKeys("Ranni");
        WebElement city = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
        city.sendKeys("pathanamthitta");
        WebElement state = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
        state.sendKeys("Kerala");
        WebElement pincode = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));
        pincode.sendKeys("689009");
        WebElement Country = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[11]/td[2]/select"));
        Country.sendKeys("India");
        WebElement username = driver.findElement(By.cssSelector("#email"));
        username.sendKeys("aradhanaabraham680@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
        password.sendKeys("asd1");
        WebElement confirmpassword = driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));
        confirmpassword.sendKeys("asd1");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        driver.close();




    }
}
