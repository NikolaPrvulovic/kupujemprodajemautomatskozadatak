package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoriesPage extends BasePage{
    public CategoriesPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }

    public void verifyCategoryClickable(String name){
        WebElement category = driver.findElement(
                By.xpath("//div[contains(@class, 'CategoryBox_titleHolder__7l4AC')]/div/a[contains(text(), '"+ name +"')]"));

        actions.moveToElement(category).perform();

    }

    public WebElement getCategoryByName(String name){

        WebElement category = driver.findElement(
                By.linkText(name));

        return category;
    }
}
