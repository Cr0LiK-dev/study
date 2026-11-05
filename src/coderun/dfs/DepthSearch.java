package coderun.dfs;

import java.io.*;
import java.util.*;

public class DepthSearch {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        in.nextToken();
        int n = (int) in.nval;
        in.nextToken();
        int m = (int) in.nval;

        boolean[][] adj = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            in.nextToken();
            int a = (int) in.nval;
            in.nextToken();
            int b = (int) in.nval;
            adj[a][b] = true;
            adj[b][a] = true;   // граф неориентированный
        }

        boolean[] visited = new boolean[n + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[1] = true;
        queue.add(1);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u = 1; u <= n; u++) {
                if (adj[v][u] && !visited[u]) {
                    visited[u] = true;
                    queue.add(u);
                }
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int v = 1; v <= n; v++) {
            if (visited[v]) {
                if (count > 0) sb.append(' ');
                sb.append(v);
                count++;
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }
}