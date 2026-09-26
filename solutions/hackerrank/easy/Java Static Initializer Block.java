import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = null;
        try{
        scan = new Scanner(System.in);
        int B = scan.nextInt();
        int H = scan.nextInt();
        if(B<0 || B>=100 || H>=100 || H<0){
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }else{
            System.out.println(B*H);
            
        }
        }
        
        finally{
          scan.close();
        }
    }
}
