package step;

import elements.ElementBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.homePage;
import utils.Constants;

public class homePage_step extends ElementBase {

    private final homePage homePage = new homePage();

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
    public void theyWantToSelectACentre(){
        homePage.chooseAgreeButton();
        homePage.clickBookNowButton();
        homePage.chooseACentre();
    }

    @Then("They want to select one centre")
    public void theyWantToSelectOneCentre() {
        homePage.chooseACentre();

    }
}
