import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public static double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public static double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0.0;
        FileResource largestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for ( File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if (p > largestPerim){
                largestPerim = p;
            }
            
        }
        
        return largestPerim;
    }

    public static String getFileWithLargestPerimeter() {
         
        double largestPerim = 0.0;
        String name = null;
        
        File largestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if (p > largestPerim){
                largestPerim = p;
                largestFile = f;
            }
        }
        
        return largestFile.getName();
    }
    
    public static void testPerimeterMultipleFiles() {
        System.out.println("Largest perimeter among the files = " + getLargestPerimeterMultipleFiles());
    }

    public static void testFileWithLargestPerimeter() {
        System.out.println("File with largest perimeter = " + getFileWithLargestPerimeter());
    }
    
    public static void main (String[] args) {
       testPerimeterMultipleFiles();
       testFileWithLargestPerimeter();
       
    }

}
