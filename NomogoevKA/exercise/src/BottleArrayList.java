import java.util.ArrayList;
import java.util.List;

public class BottleArrayList { // Список бутылок
    private List<Bottle> bottleList = new ArrayList<>(); // Список бутылок

    public void add(Bottle bottle){ // Метод добавления бутылки в список бутылок
        bottleList.add(bottle);
    }

    public void printAllBottleArrayList() { // Метод вывода списка бутылок
        for (int i = 0; i < bottleList.size(); i++){
            System.out.println("{ " + "name: " + bottleList.get(i).name + " }");
        }
    }

    public int size() {
        return bottleList.size();
    }
    public Bottle get(int i) {
        return bottleList.get(i);
    }
}
