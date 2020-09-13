import java.lang.String;

public class Part3 {
   public boolean twoOccurrences(String stringa, String stringb){
       
       int index = stringb.indexOf(stringa);
       //check if stringa occurs in stringb
       if ( index != -1){
           int index1 = stringb.indexOf(stringa, index+1);
           if (index1 != -1){
               return true;
            }
        }
        //return false in case stringa doesnot occur in stringb or occurs only once.
        return false;
                         
   }
   
   public void testing(){
       System.out.println(twoOccurrences("an", "banana"));
       System.out.println(twoOccurrences("a", "banana"));
       System.out.println(twoOccurrences("mp", "computer"));
                     
       System.out.println("The part of the string after 'an' in banana is " + lastPart("an", "banana"));
       System.out.println("The part of the string after 'a' in camera is " + lastPart("a", "camera"));
       System.out.println("The part of the string after 'in' in reinform is " +lastPart("in", "reinform"));
       System.out.println("The part of the string after 'zoo' in forest is " +lastPart("zoo", "forest"));
                                          
    }
    
    public String lastPart( String stringa, String stringb){
        int index0 = stringb.indexOf(stringa);
        //check if stringa occurs in stringb.
        if (index0 != -1){
            return stringb.substring(index0 + stringa.length(), stringb.length());
        }
        //return stringb if there is no occurrence of stringa in stringb.
        return stringb;
        
    }
    


public static void main(String[] args){
    Part3 test = new Part3();
    test.testing();
    }
}