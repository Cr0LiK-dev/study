import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if(!scan.hasNext()){
            System.out.println("0");
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

