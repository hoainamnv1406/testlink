package step;

import elements.ElementBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.pages.PageObject;
import locators.homePage_locator;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.homePage;
import utils.Constants;

import java.time.Duration;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class homePage_step extends ElementBase {

    private final homePage homePage = new homePage();
    WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    homePage_locator homePageLocator;
    @Given("Users want to open homepage")
    public void usersWantToOpenHomepage() {
        homePage.openLoginPage();

    }
    @Then("They should be able to see the homepage")
    public void theyShouldBeAbleToSeeTheHomepage(){
        Assert.assertEquals(Constants.URL,getCurrentUrl());
        homePage.checkThatHomePageIsDisplayed();
    }

    @Then("They should be able to see the homepage 1")
    public void theyShouldBeAbleToSeeTheHomepage1(){
        Assert.assertEquals("Fail",getCurrentUrl());
        homePage.checkThatHomePageIsDisplayed();
    }

    @Then("They want to select a centre")
    public void theyWantToSelectACentre() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(1));
//        Thread.sleep(3);
        homePage.chooseAgreeButton();
        homePage.clickBookNowButton();
        homePage.chooseACentre();
    }

    @Then("They want to select a centree")
    public void theyWantToSelectACentree() {
        homePage.chooseACentre();
    }
}
