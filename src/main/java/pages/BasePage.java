package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected Actions actions;

    public BasePage (WebDriver driver, WebDriverWait wait, Actions actions){
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }
}
