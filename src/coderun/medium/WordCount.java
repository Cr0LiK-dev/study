package coderun.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


/*
She sells sea shells on the sea shore;
The shells that she sells are sea shells I'm sure.
So if she sells sea shells on the sea shore,
I'm sure that the shells are sea shore shells.
 */
public class WordCount {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        
        Set<String> uniqWord = new HashSet<>();
        StringBuilder word = new StringBuilder();
        int chASCII;

        while((chASCII = reader.read()) != -1){
            if (chASCII == 0) break;

            if(Character.isWhitespace(chASCII)){
                if(word.length()>0){
                    uniqWord.add(word.toString());
                    word.setLength(0);
                }
            }else{
                word.append((char) chASCII);
            }
        }
         
        System.out.println(uniqWord.size());


        reader.close();
    }
}