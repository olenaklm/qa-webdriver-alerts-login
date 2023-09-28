package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {
    private WebDriver driver;
    private static final String USER_NAME = "tomsmith";
    private static final String USER_PASS = "SuperSecretPassword!";


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

//    @AfterMethod
//    public void afterMethod() {
//        driver.findElement(By.xpath("//*[@id='content']/div/a/i")).click();
//    }

    @Test
    public void loginTestWithXpath() {
        WebElement userNameField = driver.findElement(By.xpath("//*[@id='username']"));

        WebElement userPassField = driver.findElement(By.xpath("//*[@id='password']"));

        userNameField.clear();
        userNameField.sendKeys(USER_NAME);

        userPassField.clear();
        userPassField.sendKeys(USER_PASS);

        WebElement buttonLogin = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        buttonLogin.click();

        WebElement textLogin = driver.findElement(By.xpath("//*[@id='content']/div/h4"));

        Assert.assertEquals(textLogin.getText(), "Welcome to the Secure Area. When you are done click logout below.");
    }

    @Test
    public void loginTestWithXpatCSS() {
        WebElement userNameField = driver.findElement(By.cssSelector("#username"));

        WebElement userPassField = driver.findElement(By.cssSelector("#password"));

        userNameField.clear();
        userNameField.sendKeys(USER_NAME);

        userPassField.clear();
        userPassField.sendKeys(USER_PASS);

//        WebElement buttonLogin = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        WebElement buttonLogin = driver.findElement(By.cssSelector("i[class^=fa]"));

        buttonLogin.click();

//        WebElement textLogin = driver.findElement(By.xpath("//*[@id='content']/div/h4"));
        WebElement textLogin = driver.findElement(By.cssSelector("#content>div>h4"));

        Assert.assertEquals(textLogin.getText(), "Welcome to the Secure Area. When you are done click logout below.");
    }
}
