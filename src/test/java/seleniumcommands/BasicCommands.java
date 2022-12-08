package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BasicCommands {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.obsqurazone.com/index.php");
        driver.manage().window().maximize();
        driver.close();



    }
}
