package hackerrank.medium;
import java.util.Scanner;

class JavaRegex{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }   
        in.close();

    }
}

//Write your code here
class MyRegex{
            String nums = "(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})";
            String pattern = nums + "." + nums + "." + nums + "." + nums;
        }
