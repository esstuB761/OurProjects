package org.example;

import java.util.*;

public class Zadaniya {
    public static void main(String[] args) {
        zadanie4();
    }
    //ЗАДАНИЕ 1
    public static void zadanie1() {
        System.out.println("Задание 1: Напишите метод, который на вход получает коллекцию объектов, " +
                "а возвращает коллекцию уже без дубликатов.");
        System.out.println("Демонстрация:");
        Collection<Object> inputCollection = List.of(1, 2, 2, 3, 4, 4, 5, 5, 4, 7, 6, 1, 9, 2, "asdas", "asdas");
        Collection<Object> result = removeDuplicates(inputCollection);
        System.out.println("Коллекция:" + inputCollection);
        System.out.println("Коллекция без дубликатов:" + result);
    }
    //ЗАДАНИЕ 2
    public static void zadanie2() {
        System.out.println("Задание 2: Напишите метод, который добавляет 1000000 элементов в ArrayList и LinkedList. " +
                "Напишите еще один метод, который выбирает из заполненного списка элемент наугад 100000 раз. " +
                "Замерьте время, которое потрачено на это. Сравните результаты и предположите, почему они именно такие.");
        System.out.println("Демонстрация:");
        int size = 1000000;
        int randomAccessCount = 100000;

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        long startTime = System.nanoTime();
        fillList(arrayList, size);
        long fillArrayListTime = System.nanoTime() - startTime;
        System.out.println("ArrayList был заполнен за: " + fillArrayListTime / 1e6 + " миллисекунд");

        startTime = System.nanoTime();
        fillList(linkedList, size);
        long fillLinkedListTime = System.nanoTime() - startTime;
        System.out.println("LinkedList был заполнен за: " + fillLinkedListTime / 1e6 + " миллисекунд");

        startTime = System.nanoTime();
        getRandomElements(arrayList, randomAccessCount);
        long randomAccessArrayListTime = System.nanoTime() - startTime;
        System.out.println("Доступ к данным ArrayList был выполнен за: " + randomAccessArrayListTime / 1e6 + " миллисекунд");

        startTime = System.nanoTime();
        getRandomElements(linkedList, randomAccessCount);
        long randomAccessLinkedListTime = System.nanoTime() - startTime;
        System.out.println("Доступ к данным LinkedList был выполнен за: " + randomAccessLinkedListTime / 1e6 + " миллисекунд");
    }
    //ЗАДАНИЕ 3
    public static void zadanie3() {
        System.out.println("Задание 4: Создайте класс Школа. Поместите в этот класс информацию о количестве учащихся, а также текущее\n" +
                "время года (используя перечисление). Каждое время года должно иметь название на русском языке\n" +
                "(используйте конструктор).");
        System.out.println("Демонстрация:");
        School winterSchool = new School(513, School.Season.Winter);
        School summerSchool = new School(123, School.Season.Summer);

        System.out.println("Зимняя школа:");
        winterSchool.displaySchoolInfo();

        System.out.println("\nЛетняя школа:");
        summerSchool.displaySchoolInfo();
    }
    //ЗАДАНИЕ 4
    public static void zadanie4() {
        System.out.println("Задание 4: Представим, что у нас есть загадка: \"Что это такое: синий, большой, с усами и полностью набит зайцами?\" Ответ: троллейбус. Можно возражать, конечно, какого цвета сейчас троллейбусы - но мы не об этом.\n" +
                "Представим, что Вы загадываете эту загадку пользователю. Создайте программу, которая будет считывать с консоли ответ:\n" +
                "у пользователя есть 3 попытки. После трех ответов программа должна завершиться;\n" +
                "если пользователь вводит \"Троллейбус\", мы выводим в консоль \"Правильно!\" и выходим из цикла;\n" +
                "если пользователь вводит \"Сдаюсь\", мы выводим в консоль \"Правильный ответ: троллейбус.\" и выходим из цикла;\n" +
                "если пользователь вводит любой другой ответ, мы выводим в консоль \"Подумай еще.\" и продолжаем цикл.");
        System.out.println("Демонстрация:");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Что это такое: синий, большой, с усами и полностью набит зайцами?");
        System.out.println("У вас есть 3 попытки. Введите ваш ответ:");

        int attempts = 3;

        do {
            String answer = scanner.nextLine();

            switch (answer.toLowerCase()) {
                case "троллейбус":
                    System.out.println("Правильно!");
                    return;
                case "сдаюсь":
                    System.out.println("Правильный ответ: троллейбус.");
                    return;
                default:
                    if (attempts > 1) {
                        System.out.println("Подумай еще.");
                    } else {
                        System.out.println("Были исчерпаны все попытки");
                    }
                    attempts--;
                    if (attempts > 0) {
                        System.out.println("У вас осталось " + attempts + " попыток. Введите ваш ответ:");
                    }
            }
        } while (attempts > 0);

        scanner.close();
    }
    //ЗАДАНИЕ 5
    public static void zadanie5() {
        System.out.println("Задание 5: Напишите метод, который получает на вход Map<K, V> и возвращает Map, где ключи и значения поменяны местами. Так как значения могут совпадать, то тип значения в Map будет уже не K, а\n" +
                "Collection<K>:\n" +
                "Map<V, Collection<K>>");
        System.out.println("Демонстрация:");
        Map<String, Integer> inputMap = new HashMap<>();
        inputMap.put("Яблоко", 1);
        inputMap.put("Апельсин", 2);
        inputMap.put("Мандарин", 3);
        inputMap.put("Банан", 2);
        inputMap.put("Груша", 3);

        Map<Integer, Collection<String>> result = swapKeysAndValues(inputMap);

        for (Map.Entry<Integer, Collection<String>> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    //ДЛЯ ЗАДАНИЯ 1
    public static <T> List<T> removeDuplicates(Collection<T> collection) {
        Set<T> uniqueSet = new HashSet<>();
        List<T> resultCollection = new ArrayList<>();
        for (T item : collection) {
            if (uniqueSet.add(item)) {
                resultCollection.add(item);
            }
        }

        return resultCollection;
    }
    //ДЛЯ ЗАДАНИЯ 2
    public static void fillList(ArrayList<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
    public static void fillList(LinkedList<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
    public static void getRandomElements(ArrayList<Integer> list, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(list.size());
            list.get(index);
        }
    }
    public static void getRandomElements(LinkedList<Integer> list, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(list.size());
            list.get(index);
        }
    }
    //ДЛЯ ЗАДАНИЯ 3
    public static class School {
        private int numberOfStudents;
        private Season currentSeason;

        public enum Season {
            Winter("Зима"),
            Spring("Весна"),
            Summer("Лето"),
            Autumn("Осень");

            private String russianName;

            Season(String russianName) {
                this.russianName = russianName;
            }

            public String getRussianName() {
                return russianName;
            }
        }

        public School(int numberOfStudents, Season currentSeason) {
            this.numberOfStudents = numberOfStudents;
            this.currentSeason = currentSeason;
        }

        private int generateRandomSchoolNumber() {
            Random random = new Random();
            return 100 + random.nextInt(900);
        }

        public void displaySchoolInfo() {
            String activity = (currentSeason == Season.Summer) ? "отдыхаем" : "учимся";
            System.out.println("Школа №" + generateRandomSchoolNumber() + " учащихся " + numberOfStudents + ", сейчас мы " + activity);
        }
    }
    //ДЛЯ ЗАДАНИЯ 5
    public static <K, V> Map<V, Collection<K>> swapKeysAndValues(Map<K, V> inputMap) {
        Map<V, Collection<K>> result = new HashMap<>();

        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();

            result.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }

        return result;
    }
}