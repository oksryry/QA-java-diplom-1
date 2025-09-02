package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTests {

    @Parameterized.Parameter(0)
    public IngredientType type;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public double price; // держим double в тесте

    @Parameterized.Parameters(name = "{index}: type={0}, name=\"{1}\", price={2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 0.99},
                {IngredientType.SAUCE, "sour cream", 700},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.FILLING, "sausage", -100.17} // отрицательная цена допустима текущей моделью
        });
    }

    @Test
    public void gettersAndConstructorReturnSameValues() {
        Ingredient ingredient = new Ingredient(type, name, (float)price);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals((float)price, ingredient.getPrice(), 1e-5);
    }
}
