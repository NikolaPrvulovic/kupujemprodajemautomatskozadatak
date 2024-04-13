package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class AdresarTests extends BaseTest{

    @Test(priority = 10)
    @Description("Login form is shown after adding to 'Adresar' from an open advertisement")
    public void loginFormIsShown(){

        landingPage.verifyCookiesAcceptButtonIsClickable();
        landingPage.getCookiesAcceptButton().click();


        categoriesPage.verifyCategoryClickable("Automobili"); // Name of category must be exact as it is on site
        categoriesPage.getCategoryByName("Automobili").click(); // Choose a category
        categoriesPage.verifyCategoryClickable("BMW"); // Choose a subcategory
        categoriesPage.getCategoryByName("BMW").click();


        adsPage.getARandomAdFromAList().click();

        adsPage.verifyAdresarButtonClickable(); // If button does not exist, as user can disable adding to "Adresar"
        adsPage.getAdresarButton().click();     //  from another user, it will choose another random advertisement

        adsPage.verifyLoginFormShown();




    }
}
