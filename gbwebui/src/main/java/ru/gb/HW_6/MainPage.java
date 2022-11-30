package ru.gb.HW_6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.Random;


public class MainPage extends BaseView {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInputElement;

    @FindBy(xpath = "//span[.='Поиск']")
    private WebElement searchButton;

    @FindBy(xpath = "//h4 ")
    private List<WebElement> searchResultProductList;

    @Step("поиск товара")
    public MainPage productSearch(String product) {
        javascriptExecutor.executeScript("arguments[0].click();", searchButton);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchInputElement));
        actions
                .moveToElement(searchInputElement)
                .sendKeys(product, Keys.ENTER)
                .perform();
        webDriverWait.until(d -> searchResultProductList.size() >= 1);
        return this;
    }

    @Step("кликаем нужный товар")
    public MainPage clickProduct(String product) {
        WebElement searchResult = searchResultProductList.stream().filter(
                webElement -> webElement.getText().contains(product)).findFirst().get();
        javascriptExecutor.executeScript("arguments[0].click();", searchResult);
        return this;
    }

    @Step("проверяем что найден хотя бы один товар")
    public MainPage checkSearchResult(String product) {
        Assertions.assertTrue(searchResultProductList.stream().filter(
                d -> d.getText().contains(product)).findFirst().get().isDisplayed());
        return this;
    }

    //проверка, сколько найдено товаров. Если бы были требования, можно указать минимальное количество товаров,
    //которое должно появляться в ответе на поиск
    @Step("сколько запрашиваемых товаров присутствует на странице")
    public MainPage checkHowManyProductsWereFound(String productName) {
        int productsWereFound = searchResultProductList.size();

        System.out.println("Найдено товаров, содержащих в названии " + productName.toUpperCase() + ": " + productsWereFound);
        return this;
    }

    @FindBy(xpath = "//input[@placeholder='Электронная почта']")
    private WebElement inputEmail;

    @FindBy(xpath = "//span[.='Подписаться']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//h3[text()='Спасибо!']")
    private WebElement thanksForSubscribing;

    @Step("оформляем подписку на рассылку")
    public MainPage subscribeForMailing() {
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        inputEmail.sendKeys("mail" + new Random().nextInt(1000) + "@mail.ru");
        subscribeButton.click();
        return this;
    }

    @Step("проверяем что подписка на рассылку успешно оформлена")
    public MainPage checkSubscribeIsSuccess() {
        webDriverWait.until(ExpectedConditions.visibilityOf(thanksForSubscribing));
        Assertions.assertTrue(thanksForSubscribing.isDisplayed());
        return this;
    }


}
