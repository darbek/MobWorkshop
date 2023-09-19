package org.example;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    static class StringCalculator {
        int add(String input){
            if (input.isEmpty()) {
                return 0;
            }
            String regex = "[,\n]";
            if (input.startsWith("//")) {
                regex = input.substring(2,3);
                input = input.substring(4);
            }
            String[] numbers = input.split(regex);
            var ints_stream = Stream.of(numbers).mapToInt(Integer::valueOf);
            var negatives = ints_stream.filter( x-> x < 0);
            return Stream.of(numbers).mapToInt(Integer::valueOf)
                   /* .map(n-> {
                        return n > 0 ? n : throw new RuntimeException("Negative numbers not allowed:" + n);
                    })*/
            .sum();
        }
    }

    @Test
    void addMethodTest() {
        StringCalculator sc = new StringCalculator();
        assertEquals(0, sc.add(""));
        assertEquals(1, sc.add("1"));
        assertEquals(3, sc.add("1,2"));
        assertEquals(10, sc.add("1,2,3,4"));
        assertEquals(6, sc.add("1\n2,3"));
        assertEquals(3,sc.add("//;\n1;2"));
        assertEquals(3,sc.add("//a\n1a2"));
        assertThrows(RuntimeException.class, () -> sc.add("1,4,-1"));
    }


}
