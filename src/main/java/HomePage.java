
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    WebDriver driver;

    By searchInput = By.className("search-input");
    By searchButton = By.className("search-button");
    By medicineButton = By.xpath("//button[text()='Medicine']");
    By sympButton = By.xpath("//button[text()='Symptomps']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearch(String search) {
        driver.findElement(searchInput).sendKeys(search);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public void clickMedicine() {
        driver.findElement(medicineButton).click();
    }

    public void clickSymp() {
        driver.findElement(sympButton).click();
    }


}
