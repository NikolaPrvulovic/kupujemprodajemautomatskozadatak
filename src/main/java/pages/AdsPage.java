package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class AdsPage extends BasePage{
    public AdsPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }

    public WebElement getMoreFiltersButton(){

        return driver.findElement(
                By.xpath("//ul[@class='SearchTagContainer_tagList__EwoaI']//button[@aria-label='Još filtera']"));
    }

    public void verifyMoreFiltersButtonIsClickable(){
        wait.until(ExpectedConditions.elementToBeClickable(getMoreFiltersButton()));
    }

    public WebElement getMoreFiltersFromDialogue(String name){

        int index = 0;

        switch (name.toLowerCase()){
            case "stanje" -> index = 1;
            case "nudi se" -> index = 2;
            case "trazi se" -> index = 2;
            case "period" -> index = 3;
            default -> {
            }
        }

        return driver.findElement(
                By.cssSelector("#__next .Sticky_sticky__iUnNu " +
                        ".Header_headerColMiddle__me1q5 form section " +
                        ".Search_animated__hnvyR " +
                        ".Grid_row__pl8x2 > div:nth-child(3) > span > div:nth-child("+ index +")"));

    }

    public void verifyMoreFiltersFromDialogueClickable(String name){
        wait.until(ExpectedConditions.elementToBeClickable(getMoreFiltersFromDialogue(name)));
    }

    public WebElement getConditionOption(String option){

        int index = 1;

        switch (option.toLowerCase()){
            case "novo" -> index = 2;
            case "kao novo" -> index = 3;
            case "polovno" -> index = 4;
            case "osteceno" -> index = 5;
            default -> {
            }
        }

        return driver.findElement(
                By.xpath("(//div[@class=' css-1of53lt-menu']//span[@class='Checkbox_holder__YEOBX'])["+ index +"]"));
    }

    public void verifyConditionOptionIsVisible(String option){
        wait.until(ExpectedConditions.visibilityOf(getConditionOption(option)));
    }


    public WebElement getPriceCheckbox(String name){

        WebElement checkboxName = driver.findElement(
                By.xpath("//div[@class='Search_detailedFilter__8diYh']" +
                        "//label[@class='Checkbox_container__7TDME']/span[contains(text(),'"+ name +"')]"));

        return checkboxName;
    }

    public void verifyPriceCheckboxClickable(String name){
        wait.until(ExpectedConditions.elementToBeClickable(getPriceCheckbox(name)));
    }

    public WebElement getPriceInputByPriceRange(String range){
        return driver.findElement(
                By.xpath("//div[@class='Search_detailedFilter__8diYh']" +
                        "//div[@class='Input_inputRow__YMC5T']/input[@placeholder='" + range +"']"));
    }

    public WebElement getAcceptResetFilterButtons(String name){
        return driver.findElement(
                By.xpath("//div[@class='SearchDetailed_filterButtons__cghDE']/button[@aria-label='"+ name +"']"));
    }

    public void verifyNumberOfResultsHigherThanGivenNumber(int number){

        verifyPriceTypeContains("Samo sa cenom");

        String[] splitted = driver.findElement(
                By.xpath("//div[@class='BreadcrumbHolder_breadcrumbHolder__riFtq']//span//span"))
                .getText().split(" ");


        int numberOfResults = Integer.parseInt(splitted[0].replace(".", ""));
        Assert.isTrue(numberOfResults > number,
                "Number of results is lower than expected: " + numberOfResults);


    }


    public void verifyFilterDialogueIsVisible(){
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='Search_detailedFilter__8diYh']")));
    }


    public WebElement getARandomAdFromAList(){
        List<WebElement> listOfAds = driver.findElements(
                By.xpath("(//div[@class='ThreeColumnLayout_content__eTm7W']" +
                        "/div/div/div[2]/div[2]/div)[position() < last()]/section/article/a"));

        int random = new Random().nextInt(listOfAds.size() - 1);

        actions.moveToElement(listOfAds.get(random)).perform();

        return listOfAds.get(random);

    }

    public WebElement getAdresarButton(){
        return driver.findElement(By.xpath("//span[contains(text(), 'Dodaj u adresar')]"));
    }


    public void verifyAdresarButtonClickable(){

        while(true){
            try{
                actions.moveToElement(getAdresarButton()).perform();
                wait.until(ExpectedConditions.elementToBeClickable(getAdresarButton()));
                if(getAdresarButton().isDisplayed()){
                 break;
                }
            } catch (NoSuchElementException e){
                System.out.println("This user disabled adding to 'Adresar'. Choosing next random advertisement. . .");
                driver.navigate().back();
                actions.moveToElement(getARandomAdFromAList()).perform();
                wait.until(ExpectedConditions.elementToBeClickable(getARandomAdFromAList()));
                getARandomAdFromAList().click();
            }
        }

    }



    public void verifyLoginFormShown(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//aside[@role='dialog']")));
    }

    private void verifyPriceTypeContains(String name){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//span[contains(text(), '"+ name +"')]"), name));
    }
}
