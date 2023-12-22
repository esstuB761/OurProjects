class Bottle { // Класс бутылка
    static private int count = 0; // кол-во всего созданных бутылок
    String name; // Название бутылки
    Bottle (String name) { // Конструктор
        this.name = name;
        count++;
    }
    static int getCount() { // Получить кол-во всего созданных бутылок
        return count;
    } // Метод, который возвращает кол-во всего созданных бутылок

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Bottle))
            return false;
        Bottle b = (Bottle)obj;
        return this.name.equals(b.name);
    }
}