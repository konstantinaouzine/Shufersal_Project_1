package ShufersalProject.Pages;

import org.openqa.selenium.support.PageFactory;

import static ShufersalProject.Tests.TestClassShufersal.driver;

import java.util.concurrent.TimeUnit;

public class PageClass {

    //Constructor
    PageClass(){
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        PageFactory.initElements(driver, this);
    }
}
