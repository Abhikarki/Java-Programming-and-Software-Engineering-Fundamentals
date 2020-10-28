import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.String;

public class miniProjectBabyNames {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int boysNames = 0;
        int girlsNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)){
            int number = Integer.parseInt(rec.get(2));
            totalBirths += number;
            if (rec.get(1).equals("M")){
                boysNames++;
            }
            
            if (rec.get(1).equals("F")){
                girlsNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total number of boys' names = " + boysNames);
        System.out.println("total number of girls' names = " + girlsNames);
        System.out.println("total number of names in file = " + (boysNames + girlsNames));
        
    }
    
    
    public void testTotalBirths(){
       FileResource fr = new FileResource();
       totalBirths(fr);       
    }
    
    //This method returns the starting line number for boys names.
    public int startNumForBoys(CSVParser parser){
    int boysStartNum = 0;
    for (CSVRecord rec : parser){
            boysStartNum++;
            if (rec.get(1).equals("M")){
                //the loop breaks at boysStartNum == starting rank of boys.
                break;
            }            
        }
        return boysStartNum;
    }
    
    
    public int getRank(int year, String name, String gender){
              
        FileResource fr = new FileResource();
        //To find the starting line number for boys names.
        int boysStartNum = startNumForBoys(fr.getCSVParser(false));
        
        //To keep value of current line 
        int lineNumber = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            lineNumber ++;
            
            String recName = record.get(0);
            String recGender = record.get(1);
            if (gender.equals("F")){
                 if (recName.equals(name) && recGender.equals(gender)){
                     //return lineNumber i.e rank for the female name.
                     return lineNumber ;
                 }
            }
            
            if (gender.equals("M")){
                if (recName.equals(name) && recGender.equals(gender)){
                     //return rank for the male name.
                     return (lineNumber - boysStartNum) + 1 ;
                 }
            }               
        }
        //return -1 if given name and gender donot appear in the file.
        return -1;                                             
    }
    
    
    public void testGetRank(){
        System.out.println("The Rank of Ethan(M) is " + getRank(2012, "Ethan", "M"));       
    }
    
    
    public String getName(int year, int rank, String gender){
               
        FileResource fr = new FileResource();
        
        //To find the starting line number for boys names.
        int boysStartNum = startNumForBoys(fr.getCSVParser(false));
        
        
        //To keep the value of current line number
        int lineNumber = 0;
        
        
        for (CSVRecord record : fr.getCSVParser(false)){
             lineNumber++;
             //gender of the person in current line.
             String currGender = record.get(1);
             
             if (gender.equals("F")){                 
                 if (rank == lineNumber && currGender.equals(gender)){
                 return record.get(0);
                 }
             }
             
             if (gender.equals("M")){                 
                 if (rank == (lineNumber - boysStartNum + 1) && currGender.equals(gender)){
                 return record.get(0);
                 }
             }
        }
        //return "NO NAME" if the rank doesnot exist in file.
        return "NO NAME";        
    } 
    
    
    public void testGetName(){        
       System.out.println("The name of the person at rank 2(M) is " + getName(2012, 2, "M" ));    
    }
    
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year,  name,  gender);
        
        String newName = getName(newYear, rank, gender);
        
        System.out.println( name + " born in " + year + " would be " + newName + " if born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella",2012 ,2013,"F");
    }
    
    
    public int yearOfHighestRank(String name, String gender){
        int highestRank = 0;
        int highestRankYear = 0;
        int rank1 = getRank(2012, name, gender);
        int rank2 = getRank(2013, name, gender);
        int rank3 = getRank(2014, name, gender);
        
        if (rank1 == -1 || (rank2 != -1 && rank2 < rank1)){
            highestRank = rank2;
            highestRankYear = 2013;
        }
        else{
            highestRank = rank1;
            highestRankYear = 2012;
        }
        
        if (highestRank == -1 || (rank3 != -1 && rank3 < highestRank)){
            highestRank = rank3;
            highestRankYear = 2014;
        }
        
        if (highestRank == -1){
        //return -1 if given name and gender donot appear in the file.
        return -1;
        }
        else{
        return highestRankYear;
        }
    }
    
    public void testYearOfHighestRank(){
        String name = "Mason";
        System.out.println(name + " was ranked highest in " + yearOfHighestRank(name, "M"));
    }

}
