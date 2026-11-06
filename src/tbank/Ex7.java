package tbank;

import java.io.*;
import java.util.*;

public class Ex7 {
    static int[][] parent;

    static int find(int[] p, int x) {
        while (p[x] != x) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return x;
    }

    // добавляем ребро веса w с порогом t >= w; возвращаем, на сколько уменьшился ответ
    static int addEdge(int u, int v, int w) {
        int merged = 0;
        for (int t = w; t <= 9; t++) {
            int[] p = parent[t];
            int a = find(p, u), b = find(p, v);
            if (a != b) {
                p[a] = b;
                merged++;
            }
        }
        return merged;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine().trim());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        parent = new int[10][n + 1];
        for (int t = 0; t < 10; t++) {
            for (int i = 0; i <= n; i++) {
                parent[t][i] = i;
            }
        }

        long total = 10L * (n - 1);          
        for (int i = 0; i < n - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            total -= addEdge(a, b, c);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            total -= addEdge(u, v, w);
            result.append(total).append('\n');
        }
        writer.write(result.toString());

        reader.close();
        writer.close();
    }
}