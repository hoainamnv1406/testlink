package pages;

import elements.ElementBase;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import locators.homePage_locator;
import org.openqa.selenium.By;

public class homePage extends ElementBase {

    private final homePage_locator homePageLocator = new homePage_locator();

    private final ElementBase elementBase = new ElementBase();
    public void openLoginPage(){
        open();
    }

    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    public void clickBookNowButton(){
        clickElement(homePageLocator.btn_bookNow);
    }

    public void checkThatHomePageIsDisplayed(){
        checkElementIsDisplayed(homePageLocator.btn_centres);
    }

    public void chooseACentre(){
        clickElement(homePageLocator.dropdown_selectCentre);
        clickElement(homePageLocator.txt_Ashford);
    }

    public void chooseAgreeButton(){
//        getDriver().findElement(homePageLocator.btn_agree).click();
        clickElement(homePageLocator.btn_agree);
    }


}
