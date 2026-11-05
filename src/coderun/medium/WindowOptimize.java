package coderun.medium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class WindowOptimize {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String firstLine = reader.readLine();
        if (firstLine == null || firstLine.trim().isEmpty()) {
            return;
        }
        StringTokenizer firstTokenizer = new StringTokenizer(firstLine);
        int count = Integer.parseInt(firstTokenizer.nextToken());
        int window = Integer.parseInt(firstTokenizer.nextToken());

        List<Integer> list = new ArrayList<>(count);
        while (list.size() < count) {
            String line = reader.readLine();
            if (line == null) break;
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens() && list.size() < count) {
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        // Монотонная очередь: хранит индексы, значения по возрастанию
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < count; i++) {
            // 1. Выкидываем с конца те, кто больше или равен новому элементу
            while (!deque.isEmpty() && list.get(deque.peekLast()) >= list.get(i)) {
                deque.pollLast();
            }
            // Добавляем текущий индекс
            deque.addLast(i);

            // 2. Выкидываем с начала те, кто вышел из окна
            while (deque.peekFirst() < i - window + 1) {
                deque.pollFirst();
            }

            // 3. Как только окно полностью сформировалось — выводим минимум
            if (i >= window - 1) {
                writer.write(String.valueOf(list.get(deque.peekFirst())));
                writer.write(' ');
            }
        }

        writer.newLine();
        reader.close();
        writer.close();
    }
}