package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static praktikum.support.Constants.DELTA_4;

public class BurgerTests {

    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    @Before
    public void setUp() {
        bun = mock(Bun.class);
        sauce = mock(Ingredient.class);
        filling = mock(Ingredient.class);
    }

    // --- setBuns ---
    @Test
    public void setBuns_assignsField() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertSame("bun должен устанавливаться как есть", bun, burger.bun);
    }


    // --- addIngredient ---
    @Test
    public void addIngredient_appendsToList() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(2, burger.ingredients.size());
        assertSame(sauce,   burger.ingredients.get(0));
        assertSame(filling, burger.ingredients.get(1));
    }


    // --- removeIngredient ---
    @Test
    public void removeIngredient_removesByIndex() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame("После удаления первого должен остаться второй",
                filling, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_invalidIndex_throws() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        burger.removeIngredient(5); // индекса нет
    }

    // --- moveIngredient ---

    @Test
    public void moveIngredient_forward_movesItemToNewIndex() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        burger.moveIngredient(0, 2);

        assertSame(b, burger.ingredients.get(0));
        assertSame(c, burger.ingredients.get(1));
        assertSame(a, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredient_backward_movesItemToNewIndex() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        burger.moveIngredient(2, 0);

        assertSame(c, burger.ingredients.get(0));
        assertSame(a, burger.ingredients.get(1));
        assertSame(b, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredient_sameIndex_noChanges() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(a);
        burger.addIngredient(b);

        burger.moveIngredient(1, 1);

        assertSame(a, burger.ingredients.get(0));
        assertSame(b, burger.ingredients.get(1));
    }


    // --- getPrice (базовый кейс: только булка) ---
    @Test
    public void getPrice_onlyBuns_noIngredients() {
        when(bun.getPrice()).thenReturn((float) 200);

        Burger burger = new Burger();
        burger.setBuns(bun);

        double expected = 200 * 2;
        assertEquals(expected, burger.getPrice(), DELTA_4 );
    }

    // --- getReceipt (структура: шапка/подвал + строка с ценой) ---
    @Test
    public void getReceipt_hasHeaderFooterAndPriceLine() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn((float) 100);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("any");
        when(sauce.getPrice()).thenReturn((float) 10);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();

        String header = String.format("(==== %s ====)%n", "white bun");
        assertTrue("В чеке должна быть шапка", receipt.startsWith(header));
        // подвал: второй такой же заголовок
        assertTrue("В чеке должен быть подвал",
                receipt.indexOf(header) != receipt.lastIndexOf(header));

        // строка с ценой — используем расчёт самого бургера, чтобы не упасть на округлениях
        String priceLine = String.format("%nPrice: %f%n", burger.getPrice());
        assertTrue("В чеке должна быть строка с ценой", receipt.contains(priceLine));
    }

}
