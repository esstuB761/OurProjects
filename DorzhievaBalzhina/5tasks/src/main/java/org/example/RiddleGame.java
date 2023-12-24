package org.example;

import java.util.Scanner;

public class RiddleGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 3;

        System.out.println("Что это такое: синий, большой, с усами и полностью набит зайцами?");
        System.out.println("У вас есть 3 попытки.");

        while (attempts > 0) {
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Троллейбус")) {
                System.out.println("Правильно!");
                break;
            } else if (answer.equalsIgnoreCase("Сдаюсь")) {
                System.out.println("Правильный ответ: троллейбус.");
                break;
            } else {
                System.out.println("Подумай еще.");
                attempts--;
                if (attempts > 0) {
                    System.out.println("У вас осталось " + attempts + " попыток.");
                }
                else
                {
                    System.out.println("Попытки закончились");
                }
            }
        }

        System.out.println("Игра завершена.");
    }
}
