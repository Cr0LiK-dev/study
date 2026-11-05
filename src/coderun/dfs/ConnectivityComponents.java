package coderun.dfs;

import java.io.*;
import java.util.*;

public class ConnectivityComponents {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        in.nextToken();
        int n = (int) in.nval;
        in.nextToken();
        int m = (int) in.nval;

        // списки смежности на массивах: head[v] -> цепочка рёбер через next[]
        int[] head = new int[n + 1];
        Arrays.fill(head, -1);
        int[] next = new int[2 * m];
        int[] to = new int[2 * m];
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            in.nextToken();
            int a = (int) in.nval;
            in.nextToken();
            int b = (int) in.nval;
            to[cnt] = b; next[cnt] = head[a]; head[a] = cnt++;
            to[cnt] = a; next[cnt] = head[b]; head[b] = cnt++;
        }

        boolean[] visited = new boolean[n + 1];
        int[] stack = new int[n];
        int[] comp = new int[n];
        int components = 0;
        StringBuilder sb = new StringBuilder();

        for (int s = 1; s <= n; s++) {
            if (visited[s]) continue;

            int sp = 0, size = 0;
            stack[sp++] = s;
            visited[s] = true;

            while (sp > 0) {
                int v = stack[--sp];
                comp[size++] = v;
                for (int e = head[v]; e != -1; e = next[e]) {
                    int u = to[e];
                    if (!visited[u]) {
                        visited[u] = true;
                        stack[sp++] = u;
                    }
                }
            }

            components++;
            sb.append(size).append('\n');
            for (int i = 0; i < size; i++) {
                if (i > 0) sb.append(' ');
                sb.append(comp[i]);
            }
            sb.append('\n');
        }

        System.out.println(components);
        System.out.print(sb);
    }
}