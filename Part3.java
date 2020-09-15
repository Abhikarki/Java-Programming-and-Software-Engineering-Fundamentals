import edu.duke.*;
import java.lang.String;
 
public class Part3 {
    public void processGenes(StorageResource sr){
       
        int largeStringsNum = 0;       //for number of genes longer than 60 characters
        int longest = 0;               //for length of longest gene
        int cgRatioNum = 0;            //number of genes with cgRatio higher than 0.35
        
        if (sr.size() != 0){          //check if the list is empty.
                                   
            System.out.println("genes longer than 60 characters: ");
            
            for ( String gene : sr.data()){
                
                if (gene.length() > longest){
                    longest = gene.length();          //Update longest 
                }
                
                if (gene.length() > 60){
                   System.out.println(gene);
                   //Update longStringsNum.
                   largeStringsNum++;
                }           
            }
            System.out.println("The number of genes that are longer than 60 characters is : " + largeStringsNum );
            
            
            System.out.println("The genes whose C-G-ratio is higher than 0.35 : " );
            
            for (String gene : sr.data()){
                if (cgRatio(gene) > 0.35){
                    System.out.println(gene);
                    //Update cgRatioNum.
                    cgRatioNum++;
                }
            }
            System.out.println("The number of genes whose C-G-ratio is higher than 0.35 : " + cgRatioNum );
        }
        System.out.println("The length of the longest gene is : " + longest);
    }
    
    
    public void testProcessGenes(){
        
        String DNA ="CTATGTCTTATTAATCATGCTATATTGACTATGTAA";
        System.out.println("DNA : " + DNA);
        StorageResource genes = getAllGenes(DNA);
        processGenes(genes);
        
        System.out.println("");
        
        DNA ="CTATGTAACTATATATGTAGTCA";
        System.out.println("DNA : " + DNA);
        genes = getAllGenes(DNA);
        processGenes(genes);
        
        System.out.println("");
        
        DNA ="CTATGTCCTGAATGTGA";
        System.out.println("DNA : " + DNA);
        genes = getAllGenes(DNA);
        processGenes(genes);
        
        System.out.println("");
        
        DNA ="CTATGTCCTGAATGTGAATGTCTTATTGA";
        System.out.println("DNA : " + DNA);
        genes = getAllGenes(DNA);
        processGenes(genes);
        
        System.out.println("");
        
        DNA ="TAAGTCTAGTAACTACGTCGTATACTAATTAGTAAT";
        System.out.println("DNA : " + DNA);
        genes = getAllGenes(DNA);
        processGenes(genes);
        
        System.out.println("");
        //real DNA
        FileResource fr = new FileResource();     // Select brca1line.fa
        DNA = fr.asString();
        System.out.println("Real DNA ");
        //the file brca1line.fa has all characters in lowercase and hence should be converted to uppercase first.
        DNA = DNA.toUpperCase();         
        genes = getAllGenes(DNA);
        processGenes(genes);
                
    }
    
    
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
         StorageResource geneList = new StorageResource();
         int startIndex = 0;

         while(true){
            
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;        //exit the loop if no gene is found.
            }
            //add gene to geneList
            geneList.add(currentGene);
            //Update startIndex
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();                                
         }
         return geneList;
    
    }  
    
    public static void main(String[] args){
        Part3 obj = new Part3();
        obj.testProcessGenes();
    }
                                
}
