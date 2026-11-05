package coderun.easy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreeOfOne {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());

        if (n == 0) {
            System.out.println(1);
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

        System.out.println(a + b + c);
    }
}