
// given an array and the element you need to find this ele in the array or remove it or return the new len of the array which do not have that ele.
class RemoveKElements{

	public static int removeEle(int[]arr, int n, int ele){

		int indx = 0;
		for(int i = 0;i<n;i++){
			if(arr[i] != ele){
				arr[indx] = arr[i];
				indx++;
			}
	    }
		
		return indx;
	}


	public static void main(String[] args){
		
	
			int[] arr = {2,3,3,2};
			int n = arr.length;
			
			int ele = 2;
			int indx = removeEle(arr,n,ele);
			for(int i = 0;i<indx;i++){
				System.out.print(arr[i] + " " );
			}
			System.out.println();

	}
}
