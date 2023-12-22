package Task_2;

import java.util.ArrayList;
import java.util.LinkedList;

public class GetOneElement {
    private ArrayList<Integer> sumArrayList = new ArrayList<Integer>();
    private LinkedList<Integer> sumLinkList = new LinkedList<Integer>();

    public GetOneElement(){
        for(int i = 0; i< 1000000; i++){
            sumArrayList.add(i);
            sumLinkList.add(i);
        }
    }

    public int getRandomElementLinked()
    {
        return sumArrayList.get((int)Math.random() * 100000);
    }

    public int getRandomElementArray() {
        return sumLinkList.get((int)Math.random() * 100000);
    }
}
