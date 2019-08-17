package ShufersalProject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import static ShufersalProject.TestClassShufersal.driver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PageClass {

    //Constructor
    PageClass(){
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        PageFactory.initElements(driver, this);
    }
}
