package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BasicCommands2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles\\msedgedriver.exe");
        WebDriver driver=new EdgeDriver();
        driver.get("https://selenium.obsqurazone.com/index.php");
        driver.manage().window().maximize();
        driver.close();
    }
}
