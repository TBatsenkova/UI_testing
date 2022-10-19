package ru.gb.HW_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Test_2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        options.addArguments("start-maximized");


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://www.detmir.ru/");

        //во всплывающем окне "Ваш регион" выбрать "Верно"
        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[.='Поиск']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("41936");
        driver.findElement(By.xpath("//a//div/img")).click();

        String s = driver.findElement(By.xpath("//h1")).getText();
        if (s.equals("Конструктор LEGO Dots Подставка для карандашей 41936")) {
            System.out.println("Тест №2 пройден");
        }
        driver.quit();
    }
}
