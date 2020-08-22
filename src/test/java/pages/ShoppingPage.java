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
    WebElement shoppingListName;
    @FindBy(xpath = "//div[@data-testid='shopping-lists-list-name']//..//..//button")
    WebElement menuButton;
    @FindBy(css = "[data-testid='shopping-list-delete-menu-button']")
    WebElement deleteListMenuButton;
    @FindBy(css = "[data-testid='confirm-delete-button']")
    WebElement confirmDeleteButton;
    @FindBy(xpath = "//span[contains(text(), 'List was deleted')]")
    WebElement successMessage;
    String locatorForSelectionItem = "//span[contains(text(),'%s')]//..//..//div";
    String locatorForItemsCollection = "//span[text()='%s']";
    String locatorForShoppingList = "div[data-testid='shopping-lists-list-name']";

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

    public ShoppingPage addItemAndCheck(String item) {
        wait.until(ExpectedConditions.visibilityOf(inputAddItem));
        inputAddItem.click();
        driver.findElement(By.xpath(String.format(locatorForSelectionItem, item))).click();
        shoppingListName.click();
        List<WebElement> addedItems = driver.findElements(By.xpath(String.format(locatorForItemsCollection, item)));
        assertEquals(addedItems.size(), 1, "Product wasn't added!");
        return this;
    }

    public ShoppingPage deleteShoppingList(){
        wait.until(ExpectedConditions.visibilityOf(menuButton));
        menuButton.click();
        deleteListMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        confirmDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        driver.navigate().refresh();
        return this;
    }

    public ShoppingPage verifyNumberOfShoppingLists(int number) {
        List<WebElement> shoppingLists = driver.findElements(By.cssSelector(locatorForShoppingList));
        assertEquals(shoppingLists.size(), number + 1, "Shopping list wasn't deleted!");
        return this;
    }


}
