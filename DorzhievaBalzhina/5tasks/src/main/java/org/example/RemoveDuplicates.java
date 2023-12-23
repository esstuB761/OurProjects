package org.example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RemoveDuplicates {
    public static <T> List<T> removeDuplicates(List<T> collection) {
        Set<T> uniqueSet = new HashSet<>(collection);
        return new ArrayList<>(uniqueSet);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the elements of the collection (enter a non-integer to finish):");
        List<Integer> myList = new ArrayList<>();

        while (scanner.hasNextInt()) {
            int element = scanner.nextInt();
            myList.add(element);
        }

        List<Integer> result = removeDuplicates(myList);
        System.out.println("Collection after removing duplicates: " + result);
    }
}
