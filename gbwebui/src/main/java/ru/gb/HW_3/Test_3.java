package ru.gb.HW_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Test_3 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://www.detmir.ru/product/index/id/3596822/");

        driver.findElement(By.xpath("//span[text()='В корзину']")).click();
        driver.findElement(By.xpath("//span[text()='В корзине']")).click();

        String s = driver.findElement(By.xpath("//*[text()='Конструктор LEGO Dots Подставка для карандашей 41936']")).getText();
        if (s.equals("Конструктор LEGO Dots Подставка для карандашей 41936")) {
            System.out.println("Тест №3 пройден");
        }
        driver.quit();
    }
}
