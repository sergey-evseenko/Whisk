package test;

import org.testng.annotations.Test;

public class WhiskUITests extends BaseTest {

    @Test(description = "Add five items to the Shopping list", priority = 1)
    public void addItems(){
        loginPage
                .openPage()
                .provideEmailAndContinue(email)
                .providePasswordAndLogin(password)
                .onboardingAndVerifySuccess()
                .openPage()
                .createShoppingList() //TODO transfer shopping list name
                .addItemAndCheck("Milk")
                .addItemAndCheck("Bread")
                .addItemAndCheck("Onions")
                .addItemAndCheck("Butter")
                .addItemAndCheck("Cheese");
    }

    @Test(description = "Delete shopping list", priority = 2)
    public void deleteShoppingList(){
        loginPage
                .openPage()
                .provideEmailAndContinue(email)
                .providePasswordAndLogin(password)
                .onboardingAndVerifySuccess()
                .openPage()
                .deleteShoppingList() //TODO transfer shopping list name
                .verifyNumberOfShoppingLists(0);
    }

}
