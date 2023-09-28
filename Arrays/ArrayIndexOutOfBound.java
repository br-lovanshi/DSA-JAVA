import java.util.ArrayList;
import java.util.List;

public class ArrayIndexOutOfBound {
    public static void main(String[] args) {
        int year= 1900;
        int testYear = 1900;
        List<Integer> dropdown = new ArrayList<>();
        for(int i = 1;i<=124;i++){
            dropdown.add(year++);
        }

        List<Integer> test = new ArrayList<>();
        for(int i = 1; i<124;i++){
            test.add(testYear++);
        }

        for(int i = 0;i<=test.size();i++){// here exception will occure
            System.out.println(dropdown.get(i) + " - " + test.get(i));
        }
    }
}
    