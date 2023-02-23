package elements;

import helpers.LogHelper;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;
import java.util.List;

public class ElementBase extends PageObject {

    WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    protected WebElement getElement(By by) {
        return getDriver().findElement(by);
    }

    protected List<WebElement> getElements(By by) {
        return getDriver().findElements(by);
    }

    public void clickElement(By by) {
        waitForElementVisibility(by,Duration.ofSeconds(Constants.SHORT_WAIT));
        getElement(by).click();
    }

    protected void enterData(By by, String value) {
        getElement(by).sendKeys(value);
    }

    protected String getText(By by) {
        waitForElementToBeClickable(by);
        return getElement(by).getText();
    }

    public boolean isVisible(By by, Duration duration) {
        WebDriverWait wait = new WebDriverWait(getDriver(), duration);
        return wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
    }

    public String getElmAttribute(By by,String value){
        return getElement(by).getAttribute(value);
    }

    public String getElmsAttribute(By by, int index, String value){
        return getElements(by).get(index).getAttribute(value);
    }

    public boolean isEnabled (By by){
        return getElement(by).isEnabled();
    }


    public boolean isVisible(By by) {
        return isVisible(by, Duration.ofSeconds(Constants.SHORT_WAIT));
    }

    protected void waitForElementToBeClickable(By by, Duration duration) {
        try {
            LogHelper.info(String.format("Wait for clickable of %s", by.toString()));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            LogHelper.error(String.format("waitForElementToBeClickable: Has error with control '%s': %s", by.toString(), e.getMessage().split("\n")[0]));
        }
    }

    protected void waitForElementToBeClickable(By by) {
        waitForElementToBeClickable(by, Duration.ofSeconds(Constants.MEDIUM_WAIT));
    }

    protected void waitForElementVisibility(By by, Duration duration) {
        try {
//            LogHelper.info(String.format("Wait for visibility of %s", by.toString()));
            WebDriverWait wait = new WebDriverWait(getDriver(), duration);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
//            LogHelper.error(String.format("waitForElementVisibility: Has error with control '%s': %s", by.toString(), e.getMessage().split("\n")[0]));
        }
    }

    protected void checkElementIsDisplayed(By by){
        try {
            waitForElementVisibility(by);
//            LogHelper.info(String.format("%s is Displayed",by.toString()));
            getElement(by).isDisplayed();
        }
        catch (Exception e){
//            LogHelper.error(String.format("checkElementIsDisplayed: Has error with control '%s': %s", by.toString(), e.getMessage().split("\n")[0]));
        }
    }

    protected void waitForElementVisibility(By by) {
        waitForElementVisibility(by, Duration.ofSeconds(Constants.SHORT_WAIT));
    }

    public void assertEqual(By by, String attribute, String data){
        waitForElementVisibility(by);
        Assert.assertEquals(getElmAttribute(by,attribute),data);
    }

    public void clearData(By by){
        waitForElementVisibility(by);
        getElement(by).clear();
    }

    public void assertNotEqual(By by, String data){
        waitForElementVisibility(by);
        Assert.assertNotEquals(getText(by),data);
    }

    public String getDayDateMonth(By by, int idx){
        waitForElementVisibility(by);
        String str = getElmAttribute(by,"text");
        String[] splitStr = str.split("\\s+");
        return splitStr[idx];
    }

    public boolean isElementPresent(By by) {
        try {
            getElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void implicitlyWait(Duration duration){
        getDriver().manage().timeouts().implicitlyWait(duration);
    }

    public void scrollUp(){
        ((JavascriptExecutor)
                getDriver()).executeScript("window.scrollTo(0, document.body.scrollTop);");
    }

    public void scrollDown(WebElement element){
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    public void pressBackspace(By by){
        enterData(by,Keys.chord(Keys.BACK_SPACE));
    }

    public void pressSpace(By by, int a){
        for (int i = 0; i < a ; i++) {
            enterData(by,Keys.chord(Keys.BACK_SPACE));
        }
    }

    public boolean checkSelected(By by){
        return getElement(by).isSelected();
    }

    public boolean checkIsDisplay(By by) {
        return getElement(by).isDisplayed();
    }

    public void checkIsEnable(By by){
        getElement(by).isEnabled();
    }

    public String getAttribute( WebElement element, String value){
        return element.getAttribute(value);
    }

    public void RefreshPage() {
        getDriver().navigate().refresh();
    }

    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    public void compareEqual(String expected, String actual) {
        Assert.assertEquals(expected,actual);
    }

    public boolean compareFalse(boolean conditionF) {
        Assert.assertFalse(conditionF);
        return false;
    }

    public boolean compareTrue(boolean conditionT) {
        Assert.assertTrue(conditionT);
        return true;
    }
}

