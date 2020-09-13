import java.lang.String;

public class Part1 {
    
    public String findSimpleGene(String dna){
        String  result = "";
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex + 3 );
        if (stopIndex == -1){
            return "";
        }
        
        result = dna.substring(startIndex, stopIndex + 3);
        
        int len = result.length();
        //Check if length of substring is a multiple of 3.
        if (len % 3 == 0){
            return result;
        }
        else{
            return "";
        }
        
    }
    
    public void testSimpleGene(){
        
        //DNA strand with "ATG" codon and "TAA" codon and substring between them a multiple of 3
        String DNA = "ATCATGCTATGATAACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA));
        
        //DNA strand with "ATG" but no "TAA"
        DNA = "ATCCTATTATGACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA));
        
        //DNA strand without "ATG" and "TAA"
        DNA = "ATCATTCTATTACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA));
        
        //DNA strand with "TAA" but no "ATG"
        DNA = "ATCCTCTATAACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA));
        
        //DNA strand with both "ATG" and "TAA" but substring between them not a multiple of 3.
        DNA = "ATCATGCATGATAACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA));
           
    }
    
}

