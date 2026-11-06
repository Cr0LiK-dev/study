package tbank;

import java.io.*;
import java.util.*;

public class Ex4 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int n = Integer.parseInt(tokenizer.nextToken());
        long k = Long.parseLong(tokenizer.nextToken());

        int min = 1, max = n;              // минимальное и максимальное неиспользованные
        StringBuilder result = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            if (k >= i - 1) {
                result.append(max--).append(i > 1 ? ' ' : "");
                k -= (i - 1);
            } else {
                result.append(min++).append(i > 1 ? ' ' : "");
            }
        }
        result.append('\n');
        writer.write(result.toString());
        
        reader.close();
        writer.close();
    }
}
