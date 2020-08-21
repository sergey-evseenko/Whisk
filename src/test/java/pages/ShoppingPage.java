package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ShoppingPage extends BasePage{
    @FindBy(css = "[data-testid='create-new-shopping-list-button']")
    WebElement createListButton;
    @FindBy(css = "[data-testid='create-new-shopping-list-create-button']")
    WebElement createListCreateButton;
    @FindBy(css = "[data-testid='desktop-add-item-autocomplete']")
    WebElement inputAddItem;
    @FindBy(css = "[data-testid='shopping-lists-list-name']")
    WebElement shoppingList;
    @FindBy(xpath = "//div[@data-testid='shopping-lists-list-name']//..//..//button")
    WebElement menuButton;
    @FindBy(css = "[data-testid='shopping-list-delete-menu-button']")
    WebElement deleteListMenuButton;
    @FindBy(css = "[data-testid='confirm-delete-button']")
    WebElement confirmDeleteButton;
    @FindBy(xpath = "//span[contains(text(), 'List was deleted')]")
    WebElement successMessage;


    public ShoppingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(createListButton));
        return null;
    }

    @Override
    public ShoppingPage openPage() {
        driver.get("https://my.whisk-dev.com/shopping-list");
        isPageOpened();
        return this;
    }

    public ShoppingPage createShoppingList(){
        createListButton.click();
        wait.until(ExpectedConditions.visibilityOf(createListCreateButton));
        createListCreateButton.click();
        return this;
    }

    public ShoppingPage addItemAndCheck(String item){
        String locator1 = "//span[contains(text(),'%s')]//..//..//div";
        String locator2 = "//span[text()='%s']";
        wait.until(ExpectedConditions.visibilityOf(inputAddItem));
        inputAddItem.click();
        driver.findElement(By.xpath(String.format(locator1, item))).click();
        shoppingList.click();
        List<WebElement> addedItems = driver.findElements(By.xpath(String.format(locator2, item)));
        assertEquals(addedItems.size(),1, "Product wasn't added!");
        return this;
    }

    public ShoppingPage deleteShoppingList(){
        wait.until(ExpectedConditions.visibilityOf(menuButton));
        menuButton.click();
        deleteListMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        confirmDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return this;
    }

    public ShoppingPage verifyNumberOfShoppingLists(int number){
        List<WebElement> shoppingLists = driver.findElements(By.cssSelector("div[data-testid='shopping-lists-list-name']"));
        assertEquals(shoppingLists.size(),number+1, "ShoppingList wasn't deleted!");
        return this;
    }


}
