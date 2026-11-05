package coderun.dpOnGrids;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MaxCostRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line.trim());

        int rows = Integer.parseInt(tokenizer.nextToken());
        int columns = Integer.parseInt(tokenizer.nextToken());
        int arr[][] = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            line = reader.readLine();
            tokenizer = new StringTokenizer(line.trim());
            for(int j = 0; j < columns; j ++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int bestRoute[][] = new int[rows][columns];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j ++){
                if(i == 0 && j == 0){
                    bestRoute[i][j] = arr[i][j];
                }else if(i == 0){
                    bestRoute[i][j] = arr[i][j] + bestRoute[i][j - 1];
                }else if(j == 0){
                    bestRoute[i][j] = arr[i][j] + bestRoute[i - 1][j];
                }else{
                    bestRoute[i][j] = Math.max(arr[i][j] + bestRoute[i - 1][j], arr[i][j] + bestRoute[i][j - 1]);
                }     
            }
        }

        StringBuilder path = new StringBuilder();
        int i = rows - 1, j = columns - 1;
        while (i > 0 || j > 0) {
            if (i == 0) {
                path.append('R');
                j--;
            } else if (j == 0) {
                path.append('D');
                i--;
            } else if (bestRoute[i - 1][j] >= bestRoute[i][j - 1]) {
                path.append('D'); // пришли сверху
                i--;
            } else {
                path.append('R'); // пришли слева
                j--;
            }
        }
        System.out.println(bestRoute[rows-1][columns-1]);
        System.out.println(path);
        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        reader.close();
        writer.close();
    }
}
