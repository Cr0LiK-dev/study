package coderun.medium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class MaxMultiplication {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        long prevMax= Long.MIN_VALUE ;
        long max = Long.MIN_VALUE;
        long prevMin = Long.MAX_VALUE;
        long min = Long.MAX_VALUE;

        while(tokenizer.hasMoreTokens()){
            long check = 0;
            if((check = Long.parseLong(tokenizer.nextToken())) >= max){
            prevMax = max;
            max = check;
            }else if(check >= prevMax){
                prevMax = check;
            }
                
            if(check <= min){
               prevMin = min;
               min = check; 
            }else if(check <= prevMin){
                prevMin = check;
            }
        }

        if (Math.abs(prevMin * min) > prevMax * max ) {
            if (prevMin < min){
             System.out.println(prevMin + " " + min);
            }else{
                 System.out.println(min + " " + prevMin);
            }          
        }else if (prevMax < max){
             System.out.println(prevMax + " " + max);
            }else{
                 System.out.println(max + " " + prevMax);
            }


        reader.close();
        writer.close();
    }
}