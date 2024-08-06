
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class HashMap1 {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] array = { 3, 2, 3, 2, 3 };

        for (int i = 0; i < array.length; i++) {

            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else
                map.put(array[i], 1);
        }
        map.keySet();
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        Collection<Integer> value = map.values();
        System.out.println(value);
        int max = -1;
        int maxOccurence = 0;
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxOccurence = entry.getKey();
            }
        }
//        System.out.println(maxOccurence);
    }

}
