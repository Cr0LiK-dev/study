package coderun.medium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class OtrezkiOptimize {

    // Событие на прямой
    
static class Event implements Comparable<Event> {
    int coord;
    int type; // 1: начало, 2: конец (right+1), 3: точка
    int index;

    public Event(int coord, int type, int index) {
        this.coord = coord;
        this.type = type;
        this.index = index;
    }

    @Override
    public int compareTo(Event other) {
        if (this.coord != other.coord) {
            return Integer.compare(this.coord, other.coord);
        }
        // При равных координатах: начала (1) → концы (2) → точки (3)
        return Integer.compare(this.type, other.type);
    }
}

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        if (line == null || line.trim().isEmpty()) return;
        
        StringTokenizer tokenizer = new StringTokenizer(line.trim());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        List<Event> events = new ArrayList<>(2 * n + m);

        // Читаем отрезки и создаём события
        for (int i = 0; i < n; i++) {
            line = reader.readLine();
            while (line != null && line.trim().isEmpty()) { // защита от пустых строк
                line = reader.readLine();
            }
            if (line == null) break;
            
            StringTokenizer t = new StringTokenizer(line.trim());
            int a = Integer.parseInt(t.nextToken());
            int b = Integer.parseInt(t.nextToken());
            
            int left = Math.min(a, b);
            int right = Math.max(a, b);

            // Начало отрезка: +1
            events.add(new Event(left, 1, -1));
            // Конец отрезка: -1. 
            // Так как отрезок замкнутый [left, right], точка right должна считаться покрытой.
            // Поэтому событие "конец" ставим на right + 1.
            events.add(new Event(right + 1, 2, -1));
        }

        // Читаем точки
        line = reader.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line != null) {
            StringTokenizer t = new StringTokenizer(line.trim());
            for (int i = 0; i < m; i++) {
                int x = Integer.parseInt(t.nextToken());
                // Тип 0 для точки. index = i нужен, чтобы потом вывести ответы в правильном порядке
                events.add(new Event(x, 3, i));
            }
        }

        // Сортируем все события
        Collections.sort(events);

        int currentActiveSegments = 0;
        int[] answers = new int[m];

        // Проходим по событиям
        for (Event e : events) {
            if (e.type == 1) {
                // Начало отрезка
                currentActiveSegments++;
            } else if (e.type == 2) {
                // Конец отрезка (на самом деле это right + 1)
                currentActiveSegments--;
            } else {
                // Это точка запроса
                answers[e.index] = currentActiveSegments;
            }
        }

        // Вывод результатов
        for (int i = 0; i < m; i++) {
            writer.write(String.valueOf(answers[i]));
            if (i < m - 1) {
                writer.write(' ');
            }
        }
        writer.newLine();

        reader.close();
        writer.close();
    }
}
