package coderun.medium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BeautyLine {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int value = Integer.parseInt(reader.readLine());
        String s = reader.readLine();
        int result = 0;
        
        for(char ch = 'a'; ch <= 'z'; ch++){
            int replacement=0;
            int left=0;

            for(int i = 0; i < s.length(); i++){
                
                if(s.charAt(i) != ch){
                    replacement++;
                }

                while (replacement > value) {
                    if(s.charAt(left) != ch){
                        replacement--;
                    }
                    left++;
                    
                }
                result = Math.max(result, i - left + 1);
            }  
        }

        System.out.println(result);

        

        
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}