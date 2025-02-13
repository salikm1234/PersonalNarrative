import org.code.theater.*;
import org.code.media.*;

public class MyStory extends Scene {

  // Stores the title and subtitle text  
  private String[][] variable1;

  // Stores images to be displayed  
  private ImageFilter[][] variable2;
  
  // Constructor to set up the scene  
  public MyStory(String[][] variable1, ImageFilter[][] variable2) {
    this.variable1 = variable1;
    this.variable2 = variable2;
  }

  // Runs all scenes in order  
  public void drawScene() {
    drawExampleScene(); // Placeholder method (not implemented)
    
    drawFirstScene(); // Shows the title
    playNote(60,3);
    drawSecondScene(); // Shows images with random filters
    playNote(80,3);
  }

  // Placeholder method for an example scene  
  public void drawExampleScene() {
    // 1. Clear the scene / draw background  

    // 2. Set any style (text size, font, font style, fill color)  

    // 3. Draw text and images, play sounds  

    // 4. Pause before transitioning  
  }

  // Displays the first scene with title text  
  public void drawFirstScene() {
    clear("white");

    // Show title text with pauses  
    drawText(variable1[0][0], 50, 100);
    pause(0.5);
    drawText(variable1[0][1], 250, 100);
    pause(0.5);
    drawText(variable1[1][0], 50, 300);
    pause(0.5);
    drawText(variable1[1][1], 250, 300);

    pause(1.0);
  }

  // Displays images and applies filters  
  public void drawSecondScene() {
    clear("white");

    // Show all images one by one with pauses  
    drawImage(variable2[0][0], 0, 0, 200);
    pause(0.5);
    drawImage(variable2[0][1], 200, 0, 200);
    pause(0.5);
    drawImage(variable2[1][0], 0, 200, 200);
    pause(0.5);
    drawImage(variable2[1][1], 200, 200, 200);

    pause(1.0);

    // Apply random filters and display their names  
    drawText(doFilter(0,0), 0, 175);
    drawText(doFilter(0,1), 200, 175);
    drawText(doFilter(1,0), 0, 200);
    drawText(doFilter(1,1), 200, 350);

    // Show the images again after filters are applied  
    drawImage(variable2[0][0], 0, 0, 200);
    pause(0.5);
    drawImage(variable2[0][1], 200, 0, 200);
    pause(0.5);
    drawImage(variable2[1][0], 0, 200, 200);
    pause(0.5);
    drawImage(variable2[1][1], 200, 200, 200);
  }

  // Randomly selects and applies a filter to an image  
  public String doFilter(int row, int col) {
    int rand = (int) (Math.random() * 4);

    // List of filter names  
    String[] filtersNames = {"Black and White", "Pixelate", "Colorize", "Blur"};

    // Apply the selected filter  
    if (filtersNames[rand].equals("Black and White")) {
      variable2[row][col].BW();
    }
    if (filtersNames[rand].equals("Pixelate")) {
      variable2[row][col].pixelate(4);
    }
    if (filtersNames[rand].equals("Colorize")) {
      variable2[row][col].colorize();
    }
    if (filtersNames[rand].equals("Blur")) {
      variable2[row][col].applyBlur();
    }

    // Return the filter name to display it on screen  
    return filtersNames[rand]; 
  }

  // other methods
}