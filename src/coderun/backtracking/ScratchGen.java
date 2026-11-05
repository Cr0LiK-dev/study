package coderun.backtracking;
/*Шаблон, который стоит запомнить: 
«перебрать все варианты, удовлетворяющие правилам» решается рекурсией, 
в которой на каждом шаге проверяются условия допустимости, 
а изменение состояния откатывается при возврате. 
Здесь откат делать не нужно, 
потому что мы просто перезаписываем buf[pos] на следующем шаге. */

import java.io.*;

public class ScratchGen {
    static int n;
    static char[] buf;
    static StringBuilder out = new StringBuilder();

    static void gen(int pos, int open, int close) {
        if (pos == 2 * n) {
            out.append(buf).append('\n');
            return;
        }
        if (open < n) {
            buf[pos] = '(';
            gen(pos + 1, open + 1, close);
        }
        if (close < open) {
            buf[pos] = ')';
            gen(pos + 1, open, close + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine().trim());
        buf = new char[2 * n];
        gen(0, 0, 0);
        System.out.print(out);
    }
}