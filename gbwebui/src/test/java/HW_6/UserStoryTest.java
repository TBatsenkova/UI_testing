package HW_6;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.gb.HW_7.JunitExtension;

@Story("User Story №1")
public class UserStoryTest extends DetMirBaseTest {

    @Test
    @DisplayName("Test: User Story №1")
    void userStory() {
        mainPage
                .subscribeForMailing()
                .checkSubscribeIsSuccess();
        logger.info("подписка на рассылку оформлена");

        mainPage
                .productSearch("подставка для карандашей")
                .checkSearchResult("подставка");
        logger.info("товар найден");

        mainPage
                .clickProduct("41936");
        productPage
                .addToCart();
        cartPage
                .checkProductIsInCart();
        logger.info("товар добавлен в корзину");

        cartPage
                .deleteFromCartUsingInputQuantity()
                .checkIsCartEmpty();
        logger.info("товар удален из корзины");
    }
}
