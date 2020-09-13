import java.lang.String;

public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String  result = "";
        String start;
        String stop;
        
        //Check if the codons in dna are uppercase or lowercase
        if (Character.isLowerCase(dna.charAt(0))){
            start = startCodon.toLowerCase();
            stop = stopCodon.toLowerCase();
        } 
        
        else{
            start = startCodon;
            stop = stopCodon;
        }
        
        
        int startIndex = dna.indexOf(start);
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf(stop,startIndex + 3 );
        if (stopIndex == -1){
            return "";
        }
        
        result = dna.substring(startIndex, stopIndex + 3);
        
        int len = result.length();
        //Test if length of substring is multiple of 3
        if (len % 3 == 0){
            return result;
        }
        else{
            return "";
        }
        
    }
    
    public void testSimpleGene(){
        String DNA;
        
        //DNA strand with "ATG" codon and "TAA" codon and substring between them a multiple of 3
        DNA = "ATCATGCTATGATAACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA, "ATG", "TAA"));
        
        
        //DNA strand with "atg" codon and "taa" codon and substring between them a multiple of 3
        DNA = "atcatgctatgataactg";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA, "ATG", "TAA"));
        
        //DNA strand with "ATG" but no "TAA"
        DNA = "ATCCTATTATGACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA, "ATG", "TAA"));
        
        //DNA strand without "ATG" and "TAA"
        DNA = "ATCATTCTATTACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA, "ATG", "TAA"));
        
        //DNA strand with "TAA" but no "ATG"
        DNA = "ATCCTCTATAACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA, "ATG", "TAA"));
        
        //DNA strand with both "ATG" and "TAA" but substring between them not a multiple of 3.
        DNA = "ATCATGCATGATAACTG";
        System.out.println("DNA strand is " + DNA);
        System.out.println("Gene is " + findSimpleGene(DNA, "ATG", "TAA"));
           
    }
}
