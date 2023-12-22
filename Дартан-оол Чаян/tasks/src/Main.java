import java.util.*;

public class Main {
    private static int numberOfStudents;
    private static School.Season currentSeason;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Выводим меню
            System.out.println("Выберите задание:");
            System.out.println("1. Удаление дубликатов");
            System.out.println("2. Измерение времени выполнения");
            System.out.println("3. Работа с классом Школа");
            System.out.println("4. Игра в угадывание");
            System.out.println("5. Замена ключей и значений в Map");
            System.out.println("0. Выход");

            // Читаем выбор пользователя
            int choice = scanner.nextInt();

            // Выполняем выбранное задание или завершаем программу
            switch (choice) {
                case 1:
                    runTask1();
                    break;
                case 2:
                    runTask2();
                    break;
                case 3:
                    runTask3();
                    break;
                case 4:
                    runTask4();
                    break;
                case 5:
                    runTask5();
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите задание от 1 до 5, или 0 для выхода.");
            }
        }
    }

    private static void runTask1() {
        // Задача 1: Удаление дубликатов
        Collection<Integer> inputCollection = Arrays.asList(1, 2, 3, 1, 2, 4, 5);
        Collection<Integer> resultCollection = removeDuplicates(inputCollection);
        System.out.println("Задача 1: Удаление дубликатов");
        System.out.println("Исходная коллекция: " + inputCollection);
        System.out.println("Результат: " + resultCollection);
    }

    private static Collection<Integer> removeDuplicates(Collection<Integer> collection) {
        return new HashSet<>(collection);
    }

    private static void runTask2() {
        // Задача 2: Измерение времени выполнения
        long arrayListTime = measureTimeForList(new ArrayList<>());
        long linkedListTime = measureTimeForList(new LinkedList<>());
        System.out.println("Задача 2: Измерение времени выполнения");
        System.out.println("ArrayList time: " + arrayListTime + " ms");
        System.out.println("LinkedList time: " + linkedListTime + " ms");
    }

    private static long measureTimeForList(List<Integer> list) {
        long startTime = System.currentTimeMillis();

        // Добавление 1000000 элементов
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        // Выбор элементов наугад 100000 раз
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int randomIndex = random.nextInt(1000000);
            list.get(randomIndex);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static void runTask3() {
        // Задача 3: Работа с классом Школа
        School school = new School(666, School.Season.WINTER);
        System.out.println("Задача 3: Работа с классом Школа");
        school.displaySchoolInfo();
    }

    private static void runTask4() {
        // Задача 4: Игра в угадывание
        playRiddleGame();
    }

    private static void playRiddleGame() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 3;

        while (attempts > 0) {
            System.out.println("Что это такое: синий, большой, с усами и полностью набит зайцами?");
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
            }
        }

        if (attempts == 0) {
            System.out.println("Игра завершена. Правильный ответ: троллейбус.");
        }
    }

    private static void runTask5() {
        // Задача 5: Замена ключей и значений в Map
        Map<String, Integer> inputMap = Map.of("A", 1, "B", 2, "C", 1, "D", 3);
        Map<Integer, Collection<String>> result = swapKeysAndValues(inputMap);
        System.out.println("Задача 5: Замена ключей и значений в Map");
        System.out.println("Исходная Map: " + inputMap);
        System.out.println("Результат: " + result);
    }

    private static <K, V> Map<V, Collection<K>> swapKeysAndValues(Map<K, V> inputMap) {
        Map<V, Collection<K>> result = new HashMap<>();

        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();

            result.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }

        return result;
    }
}

class School {
    private int numberOfStudents;
    private Season currentSeason;

    public School(int numberOfStudents, Season currentSeason) {
        this.numberOfStudents = numberOfStudents;
        this.currentSeason = currentSeason;
    }

    public enum Season {
        WINTER, SPRING, SUMMER, AUTUMN
    }

    public void displaySchoolInfo() {
        System.out.println("Школа №888, учащихся " + numberOfStudents + ", сейчас мы "
                + (currentSeason == Season.SUMMER ? "отдыхаем" : "учимся"));
    }
}
