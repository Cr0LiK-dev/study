package hackerrank.easy;
import java.util.*;

public class JavaStringTokens {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if(!scan.hasNext()){
            System.out.println("0");
            scan.close();
            return;
        }
        String s = scan.nextLine();
        scan.close();
        
        // Write your code here.
        s = s.trim();
        
        String[] arr = s.split("[!,?._'@ ]+");
        System.out.println(arr.length);
        for (String str: arr){
        System.out.println(str);
        }   
        
    }
}

