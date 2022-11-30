package HW_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest extends DetMirBaseTest {

    @ParameterizedTest
    @CsvSource({"коляска","41936","toy"})
    @DisplayName("Поиск товара")
    void test1(String product) {
        mainPage
                .productSearch(product)
                .checkSearchResult(product)
                .checkHowManyProductsWereFound(product);
        assertThat(driver.findElement(By.xpath("//h1")).getText()).contains("найден");
        logger.info("поиск товара выполнен");
    }
}
