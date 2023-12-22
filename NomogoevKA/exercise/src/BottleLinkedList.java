import java.util.LinkedList;

public class BottleLinkedList { // Список бутылок
    LinkedList<Bottle> bottleList = new LinkedList<>();

    public void add(Bottle bottle) {
        bottleList.add(bottle);
    }
    public int size() {return bottleList.size();}
    public Bottle get(int i) {return bottleList.get(i);}
}
