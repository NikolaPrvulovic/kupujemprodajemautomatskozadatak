package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class SearchByCriteriaTests extends BaseTest{

    @Test(priority = 10)
    @Description("TC1: Search by criteria")
    public void searchByCriteria() {

        landingPage.verifyCookiesAcceptButtonIsClickable();
        landingPage.getCookiesAcceptButton().click();


        categoriesPage.verifyCategoryClickable("Odeća | Ženska"); // Name of category must be exact as it is on site
        categoriesPage.getCategoryByName("Odeća | Ženska").click(); // Choose a category
        categoriesPage.verifyCategoryClickable("Bluze"); // Choose a subcategory
        categoriesPage.getCategoryByName("Bluze").click();


        adsPage.verifyMoreFiltersButtonIsClickable();
        adsPage.getMoreFiltersButton().click();
        adsPage.verifyFilterDialogueIsVisible();

        adsPage.getPriceInputByPriceRange("Cena od").sendKeys("100"); // Cena od / Cena do
        adsPage.verifyPriceCheckboxClickable("Samo sa cenom");
        adsPage.getPriceCheckbox("Samo sa cenom").click(); // Samo sa cenom / Besplatno / Moguća zamena / Kontakt

        adsPage.verifyMoreFiltersFromDialogueClickable("Nudi se");
        adsPage.getMoreFiltersFromDialogue("Stanje").click(); // Stanje / Nudi se / Period
        adsPage.verifyConditionOptionIsVisible("Novo");
        adsPage.getConditionOption("Novo").click(); // Novo / Kao novo / Polovno / Osteceno
        adsPage.getConditionOption("Kao novo").click();

        adsPage.getAcceptResetFilterButtons("Primeni filtere").click(); // Primeni filtere / Resetuj filtere

        adsPage.verifyNumberOfResultsHigherThanGivenNumber(1000);

    }
}
