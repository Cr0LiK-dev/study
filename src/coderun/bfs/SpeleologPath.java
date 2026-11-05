package coderun.bfs;

import java.io.*;
import java.util.*;


public class SpeleologPath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


        String line = reader.readLine();
        while (line.trim().isEmpty()) line = reader.readLine();
        int n = Integer.parseInt(line.trim());

        char[][][] cave = new char[n][n][];
        int sz = 0, sy = 0, sx = 0;

        for (int z = 0; z < n; z++) {
            for (int y = 0; y < n; y++) {
                line = reader.readLine();
                while (line.trim().isEmpty()) line = reader.readLine();
                cave[z][y] = line.trim().toCharArray();
                for (int x = 0; x < n; x++) {
                    if (cave[z][y][x] == 'S') {
                        sz = z; sy = y; sx = x;
                    }
                }
            }
        }

        int[][][] dist = new int[n][n][n];
        for(int[][] pl : dist)
            for(int[] erth : pl)
                Arrays.fill(erth, -1);

        int[] dz = new int[]{-1, 1, 0 ,0 ,0 ,0};
        int[] dy = new int[]{0, 0, -1, 1, 0 ,0};
        int[] dx = new int[]{0, 0, 0, 0, -1, 1};
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dist[sz][sy][sx] = 0;
        dq.add(new int[]{sz, sy, sx});

        while(!dq.isEmpty()){
            int[] curr = dq.pollFirst();
            int z = curr[0];
            int y = curr[1];
            int x = curr[2];

            if(z == 0){
                writer.write(String.valueOf(dist[z][y][x]));
                reader.close();
                writer.close();
                return;
            }

            for(int k =0;k<6;k++){
                int nz = z + dz[k];
                int ny = y + dy[k];
                int nx = x + dx[k];
                if(nz >= 0 && nz <n && ny >= 0 && ny <n && nx >= 0 && nx <n &&
                    cave[nz][ny][nx] !='#' && dist[nz][ny][nx] == -1){
                        dist[nz][ny][nx] = dist[z][y][x] + 1;
                        dq.add(new int[]{nz, ny, nx});
                    }
            }
        }
        
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}