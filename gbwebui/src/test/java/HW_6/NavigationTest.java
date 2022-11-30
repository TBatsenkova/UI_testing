package HW_6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NavigationTest extends DetMirBaseTest{

    @Test
    @DisplayName("Navigation bar: Проверка отображения элементов.")
    void test3() {
        Assertions.assertTrue(navigation
                .checkNavigationElementsName());
        logger.info("Элементы навигации отображаются верно");

    }
}
