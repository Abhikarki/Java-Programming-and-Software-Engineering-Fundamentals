import java.lang.String;

public class Part3 {
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
          return dna.length(); 
                                    
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
        
      
                
      public int countGenes(String dna){
         //initialize number of genes to 0.
         int geneNumber = 0;
                  
         int startIndex = 0;
                
         while(true){
            
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;        //exit the loop if no gene is found.
            }
            //Update geneNumber.
            geneNumber++;
            /*Update startIndex  -> use dna.indexOf(currentGene,startIndex) instead of just dna.indexOf(currentGene) as
            the genes might have been repeated*/
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();                             
         }
         return geneNumber;

      }
      
     public void testCountGenes(){
         String DNA = "ATGCTAAAATAACTAAATGCTCGTCTAGTACATGCAT";
         
         //print empty line 
         System.out.println("");
          
         System.out.println("Testing genes on : " + DNA);
         System.out.println("The number of genes on " + DNA +" is "+ countGenes(DNA));
         
         //gene with no "ATG"
         DNA = "CATTAACTAGTATAATAG";
         
         //print empty line 
         System.out.println("");
          
         System.out.println("Testing genes on : " + DNA);
         System.out.println("The number of genes on " + DNA + " is "+ countGenes(DNA));
         
         DNA = "CTAATGTAACTATCTATGTATTAGATGTACTAATACATGCTATCTTGATCA";
         
         //print empty line 
         System.out.println("");
          
         System.out.println("Testing genes on : " + DNA);
         System.out.println("The number of genes on " + DNA + " is "+ countGenes(DNA));
         
         //  DNA with repeated gene "ATGCTATAA".
         DNA = "CTATGCTATAACTATGTGATCATGCTATAATC";
         
         //print empty line 
         System.out.println("");
          
         System.out.println("Testing genes on : " + DNA);
         System.out.println("The number of genes on " + DNA + " is "+ countGenes(DNA));
         
         
     }
     
     public static void main(String[] args){
        Part3 test = new Part3();
        test.testCountGenes();
                  
     }
        
}
