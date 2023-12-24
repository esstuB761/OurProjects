package org.example;

import java.util.*;

public class SwapMap {
    public static <K, V> Map<V, Collection<K>> swapKeysAndValues(Map<K, V> originalMap) {
        Map<V, Collection<K>> swappedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            V originalValue = entry.getValue();
            K originalKey = entry.getKey();


            swappedMap.computeIfAbsent(originalValue, k -> new ArrayList<>()).add(originalKey);
        }

        return swappedMap;
    }

    public static void main(String[] args) {
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);
        originalMap.put("three", 3);
        originalMap.put("four", 2); // Дубликат значения
        originalMap.put("five",3);
        originalMap.put("six",1);

        System.out.println("Original Map: " + originalMap);

        Map<Integer, Collection<String>> swappedMap = swapKeysAndValues(originalMap);

        System.out.println("Swapped Map: " + swappedMap);
    }
}
