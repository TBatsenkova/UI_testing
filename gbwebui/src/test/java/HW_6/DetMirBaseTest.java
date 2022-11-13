package HW_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.gb.HW_6.CartPage;
import ru.gb.HW_6.MainPage;
import ru.gb.HW_6.ProductPage;
import java.time.Duration;

public class DetMirBaseTest {
    WebDriver driver;
    MainPage mainPage;
    ProductPage productPage;
    CartPage cartPage;


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
        driver.manage().window().setSize(new Dimension(1600, 1200));

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        driver.get("https://www.detmir.ru/");
    }


    @AfterEach
    void tearDriver() {
        driver.quit();
    }
}


