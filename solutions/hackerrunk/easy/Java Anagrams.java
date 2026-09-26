import java.util.Scanner;

public class Solution {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        a = a.toLowerCase();
        b = b.toLowerCase();
        
        if(a.length() != b.length()) return false;
        
        int[] arrA = new int[256];
        int[] arrB = new int[256];
        
        for (int i = 0; i < a.length(); i++){
            arrA[(int) a.charAt(i)] += 1;
            arrB[(int) b.charAt(i)] += 1;
        }

        for(int j = 0; j < 256; j++){  
                if(arrA[j] != arrB[j]){
                    return false;
                }
        }
        return true;
        
        
    }

  public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
