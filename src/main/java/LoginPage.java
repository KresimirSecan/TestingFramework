
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    WebDriver driver;

    By usernameField = By.xpath("//input[@placeholder='Username']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By submitButton = By.xpath("//button[text()='Login']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

}
