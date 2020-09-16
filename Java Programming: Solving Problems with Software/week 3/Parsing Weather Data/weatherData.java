import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.String;
import java.io.File; 


public class weatherData {
    public CSVRecord coldestHourFile(CSVParser parser){
        //start with minRecord as nothing.
        CSVRecord minRecord = null;
        
        for (CSVRecord record : parser){
            //if minRecord is nothing.
            if (minRecord == null){
                minRecord = record;
            }
                        
            else{
                double currTemp = Double.parseDouble(record.get("TemperatureF"));
                double minTemp = Double.parseDouble(minRecord.get("TemperatureF"));
                if (currTemp < minTemp){
                    //Update minRecord
                    minRecord = record;
                }

            }
        }
        return minRecord;
    }
    
    
    public void testColdestHourFile(){
        FileResource fr = new FileResource();
        CSVRecord minRec = coldestHourFile(fr.getCSVParser());
        System.out.println(minRec.get("TimeEST") + "  " + minRec.get("TemperatureF"));
        
    }
    
    public String fileWithColdestTemperature(){
        String name = "";
        CSVRecord minRecord = null;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVRecord currentRow = coldestHourFile(fr.getCSVParser());
           //if minRecord in null.
           if (minRecord == null){
               minRecord = currentRow;
           }
           else{
               double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double minTemp = Double.parseDouble(minRecord.get("TemperatureF"));
                if (currTemp < minTemp){
                    //Update minRecord
                    minRecord = currentRow;
                    name = f.getName();
                }
            }
        }
        //Return name of the file with coldest Day.
        return name;
    }
    
    public void allTemp(CSVParser parser, String coldestDay){
        //Print all the temperature on the coldest Day.
        for(CSVRecord record : parser){
            System.out.println(coldestDay + " " + record.get("TimeEST") + "  " + record.get("TemperatureF"));        
        }
    }
    
    
    
    public void testFileWithColdestTemperature(){
         String coldestDay = fileWithColdestTemperature();
         System.out.println("Coldest day was in file " + coldestDay);
         
         //opens new dialog box to select the file with coldest day.
         FileResource fr = new FileResource();
         CSVRecord rec = coldestHourFile(fr.getCSVParser());
         double min = Double.parseDouble(rec.get("TemperatureF"));
         System.out.println("Coldest temperature on that day was " + min);
         
         System.out.println("All the temperatures on the coldest day were: ");
         //allTemp prints all the temperatures on the coldest day.
         allTemp(fr.getCSVParser(), coldestDay);
                    
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
       CSVRecord minRecord = null;
        
       for (CSVRecord record : parser){
            //if minRecord is nothing.
            if (minRecord == null){
                minRecord = record;
            }
                        
            else{
                if (record.get("Humidity").equals("N/A")){
                    //jump to the next iteration of the loop.
                    continue;
                }
                else{
                double currHumidity = Double.parseDouble(record.get("Humidity"));
                double minHumidity = Double.parseDouble(minRecord.get("Humidity"));
                    if (currHumidity < minHumidity){
                        //Update minRecord
                        minRecord = record;
                    }
                }

            }
       }
       return minRecord;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " on " + csv.get("DateUTC"));
                
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        //Start minRecord as nothing.
        CSVRecord minRecord = null;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
           //if minRecord in null.
           if (minRecord == null){
               minRecord = currentRow;
           }
           else{
               double currHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double minHumidity = Double.parseDouble(minRecord.get("Humidity"));
                if (currHumidity < minHumidity){
                    //Update minRecord
                    minRecord = currentRow;
                }
           }
        }
        return minRecord;        
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " on " + csv.get("DateUTC"));        
        
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double total = 0.0;
        
        for (CSVRecord record : parser){
           total = total + Double.parseDouble(record.get("TemperatureF"));
           count ++;           
        }
        //return average temperature.
        return total / count;
        
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        
       int count = 0;
       double total = 0.0;
       for (CSVRecord record : parser){
            if (record.get("Humidity").equals("N/A")){
                    //jump to the next iteration of the loop.
                    continue;
                }
                
           int humidity = Integer.parseInt(record.get("Humidity"));
           
           if (humidity >= value){
           total = total + Double.parseDouble(record.get("TemperatureF"));
           count ++;  
           }
       }
       if (count == 0){
           //if no temperatures with that humidity.
           return 0.0;
        }
       //return Average Temperature,       
       return total / count; 
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80); 
        if (avgTemp == 0.0){
            System.out.println("No temperatures with that humidity.");
        }
        else{
        System.out.println("Average Temprature when high Humidity is " + avgTemp);
        }
    }
                
}
