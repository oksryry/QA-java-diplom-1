package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void enumContainExactlySauceAndFillingValues() {
        IngredientType[] actual = IngredientType.values();
        assertArrayEquals(
                new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                actual
        );
    }
}
