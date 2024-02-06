package Searching;

public class LinerSearch {
    
    public static boolean linerSearchOfElement(int[] arr, int k){

        for(int i = 0; i<arr.length; i++){
            if(arr[i] == k){
                return true;
            }
        }

        return false;
    }
    
    // if array is sorted
    public static boolean binarySearch(int[] arr, int k){
       
        int l = 0;
        int r = arr.length-1;

        while(l<=r){

            int m = l + ( r-l)/2;

            if(k == arr[m]) return true;

            if(arr[m] < k) l = m + 1;

            else r = m -1 ;
        }

        return false;
    }
    public static void main(String[] args) {
        
        int arr [] = {4,2,4,6,3,4,2,3,1};

        boolean linerSearch = linerSearchOfElement(arr,43);
        System.out.println(linerSearch);

        int arr1 [] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        boolean binarySearch = binarySearch(arr1, 13);
        System.out.println(binarySearch);
    }
}

//https://www.geeksforgeeks.org/binary-search/?ref=lbp
