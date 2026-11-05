package coderun.dpOnSequences;

import java.io.*;



public class NvpAnswer {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        in.nextToken();
        int n = (int) in.nval;

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            in.nextToken();
            a[i] = (int) in.nval;
        }

        int[] dp = new int[n];
        int[] prev = new int[n];
        int bestEnd = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[bestEnd]) {
                bestEnd = i;
            }
        }

        // восстановление: идём от конца цепочки по prev
        int[] res = new int[dp[bestEnd]];
        int k = res.length - 1;
        for (int i = bestEnd; i != -1; i = prev[i]) {
            res[k--] = a[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(res[i]);
        }
        System.out.println(sb);
        writer.close();
    }
}