import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTest {

    public WebDriver driver;

    public  RegistrationPage regPage;

    public String testURL = "http://localhost:3000/registration";

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.navigate().to(testURL);

        regPage = new RegistrationPage(driver);
    }

    @Test
    public void RegisterClassic() throws InterruptedException {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.id("inputUsername"));


        regPage.enterUsername("TestUser123");
        regPage.enterPassword("ababab");
        regPage.clickSubmit();
        waitForElementToBeVisible(By.className("login-form"));

        WebElement testLink =
                driver.findElement(By.className("login-form")
                );
        Assert.assertEquals(testLink.getText(), "Login");
        System.out.print(testLink.getText());
    }

    @Test
    public void RegisterNoPassword() {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.id("inputUsername"));
        regPage.enterUsername("TestUser12");
        regPage.enterPassword("");
        regPage.clickSubmit();

        Assert.assertNotEquals(regPage.getErrorMessage(), "");
    }


    @Test
    public void RegisterNoUsername() throws InterruptedException {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.id("inputUsername"));
        regPage.enterUsername("");
        regPage.enterPassword("123456");
        regPage.clickSubmit();

        Assert.assertNotEquals(regPage.getErrorMessage(), "");
    }



    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
