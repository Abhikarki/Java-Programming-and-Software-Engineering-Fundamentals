import java.lang.String;

public class Part1 {
      public int findStopCodon(String dna, int startIndex, String stopCodon){

          int currIndex = dna.indexOf(stopCodon, startIndex + 3);
          
          while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff % 3 == 0){
                return currIndex;
            }
            else{
                //Update currIndex
                currIndex = dna.indexOf(stopCodon,currIndex + 1);
            }
                        
          }
          //return length of the DNA strand if no such stop codon
          return dna.length(); 
                                    
        }
        
        
      public void testFindStopCodon(){
          //DNA with one valid and one invalid stopcodon.
          String DNA = "CTATGCTATCTAATCTATAATA";
          int startIndex = DNA.indexOf("ATG");
          System.out.println(findStopCodon(DNA,startIndex, "TAA"));
          
          //DNA with one valid and one invalid stopcodon.
          DNA = "CTATGCTATCTAATCTATAGTA";
          startIndex = DNA.indexOf("ATG");
          System.out.println(findStopCodon(DNA,startIndex, "TAG"));
          
          //DNA with one valid and one invalid stopcodon.
          DNA = "CTATGCTATCTAATCTATATTGATC";
          startIndex = DNA.indexOf("ATG");
          System.out.println(findStopCodon(DNA,startIndex, "TGA"));
                                                            
        }
        
      public String findGene(String dna, int where){
          int start = dna.indexOf("ATG", where);
          //return empty string if no "ATG" is present
          if (start == -1){
              return "";
            }
            
          int taa = findStopCodon(dna, start, "TAA");
          int tag = findStopCodon(dna, start, "TAG");
          int tga = findStopCodon(dna, start, "TGA");
          //find minimum of taa tag and tga.
          int minIndex = Math.min(taa, Math.min(tag, tga));
          if (minIndex == dna.length()){
              return "";
            }
          
          return dna.substring(start, minIndex + 3);                    
        }
        
      public void testFindGene(){
          
          //DNA with no "ATG".
          String DNA = "TAGTACTATAATCTGATC";
          System.out.println("The DNA is " + DNA);
          System.out.println("gene : " + findGene(DNA,0));
          
          //DNA with one valid stop codon.
          DNA = "CTATGCTATCTAATCTATAATA";
          System.out.println("The DNA is " + DNA);
          System.out.println("gene : " + findGene(DNA,0));
          
          //DNA with multiple valid stop codons.
          DNA = "CTATGTCTTGATCATAGTAATC";
          System.out.println("The DNA is " + DNA);
          System.out.println("gene : " + findGene(DNA,0));
          
          //DNA with no valid stop codon.
          DNA = "CTATGCTATCTAATCT";
          System.out.println("The DNA is " + DNA);
          System.out.println("gene : " + findGene(DNA,0));
          
          //DNA with one invalid and two valid stop codons.
          DNA = "CTATGTCTAATTAGTCTTAACTA";
          System.out.println("The DNA is " + DNA);
          System.out.println("gene : " + findGene(DNA,0));
                    
        }
        
        
      public void printAllGenes(String dna){
          
         //print empty line 
         System.out.println("");
          
         System.out.println("Testing genes on : " + dna);
         System.out.println("genes: "); 
         int startIndex = 0;
                
         while(true){
            
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;        //exit the loop if no gene is found.
            }
            //print the gene.
            System.out.println(currentGene);
            //Update startIndex
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();                                
         }
        
        
      }
        
      public static void main(String[] args){
          Part1 gene = new Part1();
          gene.testFindStopCodon();
          
          //print empty line 
          System.out.println("");
          
          gene.testFindGene();
                        
          gene.printAllGenes("CTATGCTATATTAATCTATGTCTTAGTCTATAAATATGCTATATTGA");
          gene.printAllGenes("CTATGCTATATTAGTCTATGTCTTGATCTATAAATATGCTATATTGATCAACTTGATGCTCTAATC");
          gene.printAllGenes("ACTTGATTCGTATAATAGTC");
        }
                                        
}
