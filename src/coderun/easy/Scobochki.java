package coderun.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Scobochki {
        public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        if (s == null || s.isEmpty()) {
            System.out.println("yes");
            return;
        }

        // Создаём стек через Deque (это и есть наш "стек")
        Deque<Character> stack = new ArrayDeque<>();
        boolean correct = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                // Добавляем открывающую скобку на верх стека
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                // Если закрывающая скобка, а стек пуст — нечего закрывать
                if (stack.isEmpty()) {
                    correct = false;
                    break;
                }

                // Смотрим и убираем верхнюю открывающую скобку
                char top = stack.pop();

                // Проверяем, что скобка правильная пара
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    correct = false;
                    break;
                }
            }
        }

        // Если всё ок и стек пустой — значит, все скобки закрыты
        if (correct && stack.isEmpty()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
