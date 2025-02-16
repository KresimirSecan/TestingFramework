import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    By usernameField = By.id("inputUsername");
    By passwordField = By.id("inputPassword");
    By submitButton = By.className("formButton");
    By errorMessage = By.className("formError");


    public RegistrationPage(WebDriver driver) {
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

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
