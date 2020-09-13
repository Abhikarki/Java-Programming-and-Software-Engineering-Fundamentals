import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
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
    
public int getNumPoints (Shape s) {
        //initialize num to 0.
        int num = 0;
        //for each point upgrade the num by 1.
        for ( Point current : s.getPoints()){
            num = num + 1;
        }
        //return the number of points.
        return num;
    }

    public double getAverageLength(Shape s) {
        double perim = getPerimeter(s);
        int num = getNumPoints(s);
        //calculate the average length of sides.
        double avg = perim / num;
        return avg;
    }

    public double getLargestSide(Shape s) {
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            //update the largest.
            if (currDist > largest){
                largest = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
             
        }
        //return the largest side.
        return largest;
    }

    public double getLargestX(Shape s) {
        double Large = 0.0;
        for ( Point currPt : s.getPoints()){
            //getX() method gets the x coordinate of the point.
            double num = currPt.getX();
            if (num > Large){
                //update the value of Large.
                Large = num;
            }
        
        }
        return Large;
    }
    
public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = " + getNumPoints(s));
        System.out.println("Average length of sides = " + getAverageLength(s));
        System.out.println("Longest side = " + getLargestSide(s));
        System.out.println("Largest X value = " + getLargestX(s));
    }
    
     public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
