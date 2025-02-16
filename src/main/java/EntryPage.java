import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class EntryPage {
    WebDriver driver;

    By medicineName = By.id("inputName");
    By medicineContaining = By.id("inputContaining");
    By submitButton = By.xpath("//button[text()='Insert']");


    public EntryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMedicine(String medicine) {
        driver.findElement(medicineName).sendKeys(medicine);
    }

    public void enterContaining(String containing) {
        driver.findElement(medicineContaining).sendKeys(containing);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

}