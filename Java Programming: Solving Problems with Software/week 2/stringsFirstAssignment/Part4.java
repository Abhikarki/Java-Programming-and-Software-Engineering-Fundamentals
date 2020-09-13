import edu.duke.*;
import java.lang.String;

public class Part4 {
    
    int check = 0;
    //declare 'check' and 'YT' to use them on both the methods.        
    String YT = "youtube.com";
    
    public void urlPrint(){
        
        URLResource page = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
            for (String wordOriginal : page.words()){
            //change to lowercase.
            String lowerWord = wordOriginal.toLowerCase();
            
            boolean checked = checkString(lowerWord);
            
            if (checked == true){
                int check1 = lowerWord.lastIndexOf("\"", check);      //use "\"" for quotation mark.
                int check2 = lowerWord.indexOf("\"", check + 1);      
                //find the substring in the original word
                String result = wordOriginal.substring(check1, check2 + 1);
                System.out.println(result);
            }
        }
    }
    
    
    public boolean checkString(String lowerWord){
        
        check = lowerWord.indexOf(YT);
        
        if (check != -1){
            return true;
        }
        return false;
        
    }
    
    public static void main(String[] args){
        Part4 show = new Part4();
        show.urlPrint();
        
    }
}


