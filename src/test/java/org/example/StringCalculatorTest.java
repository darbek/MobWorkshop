package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    static class StringCalculator {
        int add(String s) {
            if (s.isEmpty()) {
                return 0;
            }
            char delimiter = ',';
            if (s.contains("//")) {
                delimiter = s.charAt(2);
                s = s.substring(4);
            }
            if (hasSeparator(s, delimiter)) {
                return sumMultipleNumbers(s, delimiter);
            }
            return Integer.valueOf(s);
        }

        private static int sumMultipleNumbers(String s, char delimiter) {
            final String x = sanitize(s, delimiter);
            return Arrays
                .stream(split(x, delimiter))
                .mapToInt(Integer::valueOf)
                .sum();
        }

        private static String[] split(String s2, char delimiter) {
            final String[] numbers = s2.split(String.valueOf(delimiter));
            return numbers;
        }

        private static String sanitize(String s, char delimiter) {
            final var s2 = s.replace('\n', delimiter);
            return s2;
        }

        private static boolean hasSeparator(String s, char delimiter) {
            return s.contains(String.valueOf(delimiter));
        }

        private static int sum(String firstNumber, String secondNumber) {
            return Integer.valueOf(firstNumber) + Integer.valueOf(secondNumber);
        }
    }

    @Test
    void emptyString() {
        final StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(2, stringCalculator.add("2"));
        assertEquals(2, stringCalculator.add("1,1"));
        assertEquals(4, stringCalculator.add("1,1,2"));
        assertEquals(9, stringCalculator.add("1,1,2,5"));
        assertEquals(6, stringCalculator.add("1\n2,3"));
        assertEquals(3, stringCalculator.add("//;\n1;2"));
        assertEquals(6, stringCalculator.add("//-\n1-2-3"));
    }
}
