package coderun.easy;

import java.io.*;
import java.util.*;


public class LengthShortestPath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int count = Integer.parseInt(tokenizer.nextToken());
        int graf[][] = new int[count][count];

        for (int i = 0; i < count; i ++){
            line = reader.readLine();
            tokenizer = new StringTokenizer(line.trim());
            for(int j = 0; j < count; j++){
                graf[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        line = reader.readLine();
        tokenizer = new StringTokenizer(line.trim());
        int start = Integer.parseInt(tokenizer.nextToken()) -1;
        int end = Integer.parseInt(tokenizer.nextToken()) -1;

        int[] dist = new int[count];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);

         while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u = 0; u < count; u++) {
                if (graf[v][u] == 1 && dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    queue.add(u);
                }
            }
        }
       
        System.out.println(dist[end]);
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}
