package Task_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WithoutDuplicate<T> {
    public List<T> GetCollection(ArrayList<T> values){

        var result = values.stream().distinct().distinct().collect(Collectors.toList());

        return result;
    }
}
