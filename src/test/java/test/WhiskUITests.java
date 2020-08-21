package test;

import models.User;
import org.testng.annotations.Test;

public class WhiskUITests extends BaseTest {
    User user = new User("sergey.evseenko.qa@gmail.com", "Alfie_2244");

    @Test(description = "Add five items to the Shopping list.")
    public void addItems(){
        loginPage
                .openPage()
                .provideEmailAndContinue(user)
                .providePasswordAndLogin(user)
                .onboardingAndVerifySuccess()
                .openPage()
                .createShoppingList()
                .addItemAndCheck("Milk")
                .addItemAndCheck("Bread")
                .addItemAndCheck("Onions")
                .addItemAndCheck("Butter")
                .addItemAndCheck("Cheese");
    }

    @Test(description = "Delete shopping list.")
    public void deleteShoppingList(){
        loginPage
                .openPage()
                .provideEmailAndContinue(user)
                .providePasswordAndLogin(user)
                .onboardingAndVerifySuccess()
                .openPage()
                .deleteShoppingList()
                .verifyNumberOfShoppingLists(0);
    }

}
