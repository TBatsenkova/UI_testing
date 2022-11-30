package ru.gb.HW_6;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BaseView {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    Actions actions;
    JavascriptExecutor javascriptExecutor;

    public BaseView(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        actions = new Actions(webDriver);
        javascriptExecutor = (JavascriptExecutor) webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public static String randomPhoneNumber() {
        Random rng = new Random();
        Integer areaCode = rng.nextInt(900) + 100;
        Integer phoneNumber = rng.nextInt(9000000) + 1000000;
        String fullNumber = areaCode.toString() + phoneNumber.toString();
        return fullNumber;
    }

    public static String randomCode() {
        Random rng = new Random();
        Integer number = rng.nextInt(10);
        String code = number.toString();
        return code;
    }
}

