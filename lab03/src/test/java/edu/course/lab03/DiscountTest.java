package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void noDiscountReturnsSamePrice() {
        NoDiscount noDiscount = new NoDiscount();
        assertEquals(100.0, noDiscount.apply(100.0), 0.0001);
    }

    @Test
    void percentDiscountAppliesCorrectly() {
        PercentDiscount discount = new PercentDiscount(20);
        assertEquals(80.0, discount.apply(100.0), 0.0001);
    }

    @Test
    void percentDiscountThrowsForInvalidPercent() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-5));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(105));
    }

    @Test
    void priceCalculatorCanChangePolicy() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0), 0.0001);

        calculator = new PriceCalculator(new PercentDiscount(50));
        assertEquals(50.0, calculator.calculate(100.0), 0.0001);
    }
}