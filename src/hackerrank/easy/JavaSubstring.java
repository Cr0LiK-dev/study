package hackerrank.easy;
import java.util.*;


public class JavaSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        
        System.out.println(str.substring(start, end));
        in.close();
        
        
    }
}
