package PocketGems.neighborhood;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Image Server (IS).
 */
final class ImageServer {
  HashMap<PlacePair, String> imageUrls;

  ImageServer() {
  	imageUrls = new HashMap<>();
  }

  String getImageForPlace(String placeName, LatLng latLng) {
  	try {
      Thread.sleep(1100); // DO NOT REMOVE
      // simulates time delay for IS being located far away from our users.
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("~~~~~~~~~~~IS stops.");
    }

    return imageUrls.get(new PlacePair(placeName, latLng));
  }

  void initializeServer(String fileName) {
    String line = null;
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null) {
        addImageToServer(line);
      }

      bufferedReader.close();
    } catch (IOException ioException) {
      System.out.println("Exception thrown populating IS data: " + ioException);
    }
  }

  private void addImageToServer(String line) {
    String[] parsedLine = line.split(",");
    LatLng latLng = new LatLng(
    	Double.parseDouble(parsedLine[1]), Double.parseDouble(parsedLine[2]));
    imageUrls.put(new PlacePair(parsedLine[0], latLng), parsedLine[3]);
  }
}
