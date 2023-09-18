package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotterTest {
    static class Potter {
        static double buy(String... input) {
            var discountMap = initializeDiscountMap();
            if (input.length == 1) {
                return 8;
            }
            if (isAUniqueSet(input)) {
                return sumWithoutDiscount(input) * (1 - discountMap.get(input.length));
            }
            return sumWithoutDiscount(input);
        }

        private static Map<Integer, Double> initializeDiscountMap() {
            return Map.of(
                    2, 0.05,
                    3, 0.1,
                    4, 0.2,
                    5, 0.25
            );
        }

        private static int sumWithoutDiscount(String[] input) {
            return input.length * 8;
        }

        private static boolean isAUniqueSet(String[] input) {
            var set = new HashSet<>(Arrays.stream(input).toList());
            return set.size() == input.length;
        }

        public static List<List<String>> findBuckets(String[] input) {
            var booksByType = new HashMap<String, List<String>>();
            for (String book : input) {
                var existingList = booksByType.getOrDefault(book, new ArrayList<>());
                existingList.add(book);
                booksByType.put(book, existingList);
            }
            List<List<String>> mainList = new ArrayList<>();
            for (var entry : booksByType.entrySet()) {
                var list = entry.getValue();
                for (int i = 0; i < list.size(); i++) {
                    if (mainList.size() < i + 1) {
                        mainList.add(i, new ArrayList<>());
                    }
                    var books = mainList.get(i);
                    books.add(list.get(0));
                }
            }
            return mainList;
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

    @Test
    void SingleBook() {
        var actual = Potter.findBuckets(new String[]{"first"});
        var expected = new ArrayList<>(List.of(
                Arrays.asList("first")
        ));
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void TwoBooksSameTypeEndInSeparateBuckets() {
        var actual = Potter.findBuckets(new String[]{"first", "first"});
        var expected = new ArrayList<>(List.of(
                Arrays.asList("first"),
                Arrays.asList("first")
        ));
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
