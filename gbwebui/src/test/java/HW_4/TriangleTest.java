package HW_4;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.HW_4.*;


public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger("TriangleTest.class");

    @ParameterizedTest
    @DisplayName("Тест №1: Проверка метода getAreaOfTriangle, валидные значения сторон треугольника ")
    @CsvSource({"3,6,7, 8.94427190999916", "7,6,7, 18.973665961010276"})
    void Test1(int sideA, int sideB, int sideD, double expectedResult) throws  ThisTriangleNotExistException, SideOfTriangleIsBelowZeroException {
        Assertions.assertEquals(expectedResult, new Function().getAreaOfTriangle(sideA, sideB, sideD));
        logger.info("Test №1 PASS");
    }

    @ParameterizedTest
    @DisplayName("Тест №2: Проверка исключения ThisTriangleNotExistException, такого треугольника не существует")
    @CsvSource({"0,6,7", "0,0,7", "0,0,0"})
    void Test2(Integer sideA, Integer sideB, Integer sideD) throws ThisTriangleNotExistException {
        Assertions.assertThrows(ThisTriangleNotExistException.class, () -> Function.getAreaOfTriangle(sideA, sideB, sideD), "Test №2 FALL");
        logger.info("Test №2 PASS");
    }

    @ParameterizedTest
    @DisplayName("Тест №3: Проверка исключения SideOfTriangleIsBelowZeroException, сторона/стороны треугольника < 0")
    @CsvSource({"-3,-6,-7", "-7,-6,-7"})
    void Test3(Integer sideA, Integer sideB, Integer sideD) throws SideOfTriangleIsBelowZeroException {
        Assertions.assertThrows(SideOfTriangleIsBelowZeroException.class, () -> Function.getAreaOfTriangle(sideA, sideB, sideD), "Test №3 FALL");
        logger.info("Test №2 PASS");
    }

}
