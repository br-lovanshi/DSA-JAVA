package Problems;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicateEle {
    public static int[] usingSet(int[] arr){
        Set<Integer> unique = new HashSet<>();

        for(int i : arr){
            unique.add(i);
        }
        
        int[] newArray = new int[unique.size()];
        int indx = 0;
        for(int element: unique){
            newArray[indx++] = element;
        }

        return newArray;
    }

    public static void Maps(int[] arr){

        Map<Integer, Integer> map = new HashMap<>();

        for(int i : arr){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        
        // basic operations
        int size = map.size();
        boolean isEmpty =  map.isEmpty();

        // modification operations
        map.put(4,map.getOrDefault(4,0)+1);
        map.remove(1);
        
        // query operations
        boolean isContainsKey = map.containsKey(1);
        boolean isContainsValue = map.containsValue(2);
        int getKeysValue = map.get(4);
        Set<Integer> keys = map.keySet();
        Collection<Integer> values = map.values();        
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        
        int maxValue = Integer.MIN_VALUE;
        int maxValueKey = 0;

        for(Map.Entry<Integer,Integer> entry : entries){
            int key = entry.getKey();
            int value = entry.getValue();
            Map.Entry<Integer,Integer> signleEntry = entry;
        }
        
       
    }

    public static int[] removeDuplicateUsingMap(int[] arr){
        
        // create map
        Map<Integer,Integer> map = new HashMap<>();

        // store key in map
        for(int element : arr){
            map.put(element, map.getOrDefault(element,0)+1);
        }

        // get the key set from map
        Set<Integer> keySet = map.keySet();

        // convert set to int[] array
        int[] resultArray = new int[keySet.size()];

        int indx = 0;
        for(int element : keySet){
            resultArray[indx++] = element;
        }

        return resultArray;
    }

    public static void main(String args[]){

       int[] arr = {1, 3, 3, 2, 1, 5, 1, 2, 4, 5};
       int[] resultArray = removeDuplicateUsingMap(arr);
          System.out.println(Arrays.toString(resultArray));
    
       
    }
}