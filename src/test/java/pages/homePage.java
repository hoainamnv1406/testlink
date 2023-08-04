package pages;

import elements.ElementBase;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import locators.homePage_locator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class homePage extends ElementBase {

    private final homePage_locator locator = new homePage_locator();

    private final ElementBase elementBase = new ElementBase();
    public void openLoginPage(){
        open();
    }

    public void userLogin(){
        enterData(locator.ipt_username, "hoainam1406");
        enterData(locator.ipt_pass, "Hoainam1406@");
        clickElement(locator.btn_login);
        getDriver().switchTo().frame(getDriver().findElement(By.name("titlebar")));
        Select dbx = new Select(getDriver().findElement(By.name("testproject")));
        dbx.selectByValue("60403");
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(getDriver().findElement(By.xpath("(//iframe[@name='mainframe'])[1]")));
        clickElement(locator.btn_excute);
        getDriver().switchTo().frame(getDriver().findElement(By.xpath("(//iframe[@name='treeframe'])[1]")));
        clickElement(By.xpath("//tbody/tr[9]/td[2]/div[1]/a[1]/span[1]"));
        WebElement countryUL= getElement(By.xpath("(//div[@class='chosen-drop'])[9]/ul"));
        List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
        for (WebElement li : countriesList) {
            if (li.getText().equals("Not Run")) {
                li.click();
            }
        }
        clickElement(By.xpath("//input[@id='doUpdateTree']"));
        clickElement(By.xpath("//input[@id='expand_tree']"));
        clickElement(By.xpath("//b[contains(text(), 'HOB')]"));
        getDriver().switchTo().parentFrame();
        getDriver().switchTo().frame(getDriver().findElement(By.xpath("(//iframe[@name='workframe'])[1]")));

        for (int i = 0; i < 200; i++) {
            scrollDown(getElement(By.xpath("//div[@class='messages']")));
//        clickElement(By.xpath("//img[@id='fastExecNextp_60484']"));
            WebElement element = getDriver().findElement(By.xpath("//img[@title = 'Click to set to passed and move to next']"));
            JavascriptExecutor executor = (JavascriptExecutor)getDriver();
            executor.executeScript("arguments[0].click();", element);
        }
    }

//h2[contains(text(), '2 games & 2 drinks')]
}
