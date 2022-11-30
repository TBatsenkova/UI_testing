package ru.gb.HW_6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends MainPage {

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//span[text()='В корзину']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[text()='В корзине']")
    private WebElement inCartButton;

    public ProductPage addToCart() {
        addToCartButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(inCartButton)).isDisplayed();
        inCartButton.click();
        return this;
    }

}
