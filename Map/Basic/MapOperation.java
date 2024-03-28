package Basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapOperation {

    public static void main(String[] args) {
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        // insert elements inside map
        map.put(10, 1);

        // modify element 
        map.put(99,map.getOrDefault(99, 0)+1);
         int removeKey = map.remove(99);

        // insert using loop
        for(int i = 0; i<10; i++){
            map.put(i, map.getOrDefault(i,0)+1);
        }

        // another way without using getOrDefault method 
        for(int i = 10; i<15; i++){

            
        }

        // basic operations
        int size = map.size();
        boolean isEmpty = map.isEmpty();
      
        // querying operation
        boolean chekContainsKey = map.containsKey(10);
        boolean checkContainsValue = map.containsValue(2);

        // set of keys 
        Set<Integer> keySet = map.keySet();

        // collection of value
        Collection<Integer> valueSet = map.values();

        // key value pair
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();

        // single key value 
        for(Map.Entry<Integer,Integer> entry : entrySet){

            Map.Entry<Integer,Integer> singleKey = entry;
            // get key
            int key = entry.getKey();
            int value = entry.getValue();
        }

        // print map
        System.out.println(map);

    }
    
}
