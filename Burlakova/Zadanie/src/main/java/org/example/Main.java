package org.example;

import java.util.*;

class Main {
    public static void main(String[] args) {

        Task4();
    }
    public static void Task1(){
        List<String> a = new ArrayList<>(List.of("aaa", "bbb", "ccc", "aaa", "ddd"));
        System.out.println("Исходная коллекция: " + a);

        Set<String> bset = new HashSet<>(a);
        List<String> c = new ArrayList<>(bset);
        System.out.println("Коллекция без дубликатов: " + c);
    }
    public static void select(List<String> list) {
        Random random = new Random();
        int el = 100000;
        for (int i = 0; i < el; i++) {
            int randomIndex = random.nextInt(list.size());
            String randomElement = list.get(randomIndex);

        }
    }
    public static void Task2(){
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        for (int i = 0; i < 1000000; i++) {
            arrayList.add("Element" + i);
        }

        for (int i = 0; i < 1000000; i++) {
            linkedList.add("Element" + i);
        }
        long startTimeArray = System.currentTimeMillis();
        select(arrayList);
        long endTimeArray = System.currentTimeMillis() - startTimeArray;
        System.out.println("Выбор 100,000 элементов наугад из ArrayList заняло " + endTimeArray + " миллисекунд");

        long startTimeLinked = System.currentTimeMillis();
        select(linkedList);
        long endTimeLinked = System.currentTimeMillis() - startTimeLinked;

        System.out.println("Выбор 100,000 элементов наугад из LinkedList заняло " + endTimeLinked + " миллисекунд");
    }
    public static void Task3() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество учащихся:");
        int colstud = scanner.nextInt();

        System.out.println("Введите номер школы:");
        int number = scanner.nextInt();

        System.out.println("Введите текущий сезон года (зима, весна, лето, осень):");
        String seasonInput = scanner.next();
        School.Season season = School.Season.valueOf(seasonInput.toUpperCase());

        School school = new School(colstud, number, season);
        school.printSchoolInfo();
    }
    public static void Task4() {
        Scanner scanner = new Scanner(System.in);
        int pop = 3;

        while (pop > 0) {
            System.out.println("Что это такое: синий, большой, с усами и полностью набит зайцами?");
            String answ = scanner.nextLine();

            if (answ.equalsIgnoreCase("Троллейбус")) {
                System.out.println("Правильно!");
                break;
            } else if (answ.equalsIgnoreCase("Сдаюсь")) {
                System.out.println("Правильный ответ: троллейбус.");
                break;
            } else {
                System.out.println("Подумай еще.");
                pop--;
            }
        }
        if (pop == 0) {
            System.out.println("Извините, у вас закончились попытки. Правильный ответ: троллейбус.");
        }
    }
    public static <K, V> Map<V, Collection<K>> reverseMap(Map<K, V> inputMap) {
        Map<V, Collection<K>> resultMap = new HashMap<>();

        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();

            // Получаем или создаем коллекцию для данного значения
            Collection<K> keys = resultMap.computeIfAbsent(value, k -> new ArrayList<>());

            // Добавляем ключ в коллекцию
            keys.add(key);

            // Обновляем значение в resultMap
            resultMap.put(value, keys);
        }
        return resultMap;
    }
    public static void Task5() {
        Map<String, Integer> inputMap = new HashMap<>();
        inputMap.put("one", 1);
        inputMap.put("two", 2);
        inputMap.put("three", 3);
        inputMap.put("four", 1); // Пример с одинаковыми значениями

        Map<Integer, Collection<String>> result = reverseMap(inputMap);
        System.out.println(result);
    }

}