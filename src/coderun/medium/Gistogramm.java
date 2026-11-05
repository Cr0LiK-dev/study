package coderun.medium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Gistogramm {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        if (line == null || line.trim().isEmpty()){
            return;
        }
        StringTokenizer tokenizer = new StringTokenizer(line.trim());

        int count = Integer.parseInt(tokenizer.nextToken());
        int[] heights = new int[count];
        int idx = 0;
         while (idx < count) {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
            }
            heights[idx++] = Integer.parseInt(tokenizer.nextToken());
        }

        Deque<Integer> dq = new ArrayDeque<>();
        long result = 0;
        
        
        for(int i = 0; i <= count; i++){
            int temp = (i == count)? 0: heights[i];

            while(!dq.isEmpty() && heights[dq.peekLast()] > temp){
                long height = heights[dq.pollLast()];
                int width = dq.isEmpty()? i : i - dq.peekLast() - 1;
                result = Math.max(result, height * width);
            }
            dq.addLast(i);
            
        }
    // 02 11  24 35 41
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