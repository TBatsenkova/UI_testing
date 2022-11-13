package HW_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserStoryTest extends DetMirBaseTest {

    @Test
    @DisplayName("Test: User Story №1")
    void userStory() {
        mainPage
                .subscribeForMailing()
                .checkSubscribeSuccess()
                .productSearch("подставка для карандашей")
                .checkSearchResult("подставка")
                .clickProduct("41936");
        productPage
                .addToCart();
        cartPage
                .checkProductIsInCart()
                .deleteFromCartUsingInputQuantity()
                .checkIsCartEmpty();
    }
}
