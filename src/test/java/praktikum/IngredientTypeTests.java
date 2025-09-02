package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTests {

    @Test
    public void enumContainExactlySauceAndFillingValues() {
        IngredientType[] actual = IngredientType.values();
        assertArrayEquals(
                new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                actual
        );
    }

    @Test
    public void valueOfReturnsCorrectConstants() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
