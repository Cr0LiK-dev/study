package yandexContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class ListOperations{

    public static void main(String[] args){

        int count = 0;
        StringTokenizer tokenizer;
        List<Integer> list = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            count = Integer.parseInt(reader.readLine());
            for(int i = 0; i < count; i++){
                String line = reader.readLine();
                tokenizer = new StringTokenizer(line.trim());
                
                    int key = Integer.parseInt(tokenizer.nextToken());
                    switch(key){
                        case 1:
                        int index = Integer.parseInt(tokenizer.nextToken());
                        list.add(index, Integer.parseInt(tokenizer.nextToken())); 
                        break;

                        case 2:
                        int result = Integer.parseInt(tokenizer.nextToken());
                        System.out.println(list.get(result - 1));
                        break;

                        case 3:
                        list.remove(Integer.parseInt(tokenizer.nextToken()) - 1);
                        break;

                        default:
                        break;                   
                }
            }
        }catch(IOException e){
            
        }
    }
}