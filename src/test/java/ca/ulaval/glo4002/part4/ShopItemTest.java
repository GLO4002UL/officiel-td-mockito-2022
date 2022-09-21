package ca.ulaval.glo4002.part4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ShopItemTest {
    private static final double UNIT_PRICE = 10;
    private static final int TOO_MANY_ITEMS = 5;
    private static final int A_VALID_QUANTITY = 2;
    private static final int MAXIMUM_QUANTITY = 3;
    private static final double DISCOUNT_PERCENTAGE = 0.25;

    private ShopItem item;

    @BeforeEach
    void before() {
        Configuration.setForTests(new Configuration(DISCOUNT_PERCENTAGE, MAXIMUM_QUANTITY));
        item = new ShopItem(UNIT_PRICE);
    }

    @Test
    void givenAHolidayDiscount_whenGettingTotalPrice_thenShouldApplyDiscount() {
        double expectedPrice = 15;

        double totalPrice = item.getTotalPrice(A_VALID_QUANTITY);

        assertEquals(expectedPrice, totalPrice);
    }

    @Test
    void whenGettingTotalPriceForTooManyItems_thenShouldThrowException() {
        Executable call = () -> item.getTotalPrice(TOO_MANY_ITEMS);

        assertThrows(TooManyItemsExceptions.class, call);
    }

}
