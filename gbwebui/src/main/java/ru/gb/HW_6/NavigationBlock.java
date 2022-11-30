package ru.gb.HW_6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Arrays;
import java.util.List;

public class NavigationBlock extends MainPage {
    public NavigationBlock(WebDriver webDriver) {
        super(webDriver);
    }
    List<String> navBarTitles = Arrays.asList(new String[]{"Поиск", "", "Акции", "Игрушки и игры", "Гигиена и уход",
            "Детская комната", "Питание и кормление", "Прогулки и путешествия", "Одежда и обувь", "Спорт и отдых"});

    @FindBy(xpath = "//nav/div/ul/li")
    private List<WebElement> listNavBarElements;

    @Step("Проверка соответствия наименования навигации с: " +
            "Поиск, \"\", Акции, Игрушки и игры, Гигиена и уход, Детская комната, Питание и кормление, Прогулки и путешествия, Одежда и обувь, Спорт и отдых")
    public boolean checkNavigationElementsName() {
        for (int i = 0; i < navBarTitles.size(); i++) {
            listNavBarElements.get(i).getText().equals(navBarTitles.get(i));
        }
        return true;
    }
}
