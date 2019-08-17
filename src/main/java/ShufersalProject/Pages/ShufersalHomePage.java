package ShufersalProject.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import static ShufersalProject.Tests.TestClassShufersal.driver;

public class ShufersalHomePage extends PageClass{

    @FindBy(css = "#categoryMenu > li:nth-child(5) > a > span")
    private WebElement americanOutletsButton;

    public void goToAmericanOutlets(){
        String windowHandle = driver.getWindowHandle();
        americanOutletsButton.click();
        for (String _windowHandle : driver.getWindowHandles())
        {
            if (!windowHandle.equalsIgnoreCase(_windowHandle))
                driver.switchTo().window(_windowHandle);
        }
    }

    public boolean isAmericanOutletsOpened(){
        return (driver.getTitle().contains("American Outlets"));
    }

}