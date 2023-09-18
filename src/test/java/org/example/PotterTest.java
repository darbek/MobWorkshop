package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotterTest {
    static class Potter {
        static double buy(String... input) {
            if (isASetOfTwo(input)) {
                final double discount = 0.05;
                return sumWithoutDiscount(input) * (1 - discount);
            }
            if (isASetOfThree(input)) {
                final double discount = 0.1;
                return sumWithoutDiscount(input) * (1 - discount);
            }
            return sumWithoutDiscount(input);
        }

        private static boolean isASetOfThree(String[] input) {
            return input.length == 3 && !input[0].equals(input[1]) && !input[0].equals(input[2]) && !input[1].equals(input[2]);
        }

        private static int sumWithoutDiscount(String[] input) {
            return input.length * 8;
        }

        private static boolean isASetOfTwo(String[] input) {
            return input.length == 2 && !input[0].equals(input[1]);
        }
    }

    @Test
    void Potter() {
        assertEquals(8, Potter.buy("first"));
        assertEquals(16 * 0.95, Potter.buy("first", "second"));
        assertEquals(16, Potter.buy("first", "first"));
        assertEquals(24 * 0.9, Potter.buy("first", "second", "third"));
    }
}
