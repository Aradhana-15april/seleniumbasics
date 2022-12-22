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
      // driver.close();
    }

    @Test
    public void TC_001_verifyObsquraTitle() {
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();
      String expectedTitle = "Obsqura Testing";
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

    @Test
    public void TC_004_verifyFormNoZipNoState(){
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstName = driver.findElement(By.xpath("//input[@class='form-control']"));
        firstName.sendKeys("Aradhana");
        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last name']"));
        lastName.sendKeys("Abraham");
        WebElement userName = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
        userName.sendKeys("aradhanaabraham680@gmail.com");
        WebElement city = driver.findElement(By.xpath("//input[@placeholder='City']"));
        city.sendKeys("Pathanamthitta");
        //WebElement state = driver.findElement(By.xpath("//input[@placeholder='State']"));
       // state.sendKeys("kerala");

        String expectedZipFieldErrorMessage = "Please provide a valid zip.";
        WebElement checkBox = driver.findElement(By.xpath("//input[@class='form-check-input']"));
        checkBox.click();
        WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submit.click();
        WebElement zipFieldValidation = driver.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));
        String actualzipFieldValidation = zipFieldValidation.getText();
        WebElement stateFieldValidation = driver.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
        String expectedStateFieldErrorMessage = "Please provide a valid state.";
        Assert.assertEquals(stateFieldValidation.getText(), expectedStateFieldErrorMessage, "Invalid Message found");
        Assert.assertEquals(actualzipFieldValidation,expectedZipFieldErrorMessage,"Invalid message found");

    }
    @Test
    public void TC_005_verifySubmitform(){
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstName = driver.findElement(By.xpath("//input[@class='form-control']"));
        firstName.sendKeys("Aradhana");
        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last name']"));
        lastName.sendKeys("Abraham");
        WebElement userName = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
        userName.sendKeys("aradhanaabraham680@gmail.com");
        WebElement city = driver.findElement(By.xpath("//input[@placeholder='City']"));
        city.sendKeys("Pathanamthitta");
        WebElement zip = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
        zip.sendKeys("123456");
        WebElement state = driver.findElement(By.xpath("//input[@placeholder='State']"));
        state.sendKeys("kerala");
        WebElement checkBox = driver.findElement(By.xpath("//input[@class='form-check-input']"));
        checkBox.click();
        WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submit.click();
        WebElement successValidationMessage = driver.findElement(By.xpath("//button[@class='btn btn-primary']/following-sibling::div"));
        String actualSuccessMessage = successValidationMessage.getText();
        System.out.println(actualSuccessMessage);
        String expectedSuccessMessage = "Form has been submitted successfully!";

       // WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        //submit.click();
        Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage,"Invalid message found");

    }
    @Test
    public void TC_006_verifyValidationFieldEmpty(){
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        button.click();
        WebElement firstnameFieldValidation = driver.findElement(By.xpath("//div[@class='invalid-feedback']"));
        String expectedFirstNameErrorMessage = "Please enter First name.";
        WebElement lastnameFieldValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[1]/div[2]/div[1]"));
        String expectedLastNameErrorMessage = "Please enter Last name.";
        WebElement userNameValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[1]/div[3]/div/div[2]"));
        String expectedUserNameErrorMessage = "Please choose a username.";
        WebElement cityValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[1]/div[1]"));
        String expectedCityErrorMessage = "Please provide a valid city.";
        WebElement stateFieldValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[2]/div[1]"));
        String expectedStateFieldErrorMessage = "Please provide a valid state.";
        WebElement zipFieldValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[3]/div[1]"));
        String expectedZipFieldErrorMessage = "Please provide a valid zip.";
        WebElement termsConditionFieldValidation = driver.findElement(By.xpath("//input[@id='invalidCheck']//following-sibling::div[1]"));
        String expectedTermsConditionErrorMessage = "You must agree before submitting.";
        button.click();
        Assert.assertEquals(firstnameFieldValidation.getText(), expectedFirstNameErrorMessage, "Invalid Message found");
        Assert.assertEquals(lastnameFieldValidation.getText(), expectedLastNameErrorMessage, "Invalid Message found");
        Assert.assertEquals(userNameValidation.getText(), expectedUserNameErrorMessage, "Invalid Message found");
        Assert.assertEquals(cityValidation.getText(), expectedCityErrorMessage, "Invalid Message found");
        Assert.assertEquals(stateFieldValidation.getText(), expectedStateFieldErrorMessage, "Invalid Message found");
        Assert.assertEquals(zipFieldValidation.getText(), expectedZipFieldErrorMessage, "Invalid Message found");
        Assert.assertEquals(termsConditionFieldValidation.getText(), expectedTermsConditionErrorMessage, "Invalid Message found");

    }

    @Test
    public void TC_007_verifyNewsletterSubscription(){
        driver.get("https://demowebshop.tricentis.com/");
        WebElement newsletter = driver.findElement(By.cssSelector("#newsletter-email"));
        newsletter.sendKeys("aradhanaabraham680@gmail.com");
        WebElement subscribe = driver.findElement(By.cssSelector("#newsletter-subscribe-button"));
        subscribe.click();

    }
    @Test
    public void TC_008_verifyInstantdemoRequestform(){
        driver.get("https://phptravels.com/demo/");
        WebElement firstName = driver.findElement(By.cssSelector("input.first_name"));
        firstName.sendKeys("Aradhana");
        WebElement lastName = driver.findElement(By.cssSelector("input.last_name"));
        lastName.sendKeys("Abraham");
        WebElement businessName = driver.findElement(By.cssSelector("input.business_name"));
        businessName.sendKeys("Aradhana Abraham");
        WebElement email = driver.findElement(By.cssSelector("input.email"));
        email.sendKeys("aradhanaabraham680@gmail.com");
        WebElement value1 = driver.findElement(By.cssSelector("span#numb1"));
        String valueA = value1.getText();
        int number1 = Integer.parseInt(valueA);
        WebElement value2 = driver.findElement(By.cssSelector("span#numb2"));
        String valueB = value2.getText();
        int number2 = Integer.parseInt(valueB);
        WebElement resultField = driver.findElement(By.cssSelector("input#number"));
        int result = number1+number2;
        String Result1 = String.valueOf(result);
        resultField.sendKeys(Result1);
        WebElement submit = driver.findElement(By.cssSelector("button#demo"));
        submit.click();
    }
    }

