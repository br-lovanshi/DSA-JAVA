import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MostFrequentElement {

    public static void findFrequentElement(int arr[]){

        int count = 0;
        int frequentEle = 0;

        for(int i = 0; i<arr.length; i++){
            int ele = arr[i];
            int eleCount = 0;

            for(int j = 0; j<arr.length; j++){
                if(ele == arr[j]) eleCount++;
            }

            if(count < eleCount){
                count = eleCount;
                frequentEle = ele;
            }
        }

        System.out.println("Frequent Ele " + frequentEle );
    }

    public static void findEle(int [] arr){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        

        int maxEle = 0;
        int eleCount = 0;

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){

            if(entry.getValue() > eleCount){
                eleCount = entry.getValue();
                maxEle = entry.getKey();
            }
        }

        System.out.println("Max ele: " + maxEle);
    }

    // if array is sorted or sort the array first
    public static void findFrequentElement1(int[] arr){
        Arrays.sort(arr);

        int frequencyCount = 1;
        int ele = arr[0];

        int count = 0;
        for(int i = 1; i<arr.length; i++){

            if(arr[i] == arr[i-1]){
                count++;
            }else{
                count = 1;
            }

            if(count > frequencyCount){
                frequencyCount = count;
                ele = arr[i];
            }
        }
        System.out.println("Frequent Ele " + ele );

    }
    public static void main(String[] args) {
        
        int arr[] = {1, 3, 2, 1,4,4,4,4, 4, 1};
        
        findFrequentElement(arr);// time o(n^2), space o(1)
        findFrequentElement1(arr); // time o(n log n), space o(1)
        findEle(arr); // time o(n), space o(n)

    }
}
