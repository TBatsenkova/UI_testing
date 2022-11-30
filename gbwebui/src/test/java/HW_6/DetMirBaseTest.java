package HW_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.HW_6.*;
import ru.gb.HW_7.CustomLogger;
import ru.gb.HW_7.JunitExtension;
import java.io.ByteArrayInputStream;
import java.time.Duration;

public class DetMirBaseTest {
    WebDriver driver;
    MainPage mainPage;
    ProductPage productPage;
    CartPage cartPage;
    NavigationBlock navigation;
    AuthorizationBlock authorization;

    Logger logger = LoggerFactory.getLogger("DetMirTest.class");

    @RegisterExtension
    JunitExtension testWatcher = new JunitExtension();

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

        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver(options));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().setSize(new Dimension(1600, 1200));

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        navigation = new NavigationBlock(driver);
        authorization = new AuthorizationBlock(driver);

        Assertions.assertDoesNotThrow(() -> driver.navigate().to("https://www.detmir.ru"),
                "Страница не доступна");
        Assertions.assertTrue(true);
    }


    @AfterEach
    void tearDriver() {
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log : browserLogs) {
            Allure.addAttachment("Логи браузера", log.getMessage());
        }

        testWatcher.setScreenshot(new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }
}



