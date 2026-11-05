package coderun.heap;

import java.io.*;
import java.util.*;


public class CommercialCalculator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        long sum = 0;
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int count = Integer.parseInt(tokenizer.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        line = reader.readLine();
        tokenizer = new StringTokenizer(line.trim());
        for(int i = 0; i < count; i++){
            pq.add(Long.parseLong(tokenizer.nextToken()));
        }

        while(pq.size()>1){
            long s = 0;
            s = pq.poll() + pq.poll();
            sum += s;
            pq.add(s);
        }

        long result = sum *5;
        writer.write(String.valueOf(result / 100));
        writer.write(".");
        writer.write( String.format("%02d", result % 100));
       
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}