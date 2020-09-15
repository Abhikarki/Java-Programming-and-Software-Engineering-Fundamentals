
import java.lang.String;

public class Part2 {    
    public float cgRatio(String dna){
        
        int cNumber =  count('C', dna);
        int gNumber =  count('G', dna);
        
        //calculate cgRatio
        float ratio = ((float)cNumber + gNumber) / dna.length();
        return ratio;
       
    }
    
    public int count( char character,String dna){
        int a= 0;
        int b= 0;
        int countNumber = 0;
        while (true){
            a = dna.indexOf(character,b);
            //exit loop if no character found.
            if (a == -1){
                break;
            }
            //update countNumber by 1.
            countNumber++;
            //Update b.
            b = a + 1;
        }
        return countNumber;
    }
    
    
    public int countCTG(String dna){
        int i = 0;
        int j = 0;
        int ctgNumber = 0;
        while (true){
            i = dna.indexOf("CTG",j);
            //exit loop if no "CTG" found.
            if (i == -1){
                break;
            }
            //update ctgNumber by 1.
            ctgNumber++;
            //Update j.
            j = i + 1;
        }
        return ctgNumber;
    }  
    
            
    
    public static void main(String[] args){
        Part2 test = new Part2();
        
        System.out.println("The cgRatio in TATCATGTCTTAGCTATCTGAATCTAGATGTACTCTGAATG is "+ test.cgRatio("TATCATGTCTTAGCTATCTGAATCTAGATGTACTCTGAATG"));
        System.out.println("The cgRatio in TAAGTCTAGTAACTACGTCGTATACTAATTAGTAATG is "+ test.cgRatio("TAAGTCTAGTAACTACGTCGTATACTAATTAGTAATG"));
        System.out.println("The cgRatio in ATGTCATCTGTATGATAATGACTATCTAAGTCTAG is "+ test.cgRatio("ATGTCATCTGTATGATAATGACTATCTAAGTCTAG"));
        System.out.println("The cgRatio in TATTATTAAATAA is "+ test.cgRatio("TATTATTAAATAA"));
        
        System.out.println("");
        
        System.out.println("The number of CTG codon in TATCTGATAACTGAATCTGTAA is "+ test.countCTG("TATCTGATAACTGAATCTGTAA"));
        System.out.println("The number of CTG codon in ATGTGCTAATGA is "+ test.countCTG("ATGTGCTAATGA")); 
        System.out.println("The number of CTG codon in TAACTGTCATCTTCTGTAGCTGCTG is "+ test.countCTG("TAACTGTCATCTTCTGTAGCTGCTG")); 
    }
}
