package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ShoppingPage;
import units.CapabilitiesGenerator;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest{
    LoginPage loginPage;
    ShoppingPage shoppingPage;

    private WebDriver driver;

    @BeforeMethod(description = "Opening chrome Driver.")
    public void setDriver(){
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);

    }

    @AfterMethod(description = "Closing chrome Driver.", alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

}
