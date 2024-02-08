package Problems;

class InsertElementInArray {
    
    public static int[] insertElement(int[] nums, int indx, int ele){
        int len = nums.length;
        
        if( indx < 0 || indx > len) throw new ArrayIndexOutOfBoundsException("Invalid index ");
        
        int[] newArray = new int[len+1];
        
        // add ele before indx
        for(int i= 0;i<indx; i++){
            
            newArray[i] = nums[i];
            
        }
        // add ele at the indx 
        newArray[indx] = ele;
        
        // add ele after index 
        for(int i = indx; i<len; i++){
            newArray[i+1] = nums[i];
        }
        
        return newArray;
    }
    public static void main(String[] args) {
        
        int[] array = {1,2,3,5,6,7};
        
        // calling the method 
        int[] output =  insertElement(array,3,4);

        for(int i : output){
            System.out.print( i + " ");
        }
        System.out.println();
    }
}