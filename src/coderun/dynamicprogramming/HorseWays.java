package coderun.dynamicprogramming;

import java.io.*;
import java.util.*;


public class HorseWays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int rows = Integer.parseInt(tokenizer.nextToken());
        int columns = Integer.parseInt(tokenizer.nextToken());
        long [][] ways = new long[rows][columns];
        ways[0][0] = 1;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(i ==0 && j==0){
                    continue;
                }
                long check = 0;
                if(i >= 2 && j >= 1){
                    check += ways[i - 2][j-1];
                }
                if(j >= 2 && i >= 1){
                    check += ways[i- 1][j-2];
                }
                ways[i][j] = check;
            }
        }
        System.out.println(ways[rows-1][columns-1]);
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}

