package Task_4;

import java.util.Scanner;

public class GuessinGGame {

    public static void getAnswer() {

        String answer;
        boolean exit;
        System.out.println("Что это такое: синий, большой, с усами и полностью набит зайцами?");
        Scanner inputAnswer = new Scanner(System.in);

        exit = false;
        for (int i = 1; i <= 3; i++) {
            if (exit == true)
                break;
            answer = inputAnswer.next();
            switch (answer) {
                case ("Троллейбус"):
                    System.out.println("Правильно!");
                    exit = true;
                    break;
                case ("Сдаюсь"):
                    System.out.println("Правильный ответ: троллейбус.");
                    exit = true;
                    break;
                default:
                    System.out.println("Подумай еще.");
            }
        }
    }

}
