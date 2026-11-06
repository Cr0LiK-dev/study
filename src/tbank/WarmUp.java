package tbank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class WarmUp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        long flag[] = new long[n + 2];          // с запасом под метку r + 1
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine().trim());
            int l = Integer.parseInt(tokenizer.nextToken());
            int r = Integer.parseInt(tokenizer.nextToken());
            long x = Long.parseLong(tokenizer.nextToken());
            flag[l] += x;          // с позиции l добавка начинается
            flag[r + 1] -= x;      // после позиции r заканчивается
        }

        StringBuilder result = new StringBuilder();
        long current = 0;
        for (int i = 1; i <= n; i++) {
            current += flag[i];
            if (i > 1) result.append(' ');
            result.append(current);
        }

        writer.write(result.toString() + "\n");

        reader.close();
        writer.close();
    }
}