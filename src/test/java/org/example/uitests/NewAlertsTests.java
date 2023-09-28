package org.example.uitests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewAlertsTests {
    private WebDriver driver;

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
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void alertTest() {
        clickAlertButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You successfully clicked an alert");

    }

    @Test
    public void confirmTestOk() {
        clickConfirmButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Ok");
    }

    @Test
    public void confirmTestCancel() {
        clickConfirmButton();
        workWithAlert(false);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Cancel");
    }


    @Test
    public void promptTestOk() {
        String textToEnter = "TestString!";
        clickPromptButton();
        workWithAlert(true, textToEnter);
        String result = getResult();
        Assert.assertEquals(result, "You entered: " + textToEnter);
    }

    @Test
    public void promptTestCancel() {
        String textToEnter = "TestString!";
        clickPromptButton();
        workWithAlert(false, textToEnter);
        String result = getResult();
        Assert.assertEquals(result, "You entered: null");
    }

    private void clickOnButton(Buttons button) {
        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'" + button.getText() + "')]"));
        alertButton.click();
    }

    public String getResult() {
        return driver.findElement(By.id("result")).getText();
    }

    public void clickAlertButton() {
        clickOnButton(Buttons.ALERT);
    }

    public void clickConfirmButton() {
        clickOnButton(Buttons.CONFIRM);
    }

    public void clickPromptButton() {
        clickOnButton(Buttons.PROMPT);
    }

    public String workWithAlert(boolean accept, String... textToEnter) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        if (textToEnter.length > 0) {
            alert.sendKeys(textToEnter[0]);
        }
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return text;
    }

    enum Buttons {
        ALERT("Click for JS Alert"),
        CONFIRM("Click for JS Confirm"),
        PROMPT("Click for JS Prompt");

        private String text;

        Buttons(String s) {
            this.text = s;
        }

        public String getText() {
            return text;
        }
    }

}
