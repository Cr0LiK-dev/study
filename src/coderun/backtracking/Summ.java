package coderun.backtracking;

import java.io.*;

public class Summ {
    static int[] terms = new int[40];
    static StringBuilder out = new StringBuilder();

    static void gen(int depth, int remaining, int max) {
        if (remaining == 0) {
            for (int i = 0; i < depth; i++) {
                if (i > 0) out.append(" + ");
                out.append(terms[i]);
            }
            out.append('\n');
            return;
        }
        int limit = Math.min(max, remaining);
        for (int k = 1; k <= limit; k++) {
            terms[depth] = k;
            gen(depth + 1, remaining - k, k);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        gen(0, n, n);
        System.out.print(out);
    }
}