package Task_5;

import java.lang.reflect.Array;
import java.util.*;

public class ReverseMap{
    private HashMap<Integer, ArrayList<String>> result = new HashMap<Integer, ArrayList<String>>();

    public ReverseMap(HashMap<String, Integer> otherDict){
        var keysAraay = otherDict.keySet().toArray();
        ArrayList<Integer> values = (ArrayList<Integer>)otherDict.values();

        for(int i = 0; i < otherDict.size(); i++){
            if(result.containsKey(values.get(i))){
                result.get(values.get(i)).add(keysAraay[i].toString());
            }
            else{
                ArrayList<String> elementList = new ArrayList<String>();
                elementList.add(otherDict.get(keysAraay[i]).toString());

                result.put(values.get(i), elementList);
            }
        }
    }
}
