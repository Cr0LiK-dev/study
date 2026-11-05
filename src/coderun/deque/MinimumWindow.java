package coderun.deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MinimumWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        String firstLine = reader.readLine();
        String[] parts = firstLine.trim().split("\\s+");
        StringTokenizer tokenizer = null;
        int var = 0;
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int count = Integer.parseInt(parts[0]);
        int window = Integer.parseInt(parts[1]);;


        while((line = reader.readLine()) !=null){
            tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        for (int i = 0 ; i < count - window ; i++){
            for(int j = i ; j < i + window ; j++){
                if(var == 0){
                    var = Math.min(list.get(j), list.get(j + 1));
                }else{
                    var = Math.min(var, list.get(j + 1));
                }
                
                //minimum 
            }
            result.add(var);
            var = 0;
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
