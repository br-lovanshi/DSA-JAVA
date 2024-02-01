public class DeleteElement {
    public static void markAsDelete(int nums[], int indx){

        if(indx >= 0 && indx < nums.length){
            nums[indx] = -1;
        }
    }
    public static void main(String[] args) {
        
        int nums[] = {1,2,3,4,5};
        markAsDelete(nums, 2);

        for(int i :nums){
            if(i != -1){
                System.out.print(i + " ");
            }
        }
        System.out.println();

    }
}
