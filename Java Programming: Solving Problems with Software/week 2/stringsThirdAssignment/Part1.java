import edu.duke.*;
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
             
                
      public StorageResource getAllGenes(String dna){
         StorageResource sr = new StorageResource();
         int startIndex = 0;

         while(true){
            
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;        //exit the loop if no gene is found.
            }
            //add gene to sr
            sr.add(currentGene);
            //Update startIndex
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();                                
         }
         return sr;
    
      }
      //Print all genes from sr.
      public void testOn(String dna){
          System.out.println("Testing genes on : " + dna);
          System.out.println("genes: ");
          StorageResource geneList = getAllGenes(dna);
          for ( String g : geneList.data()){
             System.out.println(g); 
          }
          
        }
        
      public static void main(String[] args){
          Part1 gene = new Part1();
         
          gene.testOn("CTATGCTATATTAATCTATGTCTTAGTCTATAAATATGCTATATTGA");
          gene.testOn("CTATGCTATATTAGTCTATGTCTTGATCTATAAATATGCTATATTGATCAACTTGATGCTCTAATC");
          gene.testOn("ACTTGATTCGTATAATAGTC");
                    
        }

}
