import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int num = in.nextInt();
       
        String first = str.substring(0, num);
        String last = first;
        
        for (int i = 0; i < str.length() - num+1; i++){
            if(str.substring(i, i + num).compareTo(first)<0){
                first = str.substring(i, i + num);
            }
            if (str.substring(i, i + num).compareTo(last)>0){
                last = str.substring(i, i + num);
            }
        }
        
        System.out.println(first + "\n" + last);
    }
}
