

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class FindTwoDigit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder line = new StringBuilder(reader.readLine());
        Deque<Character> dq = new ArrayDeque<>(line.charAt(0));
        int count=1;
        int result=0;

        for(int i = 1; i < line.length() - 1; i ++){
            if(dq.peekLast() == line.charAt(i)){
                count++;
            }else{
                result = Math.max(result, count);
                count = 1;
            }
            dq.pollFirst();
            dq.addLast(line.charAt(i));
        }  

        System.out.println(result);

        
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}