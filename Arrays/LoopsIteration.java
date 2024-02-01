public class LoopsIteration {
 public static void main(String[] args) {

    int len = 5;

    for(int i = 0;i<len;i++){
        for(int j = 0;j<=i;j++){
            
            System.out.print("* ");
        }
        System.out.println(" ");
    }

    for(int i = len-1; i>= 0;i--){
        for(int j = i;j<=len;j++){
            System.out.println("* ");
        }
        System.out.println(" ");
    }

    int n = 5;
    for(int i = 0;i<n;i++){
        for(int j = 0;j<n;j++){
            if(i == 0 || i == n-1 || j == 0 || j == n-1)
             System.out.print("*");
             else{
            System.out.print(" ");}
        }
        System.out.println();
    }
 }
}
