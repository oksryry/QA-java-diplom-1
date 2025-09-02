package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.junit.Before;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTests {

    private Bun bun;
    private Ingredient sauceMock;
    private Ingredient fillingMock;

    @Before
    public void setUp() {
        bun = org.mockito.Mockito.mock(Bun.class);
        sauceMock = org.mockito.Mockito.mock(Ingredient.class);
        fillingMock = org.mockito.Mockito.mock(Ingredient.class);
    }

    @Parameter
    public String bunName;
    @Parameter(1)
    public double bunPrice;

    @Parameter(2)
    public IngredientType sauceType;

    @Parameter(3)
    public String sauceName;

    @Parameter(4)
    public double saucePrice;


    @Parameter(5)
    public IngredientType fillingType;

    @Parameter(6)
    public String fillingName;

    @Parameter(7)
    public double fillingPrice;



    @Parameters(name = "{index}: булочка {0} - стоимость {1}, i1={2} {3} {4}, i2={5} {6} {7}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // bunName, bunPrice,  t1,                   n2,       p2,   t2,                    n3,       p3
                {"white bun", 200,    IngredientType.SAUCE, "chili",   50,   IngredientType.FILLING, "cutlet", 30},
                {"black bun", 320.50,  IngredientType.FILLING,"bacon",  70.4, IngredientType.SAUCE,   "mayo",   25.6},
                {"red bun",   90,     IngredientType.SAUCE, "bbq",     0,    IngredientType.FILLING, "cheese", -10.75}
        });
    }



    @Test
    public void burgerGetIngridientsAndPriceTest() {
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn((float) bunPrice);

        when(sauceMock.getType()).thenReturn(sauceType);
        when(sauceMock.getName()).thenReturn(sauceName);
        when(sauceMock.getPrice()).thenReturn((float) saucePrice);

        when(fillingMock.getType()).thenReturn(fillingType);
        when(fillingMock.getName()).thenReturn(fillingName);
        when(fillingMock.getPrice()).thenReturn((float) fillingPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        double expected = bunPrice * 2 + saucePrice + fillingPrice;
        Assert.assertEquals(expected, burger.getPrice(), 1e-5);

        String receipt = burger.getReceipt();
        Assert.assertThat(receipt, containsString("= " + sauceType.toString().toLowerCase() + " " + sauceName + " ="));
        Assert.assertThat(receipt, containsString("= " + fillingType.toString().toLowerCase() + " " + fillingName + " ="));

    }
}
