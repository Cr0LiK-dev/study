import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class Solution{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }

    }
}

//Write your code here
class MyRegex{
            String nums = "(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})";
            String pattern = nums + "." + nums + "." + nums + "." + nums;
        }
