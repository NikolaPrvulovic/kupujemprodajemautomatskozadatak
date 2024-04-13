package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

    public LandingPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }




    public WebElement getCookiesAcceptButton(){
        return driver.findElement(By.xpath("//button[@aria-label='Prihvatam']"));
    }


    public void verifyCookiesAcceptButtonIsClickable(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Prihvatam']")));
    }




}
