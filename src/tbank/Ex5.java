package tbank;

import java.io.*;
import java.util.*;

public class Ex5 {
    static int n;
    static long[] a;

    // сколько элементов <= v
    static int upper(long v) {
        int l = 0, r = n;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] <= v) l = m + 1; else r = m;
        }
        return l;
    }

    // сколько элементов < v
    static int lower(long v) {
        int l = 0, r = n;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < v) l = m + 1; else r = m;
        }
        return l;
    }

    // количество пар i < j, у которых a[i] * a[j] <= x
    static long count(long x) {
        long total = 0, self = 0;
        for (int i = 0; i < n; i++) {
            long v = a[i];
            if (v > 0) {
                total += upper(Math.floorDiv(x, v));              // a_j <= floor(x / v)
            } else if (v < 0) {
                long need = -Math.floorDiv(-x, v);                // ceil(x / v)
                total += n - lower(need);                         // a_j >= ceil(x / v)
            } else {
                if (x >= 0){
                    total += n;
                }
            }
            if (v * v <= x){
                self++;
            }                                
        }
        return (total - self) / 2;                                // каждая пара посчитана дважды
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine().trim());
        n = Integer.parseInt(tokenizer.nextToken());
        long k = Long.parseLong(tokenizer.nextToken());

        a = new long[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            while (!tokenizer.hasMoreTokens()) tokenizer = new StringTokenizer(reader.readLine());
            a[i] = Long.parseLong(tokenizer.nextToken());
        }
        Arrays.sort(a);

        long lo = -2_000_000_000_000_000_000L, hi = 2_000_000_000_000_000_000L;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;                        // floor-середина, работает и для отрицательных
            if (count(mid) >= k){
                hi = mid;
            } 
            else {
                lo = mid + 1;
            }
        }

        writer.write(lo + "\n");

        reader.close();
        writer.close();
    }
}