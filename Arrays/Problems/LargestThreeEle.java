package Problems;
public class LargestThreeEle {
    // Find the largest three distinct elements in an array

    // bubble sort
    public static void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean sorted = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int arr[] = { 10, 4, 3, 50, 23, 90 };
        // Output: 90, 50, 23
        // sort the array
        sort(arr);

        // loop last three ele 
        for(int i = arr.length-1;i>=arr.length-3;i--){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }
}
