
import java.util.Arrays;

class InsertElementInArray {
    
    public static int[] insertElement(int[] nums, int indx, int ele){
        int len = nums.length;
        
        if( indx < 0 || indx > len) throw new ArrayIndexOutOfBoundsException("Invalid index ");
        
        int[] newArray = new int[len+1];
        
        for(int i= 0;i<indx; i++){
            
            newArray[i] = nums[i];
            
        }
        
        newArray[indx] = ele;
        
        for(int i = indx; i<len; i++){
            newArray[i+1] = nums[i];
        }
        
        return newArray;
    }
    public static void main(String[] args) {
        
        int[] array = {1,2,3,5,6,7};
        
        int[] output =  insertElement(array,3,4);
        System.out.println(Arrays.toString(output));
    }
}