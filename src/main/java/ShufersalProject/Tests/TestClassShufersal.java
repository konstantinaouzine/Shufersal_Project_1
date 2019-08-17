package ShufersalProject.Tests;

import ShufersalProject.Pages.AmericanOutletsPage;
import ShufersalProject.Pages.ShufersalHomePage;
import ShufersalProject.Pages.ShufersalLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClassShufersal {
    public static WebDriver driver;

    private ShufersalLoginPage shufersalLoginPage;
    private ShufersalHomePage shufersalHomePage;
    private AmericanOutletsPage americanOutletsPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ayzin\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        System.out.println("Going to use POM with PageFactory");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test (priority = 1)
    public void shufersalLoginTest() {
        driver.get("https://www.shufersal.co.il/online/he/");
        shufersalLoginPage = new ShufersalLoginPage();
        shufersalLoginPage.performLogin("ayzink@gmail.com", "k6547899");
        boolean isLoggedIn = shufersalLoginPage.isDisconnectButtonDisplayed();
        Assert.assertTrue(isLoggedIn);

    }

    @Test (priority = 2, dependsOnMethods = {"shufersalLoginTest"})
    public void moveToAmericanOutlets(){
        shufersalHomePage = new ShufersalHomePage();
        shufersalHomePage.goToAmericanOutlets();
        boolean isAmericanOutletsOpened = shufersalHomePage.isAmericanOutletsOpened();
        Assert.assertTrue(isAmericanOutletsOpened);
    }

    @Test (priority = 3, dependsOnMethods = {"moveToAmericanOutlets"})
    public void purchaseAdidasShoesAndVerifyPrices(){
        americanOutletsPage = new AmericanOutletsPage();
        boolean result = americanOutletsPage.goToAdidasShoes();
        result &= americanOutletsPage.filterAdidasShoesByTypeAndColor();
        result &= americanOutletsPage.purchaseShoesAndComparePrices();
        Assert.assertEquals(result, true);
    }
}