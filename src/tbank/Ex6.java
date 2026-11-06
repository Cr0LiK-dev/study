package tbank;

import java.io.*;
import java.util.*;

public class Ex6 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine().trim());
        int size = 1 << n;

        // c[i * n + (j - 1)] = c_{i, j}, плоский массив вместо int[size][n]
        int[] c = new int[size * n];
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                while (!tokenizer.hasMoreTokens()){
                    tokenizer = new StringTokenizer(reader.readLine());
                } 
                c[i * n + j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        long[] h = new long[size];     // выплаты остальных игроков поддерева при условии, что i его выиграл
        long[] best = new long[size];  // лучший исход блока, если его победитель завершит ровно с d победами

        for (int d = 0; d < n; d++) {
            int block = 1 << d;                       // размер блока на уровне d
            int blocks = size >> d;                   // количество блоков

            for (int b = 0; b < blocks; b++) {
                long m = Long.MIN_VALUE;
                for (int j = b * block; j < (b + 1) * block; j++) {
                    long gain = (d == 0) ? 0 : c[j * n + (d - 1)];
                    m = Math.max(m, h[j] + gain);
                }
                best[b] = m;
            }

            for (int b = 0; b < blocks; b += 2) {     // объединяем пару соседних блоков
                for (int j = b * block; j < (b + 1) * block; j++){
                    h[j] += best[b + 1];
                } 
                for (int j = (b + 1) * block; j < (b + 2) * block; j++){
                    h[j] += best[b];
                } 
            }
        }

        long answer = 0;
        for (int i = 0; i < size; i++) {
            answer = Math.max(answer, h[i] + c[i * n + (n - 1)]);   // чемпион выиграл ровно n матчей
        }

        writer.write(answer + "\n");

        reader.close();
        writer.close();
    }
}