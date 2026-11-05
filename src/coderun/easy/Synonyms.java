package coderun.easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Synonyms {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int countLines = Integer.parseInt(reader.readLine());
        Map<String, String> dictionary = new HashMap<>();

        for(int i = 0;i < countLines; i++){
            String[] pair = reader.readLine().split("\\s+", 2);
            dictionary.put(pair[0], pair[1]);
        }
        String key = String.valueOf(reader.readLine());
        String result = null;

            if(dictionary.containsKey(key)){
                result = dictionary.get(key);
            }else{
                 for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                    if (entry.getValue().equals(key)) {
                        result = entry.getKey();
                        break;
                    }
                }
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