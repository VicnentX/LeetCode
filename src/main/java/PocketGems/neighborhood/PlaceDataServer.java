package PocketGems.neighborhood;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * Place Data Server6 (PDS).
 */
public final class PlaceDataServer {
  HashMap<String, List<PlaceData>> places;

  PlaceDataServer() {
    places = new HashMap<>();
  }

  List<PlaceData> getPlaceData(String placeName) {
    return places.get(placeName);
  }

  PlaceData getPlaceData(String placeName, LatLng latLng) {
    List<PlaceData> placeList = getPlaceData(placeName);
    for (PlaceData place : placeList) {
      if (LatLng.getDistance(latLng, place.getLatLng()) <= 1E-7) {
        return place;
      }
    }
    return null;
  }

  List<PlacePair> getNearbyPlaces(LatLng latLng, int maxResults) {
    // TODO: fix this method
    //ok
    TreeMap<Double , PlacePair> map = new TreeMap<>();
    List<PlacePair> ret = new ArrayList<>();
    for(String k : places.keySet()){
      for(PlaceData pd : getPlaceData(k)){
        map.put(latLng.getDistance(latLng , pd.getLatLng()) , new PlacePair(pd.getPlaceName() , pd.getLatLng()));
      }
    }
    for(int i = 0 ; i < maxResults ; ++i){
      ret.add(map.pollFirstEntry().getValue());
    }
    return ret;
  }

  void initializeServer(String fileName) {
    String line = null;
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null) {
        addPlaceDataToServer(line);
      }

      bufferedReader.close();
    } catch (IOException ioException) {
      System.out.println("Exception thrown populating PDS data: " + ioException);
    }
  }

  private void addPlaceDataToServer(String line) {
    String[] parsedLine = line.split(",");

    List<PlaceData> placeList = places.getOrDefault(parsedLine[0], new ArrayList<PlaceData>());
    placeList.add(
        new PlaceData(
            parsedLine[0],
            Double.parseDouble(parsedLine[1]),
            Double.parseDouble(parsedLine[2]),
            parsedLine[3],
            Integer.parseInt(parsedLine[4]),
            Double.parseDouble(parsedLine[5])));
    places.put(parsedLine[0], placeList);
  }
}