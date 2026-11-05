package coderun.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeDemo {
    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();

        // Как очередь
        dq.offerLast(1);
        dq.offerLast(2);
        System.out.println(dq.pollFirst()); // 1

        // Как стек
        dq.push(10); // = offerFirst
        dq.push(20);
        System.out.println(dq.pop());      // 20

        // Двусторонне
        dq.addFirst(100);
        dq.addLast(200);
        System.out.println(dq.peekFirst()); // 100
        System.out.println(dq.peekLast());  // 200
    }
}