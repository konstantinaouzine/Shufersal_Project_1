package ShufersalProject.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ShufersalLoginPage extends PageClass {

    @FindBy (css = "#loginDropdownContainer > button")
    private WebElement startLoginButton;

    @FindBy (id = "j_username")
    private WebElement userNameField;

    @FindBy (id = "j_password")
    private WebElement passwordField;

    @FindBy (css = "#loginForm > div.bottomSide > button")
    private WebElement loginButton;

    @FindBy (linkText = "התנתק")
    private WebElement disconnectButton;

    public void performLogin(String username, String password){
        try{
            startLoginButton.click();
            userNameField.clear();
            userNameField.sendKeys(username);

            passwordField.clear();
            passwordField.sendKeys(password);

            loginButton.click();

        }catch(NoSuchElementException e){
            Assert.fail("We failed to login");
        }
    }

    public boolean isDisconnectButtonDisplayed(){
        return disconnectButton.isDisplayed();
    }
}