package locators;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class homePage_locator extends PageObject{

    public By btn_centres = By.xpath("//header/div[1]/nav[1]/ul[2]/li[4]/span[1]");
    public By btn_bookNow = By.cssSelector("body.new-booking-widget:nth-child(2) main.main:nth-child(3) section.quick-book:nth-child(3) div.quick-book__bookNow div.bookNowGraphic.bookingWidget__trigger svg:nth-child(1) > path:nth-child(2)");

    public By txt_Ashford = By.xpath("//li[contains(text(),'Ashford')]");

    public By btn_agree = By.xpath("//span[contains(text(),'AGREE')]");
    public By dropdown_selectCentre = By.className("form__select");





}
