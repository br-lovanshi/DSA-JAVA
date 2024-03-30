

public class GetNumberFromString{

    public static void findStrNumSum(String str){
        int sum = 0;
        String output = "";

        for(char ch : str.toCharArray()){
            if(Character.isDigit(ch)){
                sum += Character.getNumericValue(ch);
            }else{
                output += ch;
            }
        }

        System.out.println(sum +"\n" +output);

    }
    public static void main(String[] args) {

        String str  = "H2e1l4o";
        findStrNumSum(str);
//        System.out.println(str);
    }
}