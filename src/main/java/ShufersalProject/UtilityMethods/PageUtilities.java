package ShufersalProject.UtilityMethods;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static ShufersalProject.Tests.TestClassShufersal.driver;

public class PageUtilities {
    protected static FluentWait wait = new FluentWait(driver).pollingEvery(Duration.ofMillis(500)).withTimeout(Duration.ofSeconds(12)).ignoring(NoSuchElementException.class);

    public static void clickElement(WebElement element){
        element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void focusOnSpecificElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    /*EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.executeScript("document.querySelector('#ui-id-5').scrollTop=150");*/

    //colorSelectionWindow.sendKeys(Keys.SPACE);

    /*EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.executeScript("document.querySelector('#ui-id-5').scrollTop=150");*/

    //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectColorInFilter);

    //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
}
