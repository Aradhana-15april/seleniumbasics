package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BasicCommands {
    public static void main(String[] args) {
        WebDriver driver;

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles\\chromedriver.exe");


        driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://selenium.obsqurazone.com/index.php");
        String sourcecode= driver.getPageSource();
        System.out.println(sourcecode);
        String CurrentUrl=driver.getCurrentUrl();
        System.out.println(CurrentUrl);
        String title= driver.getTitle();
        System.out.println(title);
        driver.close();
      //  driver=new EdgeDriver();
       // System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles\\msedgedriver.exe");
      //  driver.get("https://selenium.obsqurazone.com/index.php");
      //  driver.manage().window().maximize();
      //  driver.close();






    }
}
