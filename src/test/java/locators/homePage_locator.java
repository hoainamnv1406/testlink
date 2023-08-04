package locators;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class homePage_locator extends PageObject{

    public By ipt_username = By.xpath("(//input[@id='tl_login'])[1]");

    public By ipt_pass = By.xpath("(//input[@id='tl_password'])[1]");

    public By btn_login = By.xpath("(//input[@id='tl_login_button'])[1]");

    public By btn_excute = By.xpath("//a[normalize-space()='Execute Tests']");

}
