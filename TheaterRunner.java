import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
  public static void main(String[] args) {

    /*  
     Title text for the animation  
     First row: Main title  
     Second row: Subtitle  
    */
    String[][] array1 = {
      {"My Culture", "in Images"},
      {"(with randomly", " chosen Filters)"}
    };

    /*  
     Images used in the animation  
     First row: Cultural food images  
     Second row: National symbols  
    */
    ImageFilter[][] array2 = {
      { new ImageFilter("chai.jpg"), new ImageFilter("biryani.jpg") },
      { new ImageFilter("greenFlag.jpg"), new ImageFilter("pak.jpeg") },
    };

    // Create the scene using text and images  
    MyStory scene = new MyStory(array1, array2);
    
    // Draw the scene  
    scene.drawScene();
    
    // Play the scene in the Theater  
    Theater.playScenes(scene);
  }
}