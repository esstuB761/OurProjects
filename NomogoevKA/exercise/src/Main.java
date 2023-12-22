import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // 1 задание: Напишите метод, который на вход получает коллекцию объектов, а возвращает коллекцию уже без дубликатов.
        /*
        BottleArrayList bottleList = new BottleArrayList(); // Создание списка бутылок

        addBottleArrayList(bottleList,10); // Создаем 10 бутылок в списке бутылок
        bottleList.printAllBottleArrayList(); // Вывод этих 10 бутылок
        System.out.println("----------------Список без дупликата----------------------");

        Bottle one = new Bottle("5"); // Создаем бутылку с именем 4
        bottleList.add(one);
        bottleList.printAllBottleArrayList(); // Вывод всего списка
        System.out.println("-----------------Список с дубликатом---------------------");

        getNoDuplicatesBottleArrayList(bottleList).printAllBottleArrayList(); // Вывод списка без дубликатов (работает)
        System.out.println("-------------------Список без дубликата-------------------");
         */

        // 2 задание: Напишите метод, который добавляет 1000000 элементов в ArrayList и LinkedList.
        // Напишите еще один метод, который выбирает из заполненного списка элемент наугад 100000 раз.
        // Замерьте время, которое потрачено на это. Сравните результаты и предположите, почему они именно такие.

        /*
        BottleArrayList bottleArrayList = new BottleArrayList(); // Создание двух разных листов бутылок
        BottleLinkedList bottleLinkedList = new BottleLinkedList();

        //int randomNum = rn.nextInt(1_000_000);

        addBottleArrayList(bottleArrayList, 100_000);
        addBottleLinkedList(bottleLinkedList,100_000);

        chooseRandomElementList(bottleArrayList,bottleLinkedList,10_000);
         */

        // 3 задание: Создайте класс Школа. Поместите в этот класс информацию о количестве учащихся, а также текущее
        //время года (используя перечисление). Каждое время года должно иметь название на русском языке
        //(используйте конструктор).
        //В зависимости от времени года, выведите информацию о школе следующим образом:
        //Школа №888, учащихся 666, сейчас мы учимся
        //// или если лето
        //Школа №888, учащихся 666, сейчас мы отдыхаем
        /* // РАБОТАЕТ
        School.weatherRuEnum = WeatherRuEnum.Зима;
        School school1 = new School("Школа №1",500);
        school1.PrintStudyOrNotStudy();
        System.out.println("Переход с зимы на лето...");
        School.weatherRuEnum = WeatherRuEnum.Лето;
        school1.PrintStudyOrNotStudy();
         */
    }
    public static void addBottleArrayList(BottleArrayList bottleList, int n) { // Добавляет n бутылок в список бутылок (arrayList)
        for (int i = 1; i <= n; i++) {
            Bottle bottle = new Bottle(String.valueOf(i));
            bottleList.add(bottle);
        }
    }

    public static void addBottleLinkedList(BottleLinkedList bottleList, int n) { // Добавляет n бутылок в спиоск бутылок (linkedList)
        for (int i = 1; i <= n; i++) {
            Bottle bottle = new Bottle(String.valueOf(i));
            bottleList.add(bottle);
        }
    }

    public static BottleArrayList getNoDuplicatesBottleArrayList(BottleArrayList bottlelist) { // Возвращает тот же список, но без дубликатов
        BottleArrayList bottlelist_no_duplicates = new BottleArrayList();
        for (int i = 0; i < bottlelist.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < bottlelist_no_duplicates.size(); j++){
                if (bottlelist.get(i).equals(bottlelist_no_duplicates.get(j))) {
                    flag = true;
                }
            }
            if (flag == false)
                bottlelist_no_duplicates.add(bottlelist.get(i));
        }
        return bottlelist_no_duplicates;
    }

    public static void chooseRandomElementList(BottleArrayList bottleArrayList,BottleLinkedList bottleLinkedList, int n) { // Выборка случайного элемента списков n раз и вывод результата
        Random rn = new Random(); // Создание объекта rn для работы случайных чисел
        int countArrayList = 0;
        int countLinkedList = 0;

        System.out.println("Запуск chooseRandomElementList для arrayList..."); // arrayList
        long startTimeArrayList = System.currentTimeMillis();
        while (countArrayList != n) {
            int randomNum = rn.nextInt(bottleArrayList.size());
            Bottle bottle = new Bottle(String.valueOf(randomNum));
            for (int i = 0; i < bottleArrayList.size(); i++){
                if (bottleArrayList.get(i).equals(bottle)){
                    System.out.println(countArrayList);
                    countArrayList++;
                }
            }
        }
        long endTimeArrayList = System.currentTimeMillis();
        System.out.println("Запуск chooseRandomElementList для linkedList...");  // linkedList (РАБОТАЕТ ОЧЕНЬ ДОЛГО!!!)
        long startTimeLinkedList = System.currentTimeMillis();
        while (countLinkedList != n) {
            int randomNum = rn.nextInt(bottleLinkedList.size());
            Bottle bottle = new Bottle(String.valueOf(randomNum));
            for (int i = 0; i < bottleLinkedList.size(); i++){
                if (bottleLinkedList.get(i).equals(bottle)){
                    System.out.println(countLinkedList);
                    countLinkedList++;
                }
            }
        }
        long endTimeLinkedList = System.currentTimeMillis();

        System.out.println("Время выборки элемента " + String.valueOf(n) + " раз для arrayList составляет: " + String.valueOf(endTimeArrayList - startTimeArrayList) + " мс"); // Вывод результата
        System.out.println("Время выборки элемента " + String.valueOf(n) + " раз для linkedList составляет: " + String.valueOf(endTimeLinkedList - startTimeLinkedList) + " мс");
    }
}