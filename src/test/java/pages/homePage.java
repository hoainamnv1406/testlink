package pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import locators.homePage_locator;
import org.openqa.selenium.By;

@DefaultUrl("https://www.hollywoodbowl.co.uk/")
public class homePage extends PageObject {

    private final homePage_locator homePageLocator = new homePage_locator();

    public void openLoginPage(){
        open();
    }

    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    public void clickBookNowButton(){
        getDriver().findElement(homePageLocator.btn_bookNow).click();
    }

    public void checkThatHomePageIsDisplayed(){
        boolean t = getDriver().findElement(homePageLocator.btn_centres).isDisplayed();
        if (t == true) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
    }

    public void chooseACentre(){
        getDriver().findElement(homePageLocator.dropdown_selectCentre).click();
        getDriver().findElement(homePageLocator.txt_Ashford).click();
    }

    public void chooseAgreeButton(){
        getDriver().findElement(homePageLocator.btn_agree).click();
    }


}
