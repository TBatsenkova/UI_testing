package ru.gb.HW_6;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Arrays;
import java.util.List;

public class AuthorizationBlock extends MainPage {
    public AuthorizationBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[2]/header/div[3]//div[text()='Вход и регистрация']")
    private WebElement entryAndRegistrationButton;

    @FindBy(xpath = "//div[text()='Войти по номеру телефона']")
    private WebElement enterByPhoneNumber;

    @FindBy(id = "phone")
    private WebElement inputPhoneNumber;

    @FindBy(xpath = "//h3[contains(text(),'Введите')]/following-sibling::div[1]//input[1]")
    private WebElement inputFigureOne;

    @FindBy(xpath = "//h3[contains(text(),'Введите')]/following-sibling::div[1]//input[2]")
    private WebElement inputFigureTwo;

    @FindBy(xpath = "//h3[contains(text(),'Введите')]/following-sibling::div[1]//input[3]")
    private WebElement inputFigureThree;

    @FindBy(xpath = "//h3[contains(text(),'Введите')]/following-sibling::div[1]//input[4]")
    private WebElement inputFigureFour;

    @FindBy(xpath = "//div[text()='Код не подошел']")
    private WebElement wrongCodeMassage;

    //@FindBy(xpath = "//h3[contains(text(),'Введите')]/following-sibling::div[1]//input")
    //private List<WebElement> inputCode;

    //List<WebElement> inputCode = Arrays.asList(inputFigureOne, inputFigureTwo, inputFigureThree, inputFigureFour);

    @Step("авторизация с неверным кодом")
    public AuthorizationBlock fallerAuthorization() throws InterruptedException {
        entryAndRegistrationButton.click();
        enterByPhoneNumber.click();
        inputPhoneNumber.sendKeys(randomPhoneNumber(), Keys.ENTER);

        //иногда появляется капча, поэтому ждем, чтобы вручную ее отработать
        Thread.sleep(50);

        //webDriverWait.until(ExpectedConditions.visibilityOf(inputFigureOne));
        //for (WebElement input : inputCode) {
        //    input.sendKeys(randomCode());
        //}

        inputFigureOne.sendKeys(randomCode());
        inputFigureTwo.sendKeys(randomCode());
        inputFigureThree.sendKeys(randomCode());
        inputFigureFour.sendKeys(randomCode());
        return this;
    }

    public boolean checkMassageWrongCode() {
        wrongCodeMassage.isDisplayed();
        return true;
    }
}
