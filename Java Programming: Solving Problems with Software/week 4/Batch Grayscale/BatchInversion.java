import edu.duke.*;
import java.io.*;

public class BatchInversion {
      public ImageResource makeInversion(ImageResource inImage){
      ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
      
      for (Pixel pixel: outImage.pixels()){
          //look at the correspondin pixel in inImage
          Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
          //compute inPixel's red + inPixel's blue + inPixel's green
          //set pixel's red
          pixel.setRed(255 - inPixel.getRed());
          //set pixel's blue
          pixel.setBlue(255 - inPixel.getBlue());
          //set pixel's green
          pixel.setGreen(255 - inPixel.getGreen());       
      }
      //return the inverted image.
      return outImage;
      }

      public void selectAndConvert(){
          DirectoryResource dr = new DirectoryResource();
          for (File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            //Call makeInversion to get inverted image.
            ImageResource newImage = makeInversion(image);
            String fname = image.getFileName();
            //set new filename.
            String newName = "inverted-" + fname;
            newImage.setFileName(newName);
            //display the inverted image.
            newImage.draw();
            //save image.
            newImage.save();
          }
      }
    
     
}
