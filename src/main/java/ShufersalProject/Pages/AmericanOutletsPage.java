package ShufersalProject.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static ShufersalProject.UtilityMethods.PageUtilities.*;
import static ShufersalProject.TestClassShufersal.driver;

import java.util.List;

public class AmericanOutletsPage extends PageClass {

    @FindBy (css = "#ui-id-4 > span")
    private WebElement shoes;

    @FindBy (css = "#bio_ep_close")
    private WebElement closeButtonOnAdvertisement;

    @FindBy (linkText = "Adidas")
    private WebElement adidasShoes;

    @FindBy (className = "filter-options-title")
    private List<WebElement> openTypeFilterGroup;

    /*@FindBy (css = "#ui-id-3 > div > div > ol > li:nth-child(4) > a > label")
    private WebElement selectBasketBallShoesInFilter;*/

    @FindBy (xpath = "//span[contains(text(), 'נעלי כדורסל')]")
    private WebElement selectBasketBallShoesInFilter;

    @FindBy (css = "#ui-id-3 > div > a")
    private WebElement selectBasketBallShoesSubmitButton;

    @FindBy (className = "filter-options-title")
    private List<WebElement> openColorFilterGroup;

    @FindBy (xpath = "//span[contains(text(), 'White/Black/Gold')]")
    private WebElement selectColorInFilter;

    @FindBy (css = "#ui-id-5 > div > a")
    private WebElement submitButtonInColorSelection;

    @FindBy (xpath = "//span[contains(text(), 'adidas Originals Mens Crazy 1 Adv Pk Leather Ortholite Basketball Shoes')]")
    private WebElement adidasSpecificShoes;

    @FindBy (xpath = "//span[contains(text(), 'adidas Originals Mens Crazy 1 Adv Pk Leather Ortholite Basketball Shoes')]/../../../div[@class='price-box price-final_price']/span/span/span[2]")
    private WebElement lookForPriceInGallery;

    @FindBy (xpath = "//span[contains(text(), 'adidas Originals Mens Crazy 1 Adv Pk Leather Ortholite Basketball ')]/../../../div[@class='product-info-price']/div/span/span/span[2]")
    private WebElement beforeCartElement;

    @FindBy (xpath = "//span[contains(text(), 'adidas Originals Mens Crazy 1 Adv Pk Leather Ortholite Basketball ')]/../../../div[@class='product-add-form']/form/div/div/div/div/select")
    private WebElement sizeSelectionSelectInBeforeCartScreen;

    @FindBy (xpath = "//span[contains(text(), 'adidas Originals Mens Crazy 1 Adv Pk Leather Ortholite Basketball ')]/../../../div[@class='product-add-form']/form/div[@class='product-options-bottom']/div/div[2]/button")
    private WebElement addToCartButtonInBeforeCartScreen;

    //Stupid selector because everytime product will be added or deleted the child(ID) will change and another product will be added to cart :)
    @FindBy (xpath = "//span[contains(text(), 'adidas Originals Mens Crazy 1 Adv Pk Leather Ortholite Basketball Shoes')]/../../../div[@class='hidden']/div[2]/div/form/button")
    private WebElement addToCartButton;

    @FindBy (css = "#minicart-content-wrapper > div.block-content > div.actions-bottom > div > a")
    private WebElement cartElement;

    @FindBy (css = "#totals-price")
    private WebElement totalCartPrice;

    public boolean goToAdidasShoes(){
        clickElement(closeButtonOnAdvertisement);
        clickElement(shoes);
        clickElement(adidasShoes);

        if(driver.getTitle().contains("adidas"))
            return true;
        else {
            System.out.println("Something went wrong - we failed to enter Adidas shoes category");
            return false;
        }

    }

    public boolean filterAdidasShoesByTypeAndColor(){
        try{
            selectShoesType();
            selectShoesColorAndSubmit();
        }catch(NoSuchElementException e){
            return false;
        }
        return true;
    }

    private void selectShoesType() throws NoSuchElementException{
        for (WebElement element : openTypeFilterGroup){
            if(element.getText().equalsIgnoreCase("סגנון")){
                clickElement(element);
            }
        }
        clickElement(selectBasketBallShoesInFilter);
        clickElement(selectBasketBallShoesSubmitButton);
    }

    private void selectShoesColorAndSubmit() throws NoSuchElementException{
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for (WebElement element : openColorFilterGroup){
            if (element.getText().equalsIgnoreCase("צבע")){
                clickElement(element);
                break;
            }
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Actions action = new Actions(driver);
        action.moveToElement(selectColorInFilter);
        action.perform();

        selectColorInFilter.click();
        System.out.println("Color selected");
        clickElement(submitButtonInColorSelection);
        System.out.println("Submit button pressed");

        boolean areShoesShown = adidasSpecificShoes.isDisplayed();
        if (!areShoesShown) {
            throw new NoSuchElementException("Shoes not found");
        }

    }

    public boolean purchaseShoesAndComparePrices(){
        try {
            Actions action = new Actions(driver);
            action.moveToElement(adidasSpecificShoes).perform();
            String priceInGalleryAsString = lookForPriceInGallery.getAttribute("data-price-amount");
            double priceInGallery = Double.valueOf(priceInGalleryAsString);
            System.out.println("Price of shoes in gallery: " + priceInGallery);

            clickElement(addToCartButton);

            String priceInBeforeCartScreenAsString = beforeCartElement.getAttribute("data-price-amount");
            double priceInBeforeCartScreen = Double.valueOf(priceInBeforeCartScreenAsString);
            System.out.println("Price of shoes in beforeGallery: " + priceInBeforeCartScreen);
            if (priceInGallery!=priceInBeforeCartScreen){
                System.out.println(priceInGallery-priceInBeforeCartScreen);
                return false;
            }

            Select sizeSelect = new Select(sizeSelectionSelectInBeforeCartScreen);
            sizeSelect.selectByVisibleText("10.5 Medium (D)");

            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
            clickElement(addToCartButtonInBeforeCartScreen);
            clickElement(cartElement);
            String[] priceArr = totalCartPrice.getText().split(" ");
            double cartTotal = Double.valueOf(priceArr[0]);
            System.out.println("Price of shoes in cart: " + cartTotal);
            if(priceInGallery!=cartTotal)
                return false;
        }catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}