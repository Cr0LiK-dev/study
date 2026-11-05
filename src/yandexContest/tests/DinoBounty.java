package yandexContest.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class DinoBounty {

    static double[] a;
    static double freeBounty;

    static double eval(double lam, int n) {
        double need = freeBounty;
        for (int i = 0; i < n; i++) {
            double f = Math.min(1.0, lam / a[i]);
            need -= f;
        }
        if (need <= 0) return 2 * lam;
        double cost = 0;
        for (int i = 0; i < n && need > 0; i++) {
            if (a[i] <= lam) continue;
            double cap = 1.0 - lam / a[i];
            double t = Math.min(cap, need);
            cost += t * a[i];
            need -= t;
        }
        return 2 * lam + cost;
    }

    public static void main(String[] args){

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line = reader.readLine();
            if (line.trim() == null || line.trim().isEmpty()){
                System.out.println("Empty reader");
                return;
            }
            StringTokenizer tokenizer = new StringTokenizer(line.trim());
            int islands = Integer.parseInt(tokenizer.nextToken());
            int pirats = Integer.parseInt(tokenizer.nextToken());

            freeBounty = islands - pirats;
            a = new double[islands];
            tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < islands; i++) {
                while (!tokenizer.hasMoreTokens()) tokenizer = new StringTokenizer(reader.readLine());
                a[i] = Double.parseDouble(tokenizer.nextToken());
            }

            Arrays.sort(a);
            double lo = 0, hi = a[islands - 1];

            for (int it = 0; it < 200; it++) {
                double m1 = lo + (hi - lo) / 3;
                double m2 = hi - (hi - lo) / 3;
                if (eval(m1,islands) <= eval(m2,islands)) hi = m2;
                else lo = m1;
            }

            System.out.printf(Locale.US, "%.12f%n", eval((lo + hi) / 2, islands));


        }catch(IOException e){
            System.out.println(e);
            return;
        }
    }
}
