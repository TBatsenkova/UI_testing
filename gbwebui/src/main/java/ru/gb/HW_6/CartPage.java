package ru.gb.HW_6;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BaseView{
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[@type='number']")
    private WebElement productQuantity;

    @FindBy(xpath = "//header//label//div/i/*[name()='svg']")
    private WebElement checkBoxButton;

    @FindBy(xpath = "//h4")
    private WebElement productInCart;

   public CartPage checkProductIsInCart(){
       Assertions.assertTrue(productInCart.isDisplayed());
       return this;
   }


    public CartPage deleteFromCartUsingInputQuantity () {
        productQuantity.sendKeys(Keys.BACK_SPACE);
        productQuantity.sendKeys("0");
        return this;
    }

    public CartPage deleteFromCartUsingCheckBox () {
        checkBoxButton.click();
        return this;
    }

    @FindBy(xpath = "//div[text()=\"Оформить заказ\"]")
    private WebElement makeOrderButton;

    public boolean checkIsCartEmpty() {
        try {
            return makeOrderButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
