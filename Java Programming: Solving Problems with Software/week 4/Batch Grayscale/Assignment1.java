
import edu.duke.*;
import java.io.*;

public class Assignment1 {
   public ImageResource makeGray(ImageResource inImage){
     ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    
     for (Pixel pixel: outImage.pixels()){
       //look at the correspondin pixel in inImage
       Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
       //compute inPixel's red + inPixel's blue + inPixel's green
       //divide the sum by 3 to get average
       int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
       //set pixel's red to average
       pixel.setRed(average);
       //set pixel's green to average
       pixel.setGreen(average);
       //set pixel's blue to average
       pixel.setBlue(average);
     }
    return outImage;
   }
   
   public void SelectFiles(){
      DirectoryResource dr = new DirectoryResource();
      for (File F : dr.selectedFiles()){
        ImageResource image = new ImageResource(F);
        //Call makeGray to get grayscale image.
        ImageResource newImage = makeGray(image);
        String fname = image.getFileName();
        //set new filename.
        String newName = "gray-" + fname;
        newImage.setFileName(newName);
        newImage.draw();
        //save image.
        newImage.save();
      }
       
    }
}
