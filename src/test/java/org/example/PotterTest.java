package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotterTest {
    List<Integer> bookCount;

    @Test
    void name() {
        double pricePerBook = 8;
        assertEquals(0, calculatePrice(List.of()));
        bookCount = List.of(1, 0, 0, 0, 0);
        assertEquals(pricePerBook, calculatePrice(bookCount));
        assertEquals(pricePerBook * 2, calculatePrice(List.of(2, 0, 0, 0, 0)));
        assertEquals(pricePerBook * 2 * 0.95, calculatePrice(List.of(1, 1, 0, 0, 0)));
        assertEquals(pricePerBook * 3 * 0.9, calculatePrice(List.of(1, 1, 1, 0, 0)));
        assertEquals(pricePerBook * 2 * 0.95, calculatePrice(List.of(1, 0, 1, 0, 0)));
        assertEquals(pricePerBook * 4 * 0.8, calculatePrice(List.of(1, 1, 1, 1, 0)));
        assertEquals(pricePerBook * 5 * 0.75, calculatePrice(List.of(1, 1, 1, 1, 1)));
        assertEquals(pricePerBook * 2 * 0.95 + pricePerBook * 1, calculatePrice(List.of(2, 1, 0, 0, 0)));
    }

    private double calculatePrice(List<Integer> bookCount) {
        double discount;
        if (bookCount.isEmpty()) {
            return 0;
        }
        var numOfBook1 = bookCount.get(0) > 0 ? 1 : 0;
        var numOfBook2 = bookCount.get(1) > 0 ? 1 : 0;
        var numOfBook3 = bookCount.get(2) > 0 ? 1 : 0;
        var numOfBook4 = bookCount.get(3) > 0 ? 1 : 0;
        var numOfBook5 = bookCount.get(4) > 0 ? 1 : 0;

        int totalDistinctBooks = numOfBook1 + numOfBook2 + numOfBook3 + numOfBook4 + numOfBook5 ;

        discount = switch (totalDistinctBooks) {
            case 2 -> 5;
            case 3 -> 10;
            case 4 -> 20;
            case 5 -> 25;
            default -> 0;
        };

        double price = totalDistinctBooks * 8;

        return price * (100 - discount) / 100;
    }
}
