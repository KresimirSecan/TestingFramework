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

public class SearchBarTest {

    public WebDriver driver;
    public HomePage homePage;
    public String testURL = "http://localhost:3000/";

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.navigate().to(testURL);

        homePage=new HomePage(driver);
    }



    @Test
    public void SearchString() throws InterruptedException {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.className("search-input"));

        homePage.enterSearch("Brufen");
        homePage.clickSearch();

        waitForElementToBeVisible(By.className("title"));
        WebElement testLink =
                driver.findElement(By.className("title")
                );
        Assert.assertEquals(testLink.getText(), "Brufen");
        System.out.print(testLink.getText());

    }

    @Test
    public void SearchEmpty() throws InterruptedException {
        driver.manage().window().maximize();
        waitForElementToBeVisible(By.className("search-input"));
        homePage.clickSearch();

        waitForElementToBeVisible(By.xpath("//p[contains(text(),'No medicines found')]"));
        WebElement testLink =
                driver.findElement(By.xpath("//p[contains(text(),'No medicines found')]")
                );
        Assert.assertEquals(testLink.getText(), "No medicines found.");
        System.out.print(testLink.getText());
    }

    @Test
    public void SearchNumber() throws InterruptedException {

        driver.manage().window().maximize();
        waitForElementToBeVisible(By.className("search-input"));

        homePage.enterSearch("3");
        homePage.clickSearch();

        waitForElementToBeVisible(By.className("title"));
        WebElement testLink =
                driver.findElement(By.className("title")
                );
        Assert.assertTrue(testLink.getText().contains("3"));
        System.out.print(testLink.getText());
    }



    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}