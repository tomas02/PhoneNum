import java.io.*;
import java.util.ArrayList;


public class Solver {

    public static void main ( String args[]){
    /*
    user interface here

     */
        Dict _dict = Dict.getInstance();
        _dict.initDict("dictionary.txt");
        File f = new File("rez.txt");
        if(f.exists()) f.delete();
        readPhonesNumFile("input.txt");
    }



    public static void readPhonesNumFile(String _fileName){
        try{
            FileInputStream fstream = new FileInputStream(_fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String num;
            while ((num = br.readLine()) != null){
                printOutput(num);
            }
            in.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void printOutput(String _num){
        ArrayList<String> words = new ArrayList<String>();
        words = findWord((_num.trim()).replaceAll("[-/]", ""));
        if(words.isEmpty()){
            System.out.printf("\n %s : not found", _num);
            return;
        }

        try{
            Writer outFile = new FileWriter("rez.txt",true);

            for(String word:words)
                outFile.write(_num+" : "+word+"\n");

            outFile.close();
        }catch(Exception e){
            System.out.println("File write error!");
        }

    }

    public static ArrayList<String> findWord(String _num){
        /*
        recursion ending when
        -input is number of 1 digit
        -current number of 2 or more digits that is not key in dictionary
        -next call returned empty result

         */
        String subNum;
        ArrayList<String> subRez1, subRez2 , rez;
        subRez1 = new ArrayList<String>();
        rez = new ArrayList<String>();

        if(_num.length() == 1)
        {
        subRez1 = (Dict.getInstance()).getWords(_num);
            if (subRez1.isEmpty()){
                rez.add(_num);
                return rez;
            }
        return subRez1;
        }

        subRez2 = new ArrayList<String>();

        for(int i =1;i < _num.length();i++){
            subNum = _num.substring(0,i);
            subRez1 = (Dict.getInstance()).getWords(subNum);

            if(subRez1.isEmpty() && (subNum.length()> 1))  continue;
            if(subRez1.isEmpty() && subNum.length() == 1) subRez1.add(subNum);

            subRez2 = findWord(_num.substring(i,_num.length()));

            if(subRez2.isEmpty()) continue;

            String t;
            for(String el1 : subRez1){
                for(String el2:subRez2){
                    t = el1+" "+el2;
                    if( !t.matches(".{0,}\\d \\d.{0,}"))     // check for 2 digits
                        rez.add(t);
                }

            }


        }
        rez.addAll((Dict.getInstance()).getWords(_num));
        return rez;
    }
}
