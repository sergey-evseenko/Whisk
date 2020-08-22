package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ShoppingPage;
import utils.CapabilitiesGenerator;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    PropertyManager props;
    LoginPage loginPage;
    ShoppingPage shoppingPage;
    String email;
    String password;

    private WebDriver driver;

    @BeforeMethod(description = "Opening chrome Driver.")
    public void setDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
        props = new PropertyManager();
        email = props.get("email");
        password = props.get("password");
    }

    @AfterMethod(description = "Closing chrome Driver.", alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

}
