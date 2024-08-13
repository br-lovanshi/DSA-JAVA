package BasicOperation;

import java.util.ArrayList;
import java.util.List;

public class ArrayIteration {

    public static void main(String[] args) {

        int[] array = new int[30];
        
        int i = 0;
        int num = 2;
        
        while( i < array.length){
           if(prime(num)){
            
               array[i] = num;
               i++;
           }
            num++;
        };

        for(int number : array){
            System.out.println(number);
        }
    }

    public static boolean prime(int num){

        for(int i = 2; i<Math.sqrt(num); i++){
            if(num %i == 0) return false;
        }

        return true;
    }
    public List<Integer> someFUn(final List<Integer> numbers ){
        List<Integer> resulet = new ArrayList<>();
        for(int i = numbers.size()-1; i>=0; i--){
            resulet.add(numbers.get(i));
        }
        return resulet;
    }
}


// given an array store only prime numbers and retrive the array