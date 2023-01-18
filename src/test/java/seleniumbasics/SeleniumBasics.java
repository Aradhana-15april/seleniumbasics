package seleniumbasics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("this is before suit");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("This is before Test");

    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("this is before class");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("this is after test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("this is after class");
    }


    @BeforeMethod
    public void setUp() {
        //pre condition method
        testInitialize("Chrome");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
        }
        driver.close();
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
    public void TC_003_verifyTwoInputMessage() {
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
        Assert.assertEquals(actualtotal, expectedTotal, "Invalid total");
    }

    @Test
    public void TC_004_verifyFormNoZipNoState() {
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

       // String expectedZipFieldErrorMessage = "Please provide a valid zip.";
        WebElement checkBox = driver.findElement(By.xpath("//input[@class='form-check-input']"));
        checkBox.click();
        WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submit.click();
        WebElement zipFieldValidation = driver.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));
        String actualzipFieldValidation = zipFieldValidation.getText();
        String expectedZipFieldErrorMessage = "Please provide a valid zip.";
        WebElement stateFieldValidation = driver.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
        String expectedStateFieldErrorMessage = "Please provide a valid state.";
        String actualstateFieldValidation = stateFieldValidation.getText();

        Assert.assertEquals(actualstateFieldValidation, expectedStateFieldErrorMessage, "Invalid Message found");
        Assert.assertEquals(actualzipFieldValidation, expectedZipFieldErrorMessage, "Invalid message found");

    }

    @Test
    public void TC_005_verifySubmitform() {
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
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Invalid message found");

    }

    @Test
    public void TC_006_verifyValidationFieldEmpty() {
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
    public void TC_007_verifyNewsletterSubscription() {
        driver.get("https://demowebshop.tricentis.com/");
        WebElement newsletter = driver.findElement(By.cssSelector("#newsletter-email"));
        newsletter.sendKeys("aradhanaabraham680@gmail.com");
        WebElement subscribe = driver.findElement(By.cssSelector("#newsletter-subscribe-button"));
        subscribe.click();

    }

    @Test
    public void TC_008_verifyInstantdemoRequestform() {
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
        int result = number1 + number2;
        String Result1 = String.valueOf(result);
        resultField.sendKeys(Result1);
        WebElement submit = driver.findElement(By.cssSelector("button#demo"));
        submit.click();
    }

    @Test
    public void TC_008_verifyQuitClose() {
        driver.get("https://demo.guru99.com/popup.php");
        WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickHere.click();
        driver.quit();
    }

    @Test
    public void TC_009_verifyNavigateTo() {
        //  driver.get("https://demowebshop.tricentis.com/");
        driver.navigate().to("https://demowebshop.tricentis.com/");


    }

    @Test
    public void TC_0010_verifyRefresh() {
        driver.get("https://demowebshop.tricentis.com/");
        WebElement email = driver.findElement(By.id("newsletter-email"));
        email.sendKeys("test@gmail.com");
        driver.navigate().refresh();
    }

    @Test
    public void TC_0011_verifyForwardandBackward() throws InterruptedException {
        driver.get("https://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        driver.navigate().back();
       Thread.sleep(10000);
        driver.navigate().forward();
    }

    @Test
    public void TC_0012_verifyWebElementCommand() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subject = driver.findElement(By.id("subject"));
        subject.sendKeys("Selenium");
        WebElement description = driver.findElement(By.id("description"));
        description.sendKeys("Automation Testing");
        WebElement submit = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        submit.click();
        Thread.sleep(10000);
        WebElement successMessage = driver.findElement(By.id("message-one"));
        String actualSuccessMessage = successMessage.getText();
        System.out.println(actualSuccessMessage);
        String expectedSuccessMessage = "Form has been submitted successfully!";
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Invalid message found");
        //driver.quit();

    }

    @Test
    public void TC_013_verifyIsDisplayed() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys("Selenium");
        //boolean status = subjectField.isDisplayed();
        boolean status = subjectField.isDisplayed();
        System.out.println(status);
        Assert.assertTrue(status, "Subject field is not displayed");
    }

    @Test
    public void TC_014_verifyIsSelected() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement singleDemoCheckBox = driver.findElement(By.xpath("//input[@id='gridCheck']"));
        boolean statusBeforeClick = singleDemoCheckBox.isSelected();
        System.out.println(statusBeforeClick);
        Assert.assertFalse(statusBeforeClick, "Checkbox is selected");
        singleDemoCheckBox.click();
        boolean status = singleDemoCheckBox.isSelected();
        System.out.println(status);
        Assert.assertTrue(status, "Checkbox is not selected");
    }

    @Test
    public void TC_015_verifyIsEnabled() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        boolean status = submitButton.isEnabled();
        System.out.println(status);
        Assert.assertTrue(status, "submit button not enabled");
        Point point = submitButton.getLocation();
        System.out.println(point.x + "," + point.y);
        Dimension dim = submitButton.getSize();
        System.out.println(dim.height + "," + dim.width);
        String backgroundColor = submitButton.getCssValue("background-color");
        System.out.println(backgroundColor);
        WebElement element = driver.findElement(By.tagName("input"));
        System.out.println(element);
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        System.out.println(elements);
        submitButton.submit();
    }


    @Test
    public void TC_016_verifyDiffrenceBetweenFindElementandFindElements() {
        driver.get("https://selenium.obsqurazone.com/radio-button-demo.php");
        //WebElement radiobuttonM = driver.findElement(By.id("inlineRadio11"));
        // radiobuttonM.click();
        //WebElement radiobuttonf = driver.findElement(By.id("inlineRadio21"));
        //  radiobuttonf.click();
        List<WebElement> genders = driver.findElements(By.xpath("//input[@name='student-gender']"));
        System.out.println(genders.size());
        for (int i = 0; i < genders.size(); i++) {
            String gender = genders.get(i).getAttribute("value");
            if (gender.equals("Male")) {
                genders.get(i).click();


            }
        }
    }

    @Test
    public void TC_017_verifyMultipleWindowHandlling() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickHere.click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement emailid = driver.findElement(By.xpath("//input[@name='emailid']"));
                emailid.sendKeys("aradhanaabraham@gmail.com");
                WebElement button = driver.findElement(By.xpath("//input[@name='btnLogin']"));
                button.click();
                driver.close();
            }
        }

        driver.switchTo().window(parentWindow);

    }

    @Test
    public void TC_018_verifyNewTab() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement newTab = driver.findElement(By.id("tabButton"));
        newTab.click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                String actualMessage = text.getText();
                System.out.println(actualMessage);
                String expectedMessage = "This is a sample page";
                Assert.assertEquals(actualMessage, expectedMessage, "Invalid message found");

                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);

    }

    @Test
    public void TC_019_verifyNewWindow() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement newWindow = driver.findElement(By.id("windowButton"));
        newWindow.click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                String actualMessage = text.getText();
                System.out.println(actualMessage);
                String expectedMessage = "This is a sample page";
                Assert.assertEquals(actualMessage, expectedMessage, "Invalid message found");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_020_verifyNewWindowMessage() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement newWindowMessage = driver.findElement(By.id("messageWindowButton"));
        newWindowMessage.click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id(""));
                String actualMessage = text.getText();
                System.out.println(actualMessage);
                String expectedMessage = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";
                Assert.assertEquals(actualMessage, expectedMessage, "Invalid message found");
                driver.close();

            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_020_verifySimpleAlert() {
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
        clickMe.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();


    }

    @Test
    public void TC_020_verifyConfirmationAlert() {
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement confirmationClick = driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
        confirmationClick.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

    }

    @Test
    public void verifyPromptAlert() {
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement promptClick = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
        promptClick.click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.sendKeys("Aradhana");
        alert.accept();

    }

    @Test
    public void verifyTextFrame() {
        driver.get("https://demoqa.com/frames");
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        int numberofFrames = frames.size();
        System.out.println(numberofFrames);
        //driver.switchTo().frame("frame1");
        //driver.switchTo().frame(3);
        WebElement frame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frame);
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String text = heading.getText();
        System.out.println(text);
        driver.switchTo().parentFrame();
    }

    @Test
    public void verifyRightClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClick = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(rightClick).build().perform();
    }

    @Test
    public void verifyDoubleClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClick = driver.findElement(By.xpath("//button[text() ='Double-Click Me To See Alert']"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClick).build().perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    @Test
    public void verifyMouseHover() {
        driver.get("https://demoqa.com/menu/");
        WebElement mainItem1 = driver.findElement(By.xpath("//a[text()='Main Item 1']"));
        Actions action = new Actions(driver);
        //action.moveToElement(mainItem1).build().perform();
        action.moveToElement(mainItem1).build().perform();
        // action.moveToElement(mainItem1,50,50).build().perform();
        //  action.moveByOffset(40,80).build().perform();

    }

    @Test
    public void verifyDragDrop() {
        driver.get("https://demoqa.com/droppable");
        WebElement dragMe = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement dropHere = driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions action = new Actions(driver);
        action.dragAndDrop(dragMe, dropHere).build().perform();

    }

    @Test
    public void verifyDragDropByOfset() {
        driver.get("https://demoqa.com/dragabble");
        WebElement dragMe = driver.findElement(By.xpath("//div[text()='Drag me']"));
        Actions action = new Actions(driver);
        //action.dragAndDropBy(dragMe, 60, 70).build().perform();
        action.dragAndDropBy(dragMe,50,40).build().perform();

    }

    @Test
    public void verifyDragandResize() {
        driver.get("https://demoqa.com/resizable");
        WebElement resize = driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']"));
        Actions action = new Actions(driver);
        action.clickAndHold(resize).build().perform();
        action.dragAndDropBy(resize, 200, 200).build().perform();
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

    @Test
    public void verifyMethodsInSelectClass() {
        driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
        WebElement dropDown = driver.findElement(By.xpath("(//select[@class='spTextField'])[1]"));
        Select select = new Select(dropDown);
        boolean multipleStatus = select.isMultiple();
        System.out.println(multipleStatus);
        select.selectByVisibleText("Performance Testing");
        select.selectByValue("msmanual");
        List<WebElement> selectedOption = select.getAllSelectedOptions();
        for (int i = 0; i < selectedOption.size(); i++) {
            System.out.println(selectedOption.get(i).getText());
        }
        select.deselectByValue("msmanual");
        select.deselectAll();
    }

    @Test
    public void verifyFileUploading() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFile = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
        chooseFile.sendKeys("C:\\selenium\\test.txt");
        WebElement checkBox = driver.findElement(By.xpath("//input[@id='terms']"));
        checkBox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        submitButton.click();
    }

    @Test
    public void verifyUsingJavaScript() {
        driver.get("https://demowebshop.tricentis.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('newsletter-email').value='test@test.com'");
        js.executeScript("document.getElementById('newsletter-subscribe-button').click()");

    }

    @Test
    public void verifyWightSelenium() {
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement emailId = driver.findElement(By.xpath("//input[@id='newsletter-email']"));
        emailId.sendKeys("aradhana@gmail.com");
        WebElement subscribe = driver.findElement(By.xpath("//input[@id='newsletter-subscribe-button']"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(subscribe));
        FluentWait fwait = new FluentWait<>(driver);
        fwait.withTimeout(Duration.ofSeconds(10));
        fwait.pollingEvery(Duration.ofSeconds(1));
        fwait.until(ExpectedConditions.visibilityOf(subscribe));
        subscribe.click();
    }
    @Test
    public void verifyScrollDown() {
        driver.get("https://demowebshop.tricentis.com/");//change url
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");


    }
    @Test
    public void verifyScrollingToViewOfWebElement() {
    driver.get("https://demo.guru99.com/test/guru99home/");
    WebElement linux = driver.findElement(By.linkText("Linux"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].scrollIntoView();",linux);

    }
    @Test
    public void verifyScrollToBottomOfPage() {
        driver.get("https://demo.guru99.com/test/guru99home/");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    @Test
    public void verifyHorizontalScroll() {
    driver.get("https://demo.guru99.com/test/guru99home/scrolling.h*tml");
    WebElement vbScript = driver.findElement(By.linkText("VBScript"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].scrollIntoView();",vbScript);
    }
    @Test
    public void verifyTable() throws IOException {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> rowElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
        List<WebElement> columnElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
        List<ArrayList<String>> actGridData=TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowElements,columnElements);
        List<ArrayList<String>> expGridData=ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx","Table");
        Assert.assertEquals(actGridData,expGridData,"Invalid data found in table");

    }
    @Test
    public void verifyRobot() throws InterruptedException, AWTException {
        driver.get("https://www.foundit.in/seeker/registration");
        StringSelection s = new StringSelection("C:\\selenium\\test.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
        WebElement chooseFile = driver.findElement(By.xpath("//span[text()='Choose CV']"));
        chooseFile.click();
        Robot r = new Robot();
        Thread.sleep(5000);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        Thread.sleep(5000);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        Thread.sleep(5000);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }
}

