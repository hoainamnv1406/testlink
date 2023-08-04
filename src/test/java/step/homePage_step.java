package step;

import elements.ElementBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.homePage;
import utils.Constants;

public class homePage_step extends ElementBase {

    private final homePage homePage = new homePage();

    @Given("Users want to open homepage")
    public void usersWantToOpenHomepage() {
        homePage.openLoginPage();
    }

    @When("User login")
    public void userLogin() throws InterruptedException {
        homePage.userLogin();
        Thread.sleep(10000);
    }
}
