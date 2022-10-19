package ru.gb.HW_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Random;

public class Test_4 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://www.detmir.ru/");

        //во всплывающем окне "Ваш регион" выбрать "Верно"
        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(By.xpath("//input[@placeholder='Электронная почта']")).sendKeys("mail" + new Random().nextInt(100) + "@mail.ru");
        driver.findElement(By.xpath("//span[.='Подписаться']")).click();

        String s = driver.findElement(By.xpath("//p[text()='Спасибо!']")).getText();

        if (s.equals("Спасибо!\nТеперь вы подписаны\nна рассылку")) {
            System.out.println("Тест №4 пройден");
        }
        driver.quit();
    }
}
