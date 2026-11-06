package tbank;

import java.io.*;
import java.util.*;


public class Ex1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int count = Integer.parseInt(tokenizer.nextToken());

        int arr[] = new int[count];
        int total = 0;
        tokenizer = new StringTokenizer(reader.readLine().trim());
        for (int i = 0; i < count; i++) {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
            }
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            total += arr[i];
        }

        boolean answer = false;
        if (total % (count - 1) == 0) {
            int target = total / (count - 1);   // сумма в каждом множестве
            int equal = 0;
            for (int i = 0; i < count; i++) {
                if (arr[i] == target) equal++;
            }
            // n - 2 одиночных множества и одна пара, сумма которой равна target
            answer = (equal == count - 2);
        }

        writer.write(answer ? "YES\n" : "NO\n");

        reader.close();
        writer.close();
    }
}