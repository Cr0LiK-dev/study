package coderun.easy;

import java.io.*;
import java.util.*;

public class MiddleElement {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nums = new int[3];
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());

        for(int i = 0; i < nums.length ; i++){
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(nums);
        System.out.println(nums[1]);

        reader.close();
        writer.close();
    }
}