package org.example;

import java.util.Scanner;
import java.util.Random;

public class DiceGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int userScore = 0;
        int computerScore = 0;
        String userChoice;

        while (userScore < 100 && computerScore < 100) {

            System.out.println("Мой счёт: " + userScore);
            System.out.println("Счёт компьютера: " + computerScore);

            System.out.println("Хотите бросить кубик? (yes/no)");
            userChoice = scanner.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                int dice = random.nextInt(6) + 1;
                System.out.println("Вы получили: " + dice);
                userScore += dice;

                if (userScore >= 100) {
                    System.out.println("Поздравляем! Вы победили!");
                }
            }

            if (computerScore < userScore && computerScore < 100) {
                int dice = random.nextInt(6) + 1;
                System.out.println("Очередь компьютера, и он получил: " + dice);
                computerScore += dice;
            }
        }

        if (computerScore >= 100) {
            System.out.println("К сожалению, компьютер победил!");
        }
    }
}