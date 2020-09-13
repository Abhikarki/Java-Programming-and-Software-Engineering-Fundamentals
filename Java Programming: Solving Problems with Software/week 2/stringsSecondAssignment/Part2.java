import java.lang.String;

public class Part2 {
     public int howMany(String stringa, String stringb){
        int appears = 0;       //initialize the number of occurrences to 0
        int m = 0;
        int s = 0;
        while (true ){  
                m = stringb.indexOf(stringa, s);                      
                //exit the loop if no appearance                         
                if (m == -1){                                           
                    break;
                }
                //Update appears.
                appears = appears + 1;
                s = m + stringa.length();         //use s = m + stringa.length() to avoid overlapping.
        
        }
        
        return appears;        
     }
     
     public void testHowMany(){
       System.out.println("The number of times 'an' appears in 'banana' is " + howMany("an", "banana"));  
       System.out.println("The number of times 'zoo' appears in 'forest' is " + howMany("zoo", "forest"));  
       System.out.println("The number of times 'AA' appears in 'ATAAAA' is " + howMany("AA", "ATAAAA"));  
       System.out.println("The number of times 'BB' appears in 'BCBBTBBBBCBB' is " + howMany("BB", "BCBBTBBBBCBB"));  
     }
     
     public static void main(String[] args){
        Part2 test = new Part2();
        test.testHowMany();
     }
}

