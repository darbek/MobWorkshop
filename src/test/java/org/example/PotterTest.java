package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotterTest {
    static class Potter {
        static double buy(String... input) {
            var discountMap = Map.of(
                    2, 0.05,
                    3, 0.1,
                    4, 0.2,
                    5, 0.25
            );
            if (input.length == 1) {
                var singleBookPrice = 8;
                return singleBookPrice;
            }
            if (isAUniqueSet(input)) {
                return sumWithoutDiscount(input) * (1 - discountMap.get(input.length));
            }
            return sumWithoutDiscount(input);
        }

        private static int sumWithoutDiscount(String[] input) {
            return input.length * 8;
        }

        private static boolean isAUniqueSet(String[] input) {
            var set = new HashSet<>(Arrays.stream(input).toList());
            return set.size() == input.length;
        }
    }

    @Test
    void Potter() {
        assertEquals(8, Potter.buy("first"));
        assertEquals(16 * 0.95, Potter.buy("first", "second"));
        assertEquals(16, Potter.buy("first", "first"));
        assertEquals(24 * 0.9, Potter.buy("first", "second", "third"));
        assertEquals(32 * 0.8, Potter.buy("first", "second", "third", "fourth"));
        assertEquals(40 * 0.75, Potter.buy("first", "second", "third", "fourth", "fifth"));
    }
}
