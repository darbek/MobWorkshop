package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    static class StringCalculator {
        public static int add(String numbers) {
            if (numbers.equals("")) {
                return 0;
            }
            if(numbers.startsWith("//;")) {
                return Arrays.stream(numbers.replace("//;", ",")
                        .split(","))
                    .mapToInt(s -> Integer.parseInt(s))
                    .sum();
            }
            return Arrays.stream(numbers.replace("\n", ",")
                    .split(","))
                .mapToInt(s -> Integer.parseInt(s))
                .sum();
        }
    }

    @Test
    void calculatorTests() {
        assertEquals(0, StringCalculator.add(""));
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(3, StringCalculator.add("1,2"));
        assertEquals(6, StringCalculator.add("1,2,3"));
        assertEquals(7, StringCalculator.add("1,2,4"));
        assertEquals(5, StringCalculator.add("1,1,1,1,1"));
        assertEquals(6, StringCalculator.add("1\n2,3"));
        assertEquals(3, StringCalculator.add("//;\n1;2"));

    }
}
