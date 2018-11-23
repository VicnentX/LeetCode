package PocketGems.neighborhood;

import java.util.List;

/**
 * Finds the nearest places in the neighborhood.
 */
class Neighborhood {
  private final PlaceDataServer placeServer;
  private final ImageServer imageServer;

  Neighborhood(PlaceDataServer placeServer, ImageServer imageServer) {
    this.placeServer = placeServer;
    this.imageServer = imageServer;
  }

  List<CompletePlaceData> getPlaces(LatLng latLng, int maxResults) {
    // TODO: implement
    return null;
  }
}
