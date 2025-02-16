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

public class EntryTest {

    public WebDriver driver;
    public LoginPage logPage;
    public EntryPage entPage;

    public String testURL = "http://localhost:3000/Drugs";

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.navigate().to("http://localhost:3000/login");

        logPage = new LoginPage(driver);
        entPage = new EntryPage(driver);

    }

    @Test
    public void LogedinValidEntry() throws InterruptedException {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.xpath("//input[@placeholder='Username']"));
        logPage.enterUsername("TestUser123");
        logPage.enterPassword("ababab");
        logPage.clickSubmit();
        waitForElementToBeVisible(By.xpath("//button[text()='Logout']"));

        driver.navigate().to(testURL);
        driver.manage().window().maximize();
        entPage.enterMedicine("Lupocet 1000mg");
        entPage.enterContaining("paracetamol");
        entPage.clickSubmit();

        waitForElementToBeVisible(By.className("search-input"));
        WebElement testLink =
                driver.findElement(By.className("search-input")
                );
        Assert.assertEquals(testLink.getText(), "");
        System.out.print(testLink.getText());
    }


    @Test
    public void NotLogedinEntry() throws InterruptedException {
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
        entPage.enterMedicine("Lupocet 1000mg");
        entPage.enterContaining("paracetamol");
        entPage.clickSubmit();

        WebDriverWait wait = new WebDriverWait(driver,2);
        Alert alert = wait.until(alertIsPresent());
        Assert.assertEquals(alert.getText(), "You must be logged in to perform this action.");
        System.out.print(alert.getText());
    }


    @Test
    public void LogedinNameNotValid() throws InterruptedException {

        driver.manage().window().maximize();
        waitForElementToBeVisible(By.xpath("//input[@placeholder='Username']"));
        logPage.enterUsername("TestUser123");
        logPage.enterPassword("ababab");
        logPage.clickSubmit();
        waitForElementToBeVisible(By.xpath("//button[text()='Logout']"));

        driver.navigate().to(testURL);
        driver.manage().window().maximize();
        entPage.enterMedicine("");
        entPage.enterContaining("paracetamol");
        entPage.clickSubmit();

        waitForElementToBeVisible(By.xpath("//span[text()='Name is required']"));

        WebElement testLink =
                driver.findElement(By.xpath("//span[text()='Name is required']")
                );
        Assert.assertNotEquals(testLink.getText(), "");
        System.out.print(testLink.getText());

    }

    @Test
    public void LogedinContainingNotValid() throws InterruptedException {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.xpath("//input[@placeholder='Username']"));
        logPage.enterUsername("TestUser123");
        logPage.enterPassword("ababab");
        logPage.clickSubmit();
        waitForElementToBeVisible(By.xpath("//button[text()='Logout']"));

        driver.navigate().to(testURL);
        driver.manage().window().maximize();
        entPage.enterMedicine("Lupocet 1000mg");
        entPage.enterContaining("");
        entPage.clickSubmit();

        waitForElementToBeVisible(By.xpath("//span[text()='Containing is required']"));
        WebElement testLink =
                driver.findElement(By.xpath("//span[text()='Containing is required']")
                );
        Assert.assertNotEquals(testLink.getText(), "");
        System.out.print(testLink.getText());
    }


    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}