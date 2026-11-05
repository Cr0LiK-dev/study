package yandexContest.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class DinoParty {
     public static void main(String[] args){

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line = reader.readLine();
            if (line.trim() == null || line.trim().isEmpty()){
                System.out.println("Empty reader");
                return;
            }
            StringTokenizer tokenizer = new StringTokenizer(line.trim());
            int row = Integer.parseInt(tokenizer.nextToken());
            int column = Integer.parseInt(tokenizer.nextToken());
            int countS = 0;
            Deque<int[]> dq = new ArrayDeque<>();
            char arr[][] =new char[row][column];
            boolean visited[][] = new boolean[row][column];

            for (int  i = 0; i < row; i++){
                line = reader.readLine();
                for(int j = 0; j < column; j++){
                    arr[i][j] = line.charAt(j);
                    if(line.charAt(j) == 'D'){
                        dq.addLast(new int[]{i, j , 0}); //row colmn distant
                        visited[i][j] = true;
                    }else if(line.charAt(j) == 'S'){
                        countS ++;
                    }
                }
            }

            int invite = 0;
            int result = 0;
            int[] cordX = new int[]{-1, 1, 0 ,0}; //up down left right
            int[] cordY = new int[]{0, 0, -1, 1};

            while(!dq.isEmpty()){
                int[] temp = dq.pollFirst();
                int r = temp[0];
                int c = temp[1];
                int dist = temp[2];

                for(int i = 0; i < 4; i ++){
                    int newR = r + cordX[i];
                    int newC = c + cordY[i];

                    if(newR <0 || newR == row || newC <0 || newC == column){
                        continue;
                    }
                    if(visited[newR][newC]){
                        continue;
                    }
                    if(arr[newR][newC] == '*'){
                        continue;
                    }
                    
                    visited[newR][newC] = true;
                    int newDist = dist +1;

                    if(arr[newR][newC] == 'S'){
                        invite++;
                        result = Math.max(result, newDist);
                    }

                    dq.addLast(new int[]{newR, newC, newDist});
                }
            }

            if (invite < countS){
                System.out.println("-1");
            }else {
                System.out.println(result);
            }
        }catch (IOException e){
            System.out.println(e);
            return;
        }
            
        
     }
}
