package HW_6;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Авторизация")
public class AuthorizationTest extends DetMirBaseTest {

    @Test
    @DisplayName("Авторизация по телефону с неверным кодом")
    void test4() throws InterruptedException {
        authorization.fallerAuthorization();
        Assertions.assertTrue(authorization.checkMassageWrongCode());
        logger.info("авторизация с неверным кодом выполнена успешно");
    }
}
