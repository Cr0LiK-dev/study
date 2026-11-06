package tbank;

import java.io.*;
import java.util.*;

public class Ex3 {
     public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int n = Integer.parseInt(tokenizer.nextToken());
        int t = Integer.parseInt(tokenizer.nextToken());

        int rows[] = new int[n];       
        int columns[] = new int[n];    
        int mainDiagonal = 0;          // x == y
        int antiDiagonal = 0;          // x + y == n - 1
        int answer = -1;

        tokenizer = new StringTokenizer(reader.readLine().trim());
        for (int round = 1; round <= t; round++) {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
            }
            int cell = Integer.parseInt(tokenizer.nextToken()) - 1;   // номер клетки с нуля
            int x = cell / n;
            int y = cell % n;

            rows[x]++;
            columns[y]++;
            if (x == y) mainDiagonal++;
            if (x + y == n - 1) antiDiagonal++;

            // выиграть может только линия, в которую попала только что зачёркнутая клетка
            if (rows[x] == n || columns[y] == n || mainDiagonal == n || antiDiagonal == n) {
                answer = round;
                break;
            }
        }

        writer.write(answer + "\n");

        reader.close();
        writer.close();
    }
}
