package HW_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SearchTest extends DetMirBaseTest {

    @ParameterizedTest
    @CsvSource({"коляска","41936","toy"})
    @DisplayName("Поиск товара")
    void test1(String product) {
        mainPage
                .productSearch(product)
                .checkSearchResult(product)
                .checkHowManyProductsWereFound(product);
    }
}
