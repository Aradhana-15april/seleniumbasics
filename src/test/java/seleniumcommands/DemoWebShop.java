package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoWebShop {
    public static void main(String[] args) {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driverfiles\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://demowebshop.tricentis.com/register");
        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement email = driver.findElement(By.name("Email"));
        email.sendKeys("aradhanaabraham680@gmail.com");
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("aradhana@123");
        WebElement loginbutton = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));//x path is copying as login button not works properly.not able to click its class name so we just right click the html and copy x path.
        loginbutton.click();
        driver.close();
        //exception:no such element exception(unable to find the web element).

    }
}
