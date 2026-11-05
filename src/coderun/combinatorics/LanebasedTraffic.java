package coderun.combinatorics;

import java.io.*;
import java.util.*;


public class LanebasedTraffic {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int m = Integer.parseInt(tokenizer.nextToken());
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine().trim());
        }
        int n = Integer.parseInt(tokenizer.nextToken());

        // dp[r]: сколько способов, если последняя рассмотренная полоса заканчивается на направлении r
        long dp[] = new long[m + 1];
        for (int r = 1; r <= m; r++) {
            dp[r] = 1;                       // первая полоса: [1, r]
        }

        for (int lane = 2; lane <= n; lane++) {
            long next[] = new long[m + 1];
            long prefix = 0;                 // dp[1] + ... + dp[r - 1]
            for (int r = 1; r <= m; r++) {
                // новая полоса начинается в старом конце (общее направление)
                // либо в следующем направлении, и заканчивается в r
                next[r] = (prefix + dp[r]) + prefix;
                prefix += dp[r];
            }
            dp = next;
        }

        writer.write(dp[m] + "\n");

        reader.close();
        writer.close();
    }
}