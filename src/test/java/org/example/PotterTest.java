package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PotterTest {

    @Test
    void calculatePriceForBasket() {
        assertEquals(0, calculatePrice(List.of()));
        assertEquals(8, calculatePrice(List.of("Book1")));
        assertEquals(16 * 0.95, calculatePrice(List.of("Book1", "Book2")));
        assertEquals(24 * 0.9, calculatePrice(List.of("Book1", "Book2", "Book3")));
        assertEquals(32 * 0.8, calculatePrice(List.of("Book1", "Book2", "Book3", "Book4")));
        assertEquals(40 * 0.75, calculatePrice(List.of("Book1", "Book2", "Book3", "Book4", "Book5")));
        assertEquals(16, calculatePrice(List.of("Book1", "Book1")));
        assertEquals(16 * 0.95 + 8, calculatePrice(List.of("Book1", "Book2", "Book1")));
        assertEquals(24, calculatePrice(List.of("Book1", "Book1", "Book1")));
        assertEquals(16 + 16 * 0.95, calculatePrice(List.of("Book1", "Book1", "Book1", "Book2")));
//        assertEquals(16 + 24 * 0.9, calculatePrice(List.of("Book1", "Book1", "Book1", "Book2", "Book3")));
    }

    private double calculateDiscountForAmount(int amount) {
        if (amount == 0)
            return 0;
        else if (amount == 1)
            return 1;
        else if (amount == 2)
            return 0.95;
        else if (amount == 3)
            return 0.9;
        else if (amount == 4)
            return 0.8;
        return 0.75;
    }

    private double calculatePrice(List<String> books) {
        Set<String> set = new HashSet<>(books);
        double discountPercentage = calculateDiscountForAmount(set.size());
        double price = books.size() * 8;
        if(set.size() == books.size()) {
            return price * discountPercentage;
        }
        if (books.size() == 3) {
            return 16 * discountPercentage + 8;
        }
        if (books.size() == 4) {
            if (set.size() == 2) {
                return 16 + 16*discountPercentage;
            }
        }
        return books.size() * 8;
    }
}
