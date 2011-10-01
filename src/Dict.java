import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/*
Singeltone dictionary

 */

public class Dict {
    private static Dict ourInstance = new Dict();
    private static HashMap<String,ArrayList<String>> _dict;           // {("777"-["bku","ukb"]),("001"-["eej","een"]),("20"-["re","we"]) }

    public static Dict getInstance() {
        return ourInstance;
    }

    private Dict() {
        _dict = new HashMap<String, ArrayList<String>>(100000,0.8f);  // default for 80 000 words dictionary
    }

    public static boolean initDict(String _fileName){
        try{
            FileInputStream fstream = new FileInputStream(_fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String word, key;
            while ((word = br.readLine()) != null){
                key = getKey(word);
                if(!_dict.containsKey(key))
                    _dict.put(key,(new ArrayList<String>()));
                _dict.get(key).add(word.trim());
                }
            in.close();
        }catch (Exception e){
        return false;
        }
        return true;
    }
    /*
    transform words to numbers

     */
    public static String getKey(String _word){      // space in the middle - error of the dictionary - use memory but do not crush execution!!!
        String key = "";
        key = _word.toLowerCase().trim();
        key = key.replaceAll("[\"-]","");
        key = key.replaceAll("[e]","0");
        key = key.replaceAll("[jnq]","1");
        key = key.replaceAll("[rwx]","2");
        key = key.replaceAll("[dsy]","3");
        key = key.replaceAll("[ft]","4");
        key = key.replaceAll("[am]","5");
        key = key.replaceAll("[civ]","6");
        key = key.replaceAll("[bku]","7");
        key = key.replaceAll("[lop]","8");
        key = key.replaceAll("[ghz]","9");
        return key;
    }
    /*
    return arraylist with words or empty array

     */
    public static ArrayList<String> getWords(String _key){
        if(_dict.containsKey(_key))
            return _dict.get(_key);
        else
            return new ArrayList<String>(1);
    }
}
