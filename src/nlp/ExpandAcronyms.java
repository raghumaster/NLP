package nlp;

/*
 * Code Authored by Raghu Immaneni
 * &copy; All rights reserved
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ExpandAcronyms {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String[] commonWordsInAcronmys = {"a","all","an","and","any","are","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","no","nor","not","of","off","on","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","this","those","to","too","up","very","was","what","what's","when","when's","where","where's","which","while","who","whom","why","why's","with","won't","would","wouldn't","you"};
        Set<String> commonWordsSet = new HashSet<>(Arrays.asList(commonWordsInAcronmys));
        Scanner scanObj = new Scanner(System.in);
        int N = Integer.parseInt(scanObj.nextLine());
        List<String> inputList = new ArrayList<>();
        for(int l=0;l<N;l++){
           inputList.addAll(Arrays.asList(scanObj.nextLine().split(" ")));
        }
        String accronym; int j,k,marker=0;
        List<String> output = new ArrayList<>();
        for(int l=0;l<N;l++){
            accronym = scanObj.nextLine();
            output.clear();
            boolean firstFound=false,firstAttempt=true;
            int foundIndex=0,startIndex=0;
            if(firstAttempt){
                for(int i=0;i<inputList.size();i++){
                    if(inputList.get(i).equals("("+accronym+")")){foundIndex=i;break;}
                }
                //System.out.println(foundIndex);
                if(foundIndex>0){
                    for(j=foundIndex-1;j>=0;j--){
                        output.add(inputList.get(j));
                        if(accronym.charAt(0)==inputList.get(j).charAt(0)){
                        break;
                        }
                    }
                    for(k=output.size()-1;k>0;k--){
                        System.out.print(output.get(k)+" ");
                    }
                    System.out.println(output.get(0));
                }
                else{firstAttempt=false;}
            }
            if(!firstAttempt){
               for(int i=0;i<inputList.size();i++){
                j=0;
                output.clear();
                firstFound=false;
                while(j<accronym.length()){
                    if(!firstFound)marker=i;
                        if(inputList.get(marker).charAt(0)==accronym.charAt(j)){
                            output.add(inputList.get(marker));
                            firstFound=true;
                            marker++;j++;
                        }else if(firstFound && commonWordsSet.contains(inputList.get(marker))){
                            output.add(inputList.get(marker));
                            marker++;
                        }
                        else{
                            break;
                        }
                    }
                    if(output.size()>=accronym.length())break;
                }
                for(k=0;k<output.size()-1;k++){
                    System.out.print(output.get(k)+" ");
                }
                 System.out.println(output.get(k));
            }            
            
        }
    }
}