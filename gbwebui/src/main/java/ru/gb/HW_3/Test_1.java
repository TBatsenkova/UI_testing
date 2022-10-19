package ru.gb.HW_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Test_1 {
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

        driver.findElement(By.xpath("//span[.='Поиск']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("коляска");

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='коляска']")));
        driver.findElement(By.xpath("//div[.='коляска']")).click();

        driver.quit();
    }
}
