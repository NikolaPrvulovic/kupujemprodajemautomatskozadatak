package tests;

import helper.Helper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.AdsPage;
import pages.CategoriesPage;
import pages.LandingPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected  String baseUrl = "https://www.kupujemprodajem.com/";
    protected LandingPage landingPage;
    protected CategoriesPage categoriesPage;
    protected AdsPage adsPage;

    protected Helper helper;

    protected Actions actions;



    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();


        landingPage = new LandingPage(driver, wait, actions);
        categoriesPage = new CategoriesPage(driver, wait, actions);
        adsPage = new AdsPage(driver, wait, actions);
        helper = new Helper();

    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get(baseUrl);
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
//        if (result.getStatus() == ITestResult.FAILURE) {
//            helper.captureScreenshot(driver);
//        }
        helper.captureScreenshot(driver);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
