package HW_5;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DetMirTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    //Actions actions;
    private static Logger logger = LoggerFactory.getLogger("DetMirTest.class");

    public static String loadRandomPhoneNumber() {
        Random rng = new Random();
        Integer areaCode = rng.nextInt(900) + 100;
        Integer phoneNumber = rng.nextInt(9000000) + 1000000;
        String fullNumber = areaCode.toString() + phoneNumber.toString();
        return fullNumber;
    }

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setupBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //actions = new Actions(driver);
        driver.manage().window().setSize(new Dimension(1600, 1200));
        driver.get("https://www.detmir.ru/");
    }


    @Nested
    @DisplayName("Test suite 1: Search")
    class searchTesting {

        @BeforeEach
        void clickSearch() {
            //использую JS тк элемент Поиск перекрывает div "Ваш регион"

            WebElement search = driver.findElement(By.xpath("//span[.='Поиск']"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", search);
        }

        @Test
        @DisplayName("Test 1.0: Поиск товара по наименованию")
        void test1_0() {
            driver.findElement(By.xpath("//input[@type='search']")).sendKeys("коляска");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='коляска']")));
            driver.findElement(By.xpath("//div[.='коляска']")).click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

            assertThat(driver.findElement(By.xpath("//h1")).getText()).contains("найдено");
            logger.info("поиск по наименованию (коляска) выполнен");
        }

        @Test
        @DisplayName("Test 1.1: Поиск товара по артикулу")
        void test1_1() {
            driver.findElement(By.xpath("//input[@type='search']")).sendKeys("41936");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a//div/img")));
            driver.findElement(By.xpath("//a//div/img")).click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

            assertThat(driver.findElement(By.xpath("//h1")).getText()).isEqualTo("Конструктор LEGO Dots Подставка для карандашей 41936");
            logger.info("поиск по артикулу (41936) выполнен");
        }
    }


    @Nested
    @DisplayName("Test suite 2: Cart")
    class CartTesting {

        @BeforeEach
        void addToCart() {
            driver.get("https://www.detmir.ru/product/index/id/3596822/");
            driver.findElement(By.xpath("//span[text()='В корзину']")).click();
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='В корзине']")));
            driver.findElement(By.xpath("//span[text()='В корзине']")).click();
        }

        @Test
        @DisplayName("Test 2.0: Добавить товар в корзину")
        void test2_0() {
            assertThat(driver.findElement(By.xpath("//*[text()='Конструктор LEGO Dots Подставка для карандашей 41936']")).isDisplayed());
            logger.info("Товар в корзину добавлен");
        }

        @Test
        @DisplayName("Test 2.1: Удалить товар из корзины через input")
        void test2_1() {
            //проверяем, что в корзине есть товар
            Assumptions.assumeTrue(driver.findElement(By.xpath("//*[text()='Конструктор LEGO Dots Подставка для карандашей 41936']")).isDisplayed());

            driver.findElement(By.xpath("//input[@type='number']")).sendKeys(Keys.BACK_SPACE);
            driver.findElement(By.xpath("//input[@type='number']")).sendKeys("0");

            assertThat(driver.findElement(By.xpath("//div[text()='Вернуть']")).isDisplayed());
            logger.info("товар из корзины удален");
        }

        @Test
        @DisplayName("Test 2.2: Удалить товар из корзины c помощью чек-бокса \"Выбрать все\"")
        void test2_2() {
            //проверяем, что в корзине есть товар
            Assumptions.assumeTrue(driver.findElement(By.xpath("//*[text()='Конструктор LEGO Dots Подставка для карандашей 41936']")).isDisplayed());

            driver.findElement(By.xpath("//header//label//div/i/*[name()='svg']")).click();

            assertThat(driver.findElement(By.xpath("//div[text()='Выбрать все']")).isDisplayed());
            logger.info("товар из корзины удален");
        }
    }

    @Nested
    @DisplayName("Test suite 3: Navigation bar. Проверка отображения элементов.")
    class NavBar {
        public List<WebElement> allElements;

        @BeforeEach
        void setScreenSize() {
            allElements = driver.findElements(By.xpath("//nav/div/ul/li"));
            List<WebElement> allProduct = driver.findElements(By.xpath("//nav/div/ul/li"));
            //for( WebElement product : allProduct){
            //    System.out.println(product.getText());
            //}
        }

        @Test
        @DisplayName("Поиск")
        void test3_0() {
            assertThat(allElements.get(0).getText()).isEqualTo("Поиск");
            logger.info("Элемент " + allElements.get(0).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Реклама")
        void test3_1() {
            assertThat(allElements.get(1).getText()).isEqualTo(null);
            logger.info("Элемент " + allElements.get(1).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Акции")
        void test3_2() {
            assertThat(allElements.get(2).getText()).isEqualTo("Акции");
            logger.info("Элемент " + allElements.get(2).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Игрушки и игры")
        void test3_3() {
            assertThat(allElements.get(3).getText()).isEqualTo("Игрушки и игры");
            logger.info("Элемент " + allElements.get(3).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Гигиена и уход")
        void test3_4() {
            assertThat(allElements.get(4).getText()).isEqualTo("Гигиена и уход");
            logger.info("Элемент " + allElements.get(4).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Детская комната")
        void test3_5() {
            assertThat(allElements.get(5).getText()).isEqualTo("Детская комната");
            logger.info("Элемент " + allElements.get(5).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Питание и кормление")
        void test3_6() {
            assertThat(allElements.get(6).getText()).isEqualTo("Питание и кормление");
            logger.info("Элемент " + allElements.get(6).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Прогулки и путешествия")
        void test3_7() {
            assertThat(allElements.get(7).getText()).isEqualTo("Прогулки и путешествия");
            logger.info("Элемент " + allElements.get(7).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Одежда и обувь")
        void test3_8() {
            assertThat(allElements.get(8).getText()).isEqualTo("Одежда и обувь");
            logger.info("Элемент " + allElements.get(8).getText() + " отображается верно");
        }

        @Test
        @DisplayName("Школа")
        void test3_9() {
            assertThat(allElements.get(9).getText()).isEqualTo("Школа");
            logger.info("Элемент " + allElements.get(9).getText() + " отображается верно");
        }

    }


    @Test
    @DisplayName("Test 4: Подписаться на рассылку ")
    void test4() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(By.xpath("//input[@placeholder='Электронная почта']")).sendKeys("mail" + new Random().nextInt(1000) + "@mail.ru");
        driver.findElement(By.xpath("//span[.='Подписаться']")).click();

        String actualStr = driver.findElement(By.xpath("//p[text()='Спасибо!']")).getText();
        String expectedStr = "Спасибо!\nТеперь вы подписаны\nна рассылку";

        Assertions.assertEquals(expectedStr, actualStr);
        logger.info("подписка на рассылку оформлена");

    }


    @Test
    @DisplayName("Test 5: Авторизация с не верным кодом.")
    void test5() throws InterruptedException {
        driver.findElement(By.xpath("//div[2]/header/div[3]//div[text()='Вход и регистрация']")).click();
        driver.findElement(By.xpath("//div[text()='Войти по номеру телефона']")).click();
        
        //используем рандомный номер телефона ввиду отсутствия релевантных тестовых данных
        driver.findElement(By.id("phone")).sendKeys(loadRandomPhoneNumber());
        driver.findElement(By.id("phone")).click();
        driver.findElement(By.xpath("//div[text()='Продолжить']")).click();

        //иногда появляется капча, поэтому ждем, чтобы вручную ее отработать
        Thread.sleep(5);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Запросить код')]")));
        //вводим рандомный код
        driver.findElement(By.xpath("//*[@id=\"i_7\"]/div[2]//div[1]/label/input")).sendKeys(Integer.toString(new Random().nextInt(10)));
        driver.findElement(By.xpath("//*[@id=\"i_7\"]//div[2]/label/input")).sendKeys(Integer.toString(new Random().nextInt(10)));
        driver.findElement(By.xpath("//*[@id=\"i_7\"]//div[3]//input")).sendKeys(Integer.toString(new Random().nextInt(10)));
        driver.findElement(By.xpath("//*[@id=\"i_7\"]//div[4]//input")).sendKeys(Integer.toString(new Random().nextInt(10)));

        assertThat(driver.findElement(By.xpath("//div[text()='Код не подошел']")).isDisplayed());
        logger.info("код не подошел");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}



