package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeValueOfParamsTest {

    @Parameterized.Parameter
    public String name; // входная строка для valueOf

    @Parameterized.Parameter(1)
    public IngredientType expected; // ожидаемая константа

    @Parameterized.Parameters(name = "{index}: valueOf(\"{0}\") -> {1}")
    public static Object[][] data() {
        return new Object[][]{
                {"SAUCE",   IngredientType.SAUCE},
                {"FILLING", IngredientType.FILLING}
        };
    }

    @Test
    public void valueOfReturnsCorrectConstant() {
        assertEquals(expected, IngredientType.valueOf(name));
    }
}
