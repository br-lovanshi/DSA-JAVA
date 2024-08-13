package Algorithms.BinarySearch.Problems;

public class Count1SortedBinaryArray {
    
    public static int countOne(int[] arr){

        int count = 0;

        int i = 0;
        while (arr[i] == 1) {
            count++;
            i++;
            
        }
        return count;
    }
        public static void main(String[] args) {
            
            int [] arr = {0,0,1,2,3,4,4,4,5,5,5,15};
            int target = -1;
            int output = findOccurency(arr, target);
            

            System.out.println(output);
        }

        public static int findOccurency(int[] arr, int target) {
            
            int leftCount = findLeftSide(arr,target);
            int rightCount = findRightSide(arr,target);

            if( leftCount == -1 && rightCount == -1) return 0;

            return  rightCount - leftCount  +1;
        }
        public static int findLeftSide(int[] arr, int k){
            
            int l = 0;
            int h = arr.length-1;
            int ans = -1;
            while(l<=h){
                // find middle point
                int m = l + (h-l)/2;
                
                    if(arr[m]== k) {
                        ans = m;
                        h = m-1;
                    }

                    else if(k > arr[m]){
                        l = m+1;
                    }
        
                    else{
                        h = m-1;
                    }
                
            }

            return ans;

        }

        public static int findRightSide(int[] arr, int k){
            int l = 0;
            int h = arr.length-1;
            int ans = -1;
            
            while(l<=h){
                // find middle point
                int m = l+ (h-l)/2;

                if(arr[m]== k) {
                ans = m;
                l = m+1;
                
                }         

               else if(k > arr[m]){
                    l = m+1;
                }

                else{
                    h = m-1;
                }
            }
            return ans;

        }
}

/*Given an array count target ele frequency in the array*/