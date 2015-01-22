package nlp;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BasicSpellChecker {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Set<String> dict = generateCorpus();
        //System.out.println(dict);
        Scanner scanObj = new Scanner(System.in);
        int N = Integer.parseInt(scanObj.nextLine());
        String input; StringBuffer helperBuffer;
        boolean solutionFound=true;
        for(int l=0;l<N;l++){
            solutionFound=true;
           input = scanObj.nextLine();
            //System.out.println("Now Scanning.."+input);
           //If input is already correct word
           if(dict.contains(input)){
               System.out.println(input);
                solutionFound=false;
           }
            helperBuffer = new StringBuffer(input);
               //Remove single character from the string
               if(solutionFound){
                    for(int i=0;i<input.length();i++){
                        helperBuffer.deleteCharAt(i);
                        if(dict.contains(helperBuffer.toString())){
                            System.out.println(helperBuffer.toString());
                            solutionFound=false;
                            break;
                        }
                        helperBuffer = new StringBuffer(input);
                    }
               }
               //Try swapping characters in the string
               if(solutionFound){
                   char temp;
                   for(int i=0;i<input.length()-1;i++){
                       for(int j=i+1;j<input.length();j++){
                           temp=helperBuffer.charAt(i);
                           helperBuffer.setCharAt(i,helperBuffer.charAt(j));
                           helperBuffer.setCharAt(j,temp);
                           if(dict.contains(helperBuffer.toString())){
                                System.out.println(helperBuffer.toString());
                                solutionFound=false;
                                break;
                            }
                           helperBuffer = new StringBuffer(input);
                       }
                       if(!solutionFound)break;
                   }
               }
              //Try replacing individual characters in the string
               if(solutionFound){
                   for(int i=0;i<input.length()-1;i++){
                       for(int j=97;j<123;j++){
                          helperBuffer.setCharAt(i,(char)j);
                           if(dict.contains(helperBuffer.toString())){
                            System.out.println(helperBuffer.toString());
                            solutionFound=false;
                            break;
                         }  
                       }
                       helperBuffer = new StringBuffer(input);
                       if(!solutionFound)break;
                   }
               }
              //Append additional character at
              if(solutionFound){
                   for(int i=0;i<input.length()-1;i++){
                       for(int j=97;j<123;j++){
                          helperBuffer.insert(i,(char)j);
                           if(dict.contains(helperBuffer.toString())){
                            System.out.println(helperBuffer.toString());
                            solutionFound=false;
                            break;
                         }  
                       }
                       helperBuffer = new StringBuffer(input);
                       if(!solutionFound)break;
                   }
               }    
             if(solutionFound){
                 System.out.println(helperBuffer.toString());
             }
        }    
    }
    
    public static Set<String> generateCorpus(){
        Set<String> dict = new HashSet<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("corpus.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[^a-zA-Z\\s]","").toLowerCase();
                dict.addAll(Arrays.asList(line.split(" ")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dict;
    }
    
}