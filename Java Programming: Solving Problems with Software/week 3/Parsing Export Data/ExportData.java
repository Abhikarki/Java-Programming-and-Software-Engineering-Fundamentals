import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.String;

public class ExportData {
    
     public void tester(){
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            
            System.out.println(countryInfo(parser, "Rwanda"));
            parser = fr.getCSVParser();   // reset the parser each time you want to use the parser.
            System.out.println(countryInfo(parser, "Malawi"));
            parser = fr.getCSVParser();
            System.out.println(countryInfo(parser, "South Africa"));
            parser = fr.getCSVParser();
            System.out.println(countryInfo(parser, "Russia"));
            
            System.out.println("");
            parser = fr.getCSVParser();
            listExportersTwoProducts(parser, "gold", "diamonds");
            
            System.out.println("");
            parser = fr.getCSVParser();
            System.out.println("The number of countries exporting gold is: " + numberOfExporters(parser, "gold"));
            
            
            System.out.println("");
            parser = fr.getCSVParser();
            bigExporters(parser, "$999,999,999,999");
     }
     
     public String countryInfo(CSVParser parser, String country){
         String result = "NOT FOUND";
         
         for (CSVRecord record : parser){
            String check = record.get("Country");
            //Compare the strings.
            if (check.equals(country)){
                result = check + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
         }
         return result;
     }
     
     public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
                 
        for (CSVRecord record : parser){
            String item = record.get("Exports");
            if (item.contains(exportItem1)){
                if (item.contains(exportItem2)){
                    System.out.println(record.get("Country"));
                }
            }
        } 
     }
     
     public int numberOfExporters(CSVParser parser, String exportItem){
         int number = 0;
         
         for (CSVRecord record : parser){
             String items = record.get("Exports");
             //check for the exportItem
             if (items.contains(exportItem)){
                 //update number.
                number++; 
             }
         }
         return number;
         
     }
        
     public void bigExporters(CSVParser parser, String amount){
         
         int len = amount.length();
         for (CSVRecord record : parser){
             String value = record.get("Value (dollars)");
             //compare length.
             if (value.length() > len){
                 System.out.println(record.get("Country") + " " + value);
             }
             
         }         
                                    
     }
}
