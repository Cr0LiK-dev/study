package coderun.bfs;

import java.io.*;
import java.util.*;

public class Fleas {
        public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        in.nextToken(); int n = (int) in.nval;
        in.nextToken(); int m = (int) in.nval;
        in.nextToken(); int s = (int) in.nval - 1;
        in.nextToken(); int t = (int) in.nval - 1;
        in.nextToken(); int q = (int) in.nval;

        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);

        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        dist[s][t] = 0;
        queue.add(new int[]{s, t});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int k = 0; k < 8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        long sum = 0;
        boolean possible = true;
        for (int i = 0; i < q; i++) {
            in.nextToken(); int x = (int) in.nval - 1;
            in.nextToken(); int y = (int) in.nval - 1;
            if (dist[x][y] == -1) {
                possible = false;
            } else {
                sum += dist[x][y];
            }
        }
        writer.write(String.valueOf(possible ? sum : -1));
        writer.close();
    }
}

