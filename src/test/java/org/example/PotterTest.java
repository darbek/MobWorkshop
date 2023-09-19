package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PotterTest {

    int book1;
    int book2;
    int book3;
    int book4;
    int book5;

    public static int numBooks(List<Integer> books)
    {
        return (int) books.stream().filter(book -> book > 0).count();
    }

    public static double calculatePrice(List<Integer> books) {
        int discountPercentage = 0;

        switch (numBooks(books)){
            case 3: {
                discountPercentage = 10;
            }
            case 2: {
                discountPercentage = 5;
            }
            case 1: {
                discountPercentage = 0;
            }
        }

        if (numBooks(books)>=2) {
            discountPercentage = 5;
        }

        if (numBooks(books)>=3) {
            discountPercentage = 5;
        }

        var pricePerBook = 8;
        var totalPrice = (books.get(0)+books.get(1)+books.get(2)) * pricePerBook;
        var discountedPrice = totalPrice * (100.00 - discountPercentage) / 100.00;
        return discountedPrice;
    }

    @Test
    void name() {
        assertEquals(8, calculatePrice(Arrays.asList(1,0,0,0,0)));
        assertEquals(16, calculatePrice(Arrays.asList(2,0,0,0,0)));
        assertEquals(16 * 0.95, calculatePrice(Arrays.asList(1,1,0,0,0)));
        assertEquals(16 * 0.95, calculatePrice(Arrays.asList(1,0,1,0,0)));
        assertEquals(8* 3 * 0.90, calculatePrice(Arrays.asList(1,1,1,0,0)));
    }

    @Test
    void numBooksTest() {
        assertEquals(0, numBooks(Arrays.asList(0,0,0,0,0)));
        assertEquals(2, numBooks(Arrays.asList(1,1,0,0,0)));
        assertEquals(2, numBooks(Arrays.asList(2,1,0,0,0)));
        assertEquals(5, numBooks(Arrays.asList(1,2,3,4,5)));
    }
}
