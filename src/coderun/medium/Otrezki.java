package coderun.medium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Otrezki {

    static class Segments{
        int first;
        int second;

          public Segments(int first, int second){
            this.first = Math.min(second, first);
            this.second = Math.max(second, first);
         }

    }

    static class Points{
        int value;
        int count = 0;

          public Points(int value){
            this.value = value;
         }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());  
        List<Segments> listSeg = new ArrayList<>();
        List<Points> listPoint = new ArrayList<>();
        

        int segment = Integer.parseInt(tokenizer.nextToken());
        int point = Integer.parseInt(tokenizer.nextToken());

        for(int i = 0; i < segment; i++){
            line = reader.readLine();
            StringTokenizer tokenSeg = new StringTokenizer(line.trim());
            while(!line.isEmpty() && tokenSeg.hasMoreTokens()){
                Segments loopSeg = new Segments(Integer.parseInt(tokenSeg.nextToken()),Integer.parseInt(tokenSeg.nextToken()));
                listSeg.add(loopSeg);
            }
        }

        line = reader.readLine();
        StringTokenizer tokenPoint = new StringTokenizer(line.trim());
         while(!line.isEmpty() && tokenPoint.hasMoreTokens()){
            Points loopPoint = new Points(Integer.parseInt(tokenPoint.nextToken()));
            listPoint.add(loopPoint);
         }

         for(int i = 0; i < segment; i ++){
            for(int j = 0; j < point; j ++){
                if(listPoint.get(j).value >= listSeg.get(i).first && listPoint.get(j).value <= listSeg.get(i).second){
                listPoint.get(j).count ++;
               }  
            }
         }

         for(int i = 0; i < listPoint.size(); i ++){

         }

        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}