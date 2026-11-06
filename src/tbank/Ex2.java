package tbank;

import java.io.*;
import java.util.*;

public class Ex2 {
     public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int count = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, Integer> myMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String word = reader.readLine().trim();
            myMap.merge(word, 1, Integer::sum);
        }

        int best = 0;
        for (int value : myMap.values()) {
            best = Math.max(best, value);
        }

        // порядок обхода HashMap произвольный, поэтому ответ собираем и сортируем отдельно
        ArrayList<String> answer = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
            if (entry.getValue() == best) {
                answer.add(entry.getKey());
            }
        }
        Collections.sort(answer);

        StringBuilder result = new StringBuilder();
        for (String word : answer) {
            result.append(word).append('\n');
        }
        writer.write(result.toString());

        reader.close();
        writer.close();

     }
}
