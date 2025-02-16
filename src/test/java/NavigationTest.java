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

public class NavigationTest {

    public WebDriver driver;
    public LoginPage logPage;
    public HomePage homePage;
    public String testURL = "http://localhost:3000/";

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void pagingCheck() {
        WebElement rightbutton = driver.findElement(By.xpath("//button[i[@class='fa-solid fa-chevron-right']]"));

        for (int i = 0; i < 9; i++) {
            rightbutton.click();
        }

        WebElement leftbutton  = driver.findElement(By.xpath("//button[i[@class='fa-solid fa-chevron-left']]"));

        for(int i = 0; i < 4; i++) {
            leftbutton.click();
        }
    }

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.navigate().to(testURL);

        logPage=new LoginPage(driver);
        homePage=new HomePage(driver);
    }

    @Test
    public void Navigation() throws InterruptedException {
        driver.manage().window().maximize();


        WebElement homebutton = driver.findElement(By.xpath("//a[text()='Home']"));
        WebElement newbutton = driver.findElement(By.xpath("//a[text()='New drug']"));
        WebElement registerbutton = driver.findElement(By.xpath("//a[text()='Register']"));
        WebElement loginbutton = driver.findElement(By.xpath("//a[text()='Login']"));

        homePage.clickSymp();
        pagingCheck();
        homebutton.click();

        homePage.clickMedicine();
        pagingCheck();
        homebutton.click();


        homePage.clickSearch();
        newbutton.click();
        registerbutton.click();
        loginbutton.click();

        logPage.enterUsername("TestUser123");
        logPage.enterPassword("ababab");
        logPage.clickSubmit();
        waitForElementToBeVisible(By.xpath("//a[contains(@href, '/profile/')]"));

        WebElement userProfile = driver.findElement(By.xpath("//a[contains(@href, '/profile/')]"));
        userProfile.click();
        waitForElementToBeVisible(By.xpath("//h1[contains(@class, 'profileUsername')]"));
        homebutton.click();
    }




    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}