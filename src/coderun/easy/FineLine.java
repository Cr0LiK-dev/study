package coderun.easy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FineLine {

    public static long adding(List<Integer> arr, int count) throws IndexOutOfBoundsException{
        long summ = 0;
        for(int i = 0; i < count-1; i++){
            summ =  summ + Math.min(arr.get(i) , arr.get(i+1)) ;
        }
        return summ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


        int count = Integer.parseInt(reader.readLine());
        List<Integer> list = new ArrayList<>();        

        for(int i = 0; i< count; i++){
           list.add(Integer.parseInt(reader.readLine()));
        }

       System.out.println(adding(list,count));


        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        а а а b b b b 
        */

        reader.close();
        writer.close();
    }
}