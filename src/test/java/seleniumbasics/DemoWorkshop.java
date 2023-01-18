package seleniumbasics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoWorkshop {
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
    @Parameters({"browser","base_url"})//parametre passing from parametre testng xml
    public void setUp(String browserName,String url) {
        //pre condition method
        testInitialize(browserName);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
        }
       // driver.close();
    }

    @Test
    public void TC_001_verifyDemoWorkshopTitle() throws IOException {
        String actualTitle = driver.getTitle();
        //String expectedTitle = "Demo Web Shop";
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "HomePage");
        String expectedTitle = data.get(0).get(1);//0 is row,and 1 is column
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }

    @Test
    public void TC_002_verifyLogIn() {
        WebElement login = driver.findElement(By.className("ico-login"));
        login.click();
        String emailid = "aradhanaabraham680@gmail.com";
        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("aradhanaabraham680@gmail.com");
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("aradhana@123");
        WebElement button = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        button.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(emailid, actualEmail, "login failed");

    }
    @Test
    public void TC_003_verifyRegistration() {
        driver.get("https://demowebshop.tricentis.com/");
        WebElement reg1 = driver.findElement(By.xpath("//a[@class='ico-register']"));
        reg1.click();
        List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
        selectGender("F", gender);
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("Aradhana");
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys("Abraham");
        WebElement emailField = driver.findElement(By.id("Email"));
        String email = "aradhanaabraham6800@gmail.com";
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("Password"));
        passwordField.sendKeys("aradhana@123");
        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys("aradhana@123");
        WebElement register = driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(actualEmail, email, "Registration failed");
    }

    public void selectGender(String gen, List<WebElement> gender) {
        for (int i = 0; i < gender.size(); i++) {
            String genderValue = gender.get(i).getAttribute("value");
            if (genderValue.equals(gen)) {
                gender.get(i).click();
            }
        }
    }
    @Test(dataProvider = "Credentials")
    public void TC_004_verifyInvalidLogin(String userName, String pword){
        WebElement login1= driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        String mail = userName;
        WebElement email= driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(mail);
        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys(pword);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement error = driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
        String actMsg=error.getText();
        String expMsg= "Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actMsg,expMsg,"error message not found");
    }
    @DataProvider(name = "Credentials")//verify invalid login and credentials are same to use test data for upper test case,,,both should be same
    public Object[][] userCredentials(){
        Object[][] data= {{"aradhanaabraham68000@gmail.com","aradhana@123"},{"aradhanaabraham6800@gmail.com","aradhana@1"},{"aradhana@gmail.com","Aradhana"}};
        return data;
    }
    @Test
    public void TC_005_verifyRegistrationDataRandomGeneration(){
        WebElement reg1= driver.findElement(By.xpath("//a[@class='ico-register']"));
        reg1.click();
        String fName= RandomDataUtility.getfName();
        String lName = RandomDataUtility.getlName();
        String email= RandomDataUtility.getRandomEmail();
        String pWord = fName+"@123";
        List<WebElement> gender= driver.findElements(By.xpath("//input[@name='Gender']"));
        selectGender("F",gender);
        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys(fName);
        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys(lName);
        WebElement emailField=driver.findElement(By.id("Email"));
        emailField.sendKeys(email);
        WebElement passwordField=driver.findElement(By.id("Password"));
        passwordField.sendKeys(pWord);
        WebElement passwordConfirm=driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys(pWord);
        WebElement register=driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail=userAccount.getText();
        Assert.assertEquals(actualEmail,email,"Registration failed");
    }
    @Test
    public void TC_006_verifyRegistrationExcel() throws IOException {
        WebElement reg1= driver.findElement(By.xpath("//a[@class='ico-register']"));
        reg1.click();
        List<WebElement> gender= driver.findElements(By.xpath("//input[@name='Gender']"));
        selectGender("F",gender);
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "Registration");
        WebElement firstName=driver.findElement(By.id("FirstName"));
        String fName= data.get(0).get(1);
        firstName.sendKeys(fName);
        WebElement lastName=driver.findElement(By.id("LastName"));
        String lName= data.get(1).get(1);
        lastName.sendKeys(lName);
        WebElement emailField=driver.findElement(By.id("Email"));
        String email= RandomDataUtility.getRandomEmail();
        emailField.sendKeys(email);
        WebElement passwordField=driver.findElement(By.id("Password"));
        String pword= data.get(3).get(1);
        passwordField.sendKeys(pword);
        WebElement passwordConfirm=driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys(pword);
        WebElement register=driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail=userAccount.getText();
        Assert.assertEquals(actualEmail,email,"Registration failed");
    }
    @Test
    @Parameters({"userName","pword"})
    public void TC_007_verifyLoginByParameteriztion(String userName, String password){
        WebElement login1= driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        WebElement email= driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(userName);
        WebElement password1 = driver.findElement(By.xpath("//input[@id='Password']"));
        password1.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount= driver.findElement(By.xpath("(//a[@class='account'])[1]"));
        String actMail= userAccount.getText();
        Assert.assertEquals(userName,actMail,"Login Failed");
    }

}
