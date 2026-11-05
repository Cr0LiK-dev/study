package coderun.dpOnSequences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class OneOneOne {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        if (n == 0) {
            writer.write(String.valueOf(1));
            return;
        }

        long a = 1; // строки, оканчивающиеся на 0
        long b = 1; // строки, оканчивающиеся на одну 1 (...01)
        long c = 0; // строки, оканчивающиеся на две 1 (...011)

            for (int i = 2; i <= n; i++) {
                long newA = a + b + c;
                long newB = a;
                long newC = b;

                a = newA;
                b = newB;
                c = newC;
            }
            writer.write(String.valueOf(a + b + c));
        

        
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}