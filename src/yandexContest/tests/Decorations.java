package yandexContest.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Decorations {
    public static void main(String[] args){

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line = reader.readLine();
            if (line.trim() == null || line.trim().isEmpty()){
                System.out.println("Empty reader");
                return;
            }

            StringTokenizer tokenizer = new StringTokenizer(line.trim());
            int created = Integer.parseInt(tokenizer.nextToken());
            int needed = Integer.parseInt(tokenizer.nextToken());

            int[] width = new int[created];
            int[] height = new int[created];
            Integer[] index = new Integer[created];
            for (int i = 0; i < created; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                width[i] = Integer.parseInt(tokenizer.nextToken());
                height[i] = Integer.parseInt(tokenizer.nextToken());
                index[i] = i;
            }

            for (Integer i : index) {
                System.out.println(i);
            }
            System.out.println();
            Arrays.sort(index, Comparator.comparingInt(i -> height[i]));

            for (Integer i : index) {
                System.out.println(i);
            }
            System.out.println();

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            long sum = 0;
            long minArea = Long.MAX_VALUE;

            for (int i = 0; i < created; i++) {
                int cur = index[i];
                maxHeap.add(width[cur]);
                sum += width[cur];
                if (maxHeap.size() > needed) {
                    sum -= maxHeap.poll();
                }
                if (maxHeap.size() == needed) {
                    long area = (long) height[cur] * sum;
                    if (area < minArea) minArea = area;
                }
            }

            System.out.println(minArea);

        }catch(IOException e){
            System.out.println(e);
            return;
        }
    }
}
