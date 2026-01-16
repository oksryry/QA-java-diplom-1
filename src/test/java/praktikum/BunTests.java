package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static praktikum.support.Constants.DELTA_4;

@RunWith(Parameterized.class)
public class BunTests {

    @Parameter
    public String name;

    @Parameter(1)
    public double price;

    @Parameters(name = "{index}: Наименование -  {0}, цена - {1}")
    public static Object[][] data() {
        return new Object[][] {
                {"black bun", 250},
                {"white bun", 300.50},
                {"red bun", -400.45}
        };
    }

    @Test
    public void bunGetPriceAndNameTest() {
        Bun bun = new Bun(name, (float) price);
        Assert.assertEquals(price, bun.getPrice(), DELTA_4);
        Assert.assertEquals(name, bun.getName());
    }
}
