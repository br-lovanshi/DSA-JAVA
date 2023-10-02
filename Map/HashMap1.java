package Map;

import java.util.Map;
import java.util.HashMap;

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
        int max = -1;
        int maxOccurence = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxOccurence = entry.getKey();
            }
        }
        System.out.println(maxOccurence);
    }

}
