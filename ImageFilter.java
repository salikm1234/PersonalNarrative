import org.code.theater.*;
import org.code.media.*;

public class ImageFilter extends ImagePlus {

  // Loads an image file  
  public ImageFilter(String fileName) {
    super(fileName);
  }

  // Applies a pixelation effect  
  public void pixelate(int gridSize) {
    Pixel[][] pixels = getImagePixels();

    // Loop through the image in grid-sized blocks  
    for (int i = 0; i < pixels.length; i += gridSize) {
      for (int j = 0; j < pixels[0].length; j += gridSize) {

        double totalRed = 0;
        double totalGreen = 0;
        double totalBlue = 0;

        // Calculate the average color of the block  
        for (int k = 0; k < gridSize; k++) {
          for (int l = 0; l < gridSize; l++) {
            Pixel currentPixel = pixels[i + k][j + l];
            totalRed += currentPixel.getRed();
            totalGreen += currentPixel.getGreen();
            totalBlue += currentPixel.getBlue();
          }
        }

        int avgRed = (int) (totalRed / (gridSize * gridSize));
        int avgGreen = (int) (totalGreen / (gridSize * gridSize));
        int avgBlue = (int) (totalBlue / (gridSize * gridSize));

        // Apply the average color to the entire block  
        for (int k = 0; k < gridSize; k++) {
          for (int l = 0; l < gridSize; l++) {
            Pixel currentPixel = pixels[i + k][j + l];
            currentPixel.setRed(avgRed);
            currentPixel.setGreen(avgGreen);
            currentPixel.setBlue(avgBlue);
          }
        }
      }
    }
  }

  // Changes pixel colors based on brightness  
  public void colorize() {
    Pixel[][] pixels = getImagePixels();

    // Loop through every pixel  
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {

        Pixel currentPixel = pixels[i][j];

        // Calculate the average brightness  
        int avg = (currentPixel.getRed() + currentPixel.getGreen() + currentPixel.getBlue()) / 3;

        // Change color based on brightness level  
        if (avg < 85) {
          currentPixel.setRed(255);
          currentPixel.setGreen(0);
          currentPixel.setBlue(0);
        } else if (avg < 170) {
          currentPixel.setRed(0);
          currentPixel.setGreen(255);
          currentPixel.setBlue(0);
        } else {
          currentPixel.setRed(0);
          currentPixel.setGreen(0);
          currentPixel.setBlue(255);
        }
      }
    }
  }

  // Applies a blur effect  
  public void applyBlur() {
    Pixel[][] pixels = getImagePixels();

    // Loop through every pixel (excluding edges)  
    for (int i = 1; i < pixels.length - 1; i++) {
      for (int j = 1; j < pixels[0].length - 1; j++) {
        Pixel currentPixel = pixels[i][j];

        // Set the pixel to the weighted average of its neighbors  
        currentPixel.setRed(calcWeightedRed(pixels, i, j));
        currentPixel.setGreen(calcWeightedGreen(pixels, i, j));
        currentPixel.setBlue(calcWeightedBlue(pixels, i, j));
      }
    }
  }

  // Calculates the weighted red value for blur  
  public int calcWeightedRed(Pixel[][] pixels, int row, int col) {
    return (pixels[row - 1][col - 1].getRed() + pixels[row - 1][col].getRed() + pixels[row - 1][col + 1].getRed() +
            pixels[row][col - 1].getRed() + pixels[row][col].getRed() + pixels[row][col + 1].getRed() +
            pixels[row + 1][col - 1].getRed() + pixels[row + 1][col].getRed() + pixels[row + 1][col + 1].getRed()) / 9;
  }

  // Calculates the weighted green value for blur  
  public int calcWeightedGreen(Pixel[][] pixels, int row, int col) {
    return (pixels[row - 1][col - 1].getGreen() + pixels[row - 1][col].getGreen() + pixels[row - 1][col + 1].getGreen() +
            pixels[row][col - 1].getGreen() + pixels[row][col].getGreen() + pixels[row][col + 1].getGreen() +
            pixels[row + 1][col - 1].getGreen() + pixels[row + 1][col].getGreen() + pixels[row + 1][col + 1].getGreen()) / 9;
  }

  // Calculates the weighted blue value for blur  
  public int calcWeightedBlue(Pixel[][] pixels, int row, int col) {
    return (pixels[row - 1][col - 1].getBlue() + pixels[row - 1][col].getBlue() + pixels[row - 1][col + 1].getBlue() +
            pixels[row][col - 1].getBlue() + pixels[row][col].getBlue() + pixels[row][col + 1].getBlue() +
            pixels[row + 1][col - 1].getBlue() + pixels[row + 1][col].getBlue() + pixels[row + 1][col + 1].getBlue()) / 9;
  }

  // Converts the image to pure black and white  
  public void BW() {
    Pixel[][] pixels = getImagePixels();

    // Loop through every pixel  
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {

        Pixel currentPixel = pixels[i][j];

        // Calculate brightness  
        int avg = (currentPixel.getRed() + currentPixel.getGreen() + currentPixel.getBlue()) / 3;

        // Set pixel to black or white based on threshold  
        if (avg < 128) {
          currentPixel.setRed(0);
          currentPixel.setGreen(0);
          currentPixel.setBlue(0);
        } else {
          currentPixel.setRed(255);
          currentPixel.setGreen(255);
          currentPixel.setBlue(255);
        }
      }
    }
  }
}