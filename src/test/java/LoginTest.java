import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class LoginTest {

    public WebDriver driver;
    public LoginPage logPage;
    public String testURL = "http://localhost:3000/login";

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.navigate().to(testURL);

        logPage = new LoginPage(driver);
    }

    @Test
    public void ValidUser() throws InterruptedException {
        waitForElementToBeVisible(By.className("login-form"));
        logPage.enterUsername("TestUser123");
        logPage.enterPassword("ababab");
        logPage.clickSubmit();
        waitForElementToBeVisible(By.xpath("//button[text()='Logout']"));
        WebElement testLink =
                driver.findElement(By.xpath("//button[text()='Logout']")
                );
        Assert.assertEquals(testLink.getText(), "Logout");
        System.out.print(testLink.getText());

    }

    @Test
    public void NotValidUser() throws InterruptedException {
        driver.manage().window().maximize();
        logPage.enterUsername("Testiranje2025");
        logPage.enterPassword("pozdrav");
        logPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(driver,2);
        Alert alert = wait.until(alertIsPresent());

        Assert.assertEquals(alert.getText(), "User doesn't exist");
        System.out.print(alert.getText());
    }

    @Test
    public void ValidUserWrongPassword() throws InterruptedException {

        driver.manage().window().maximize();
        logPage.enterUsername("TestUser123");
        logPage.enterPassword("pozdrav");
        logPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(driver,2);
        Alert alert = wait.until(alertIsPresent());

        Assert.assertEquals(alert.getText(), "Wrong login");
        System.out.print(alert.getText());;
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}


