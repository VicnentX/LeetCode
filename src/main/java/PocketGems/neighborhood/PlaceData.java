package PocketGems.neighborhood;

/**
 * PlaceData object.
 */
final class PlaceData {
  private final String placeName;
  private final LatLng latLng;

  // a String of the # and street name. eg "1 Main St." 
  private final String address;

  // an int representing the number of dollar signs (1-4)
  private final int priceLevel;

  // rating returns a double representing the number of stars (1-5)
  private final double rating;

  PlaceData(
      String placeName,
      double latitude,
      double longitude,
      String address,
      int priceLevel,
      double rating) {
    this.placeName = placeName;
    this.latLng = new LatLng(latitude, longitude);
    this.address = address;
    this.priceLevel = priceLevel;
    this.rating = rating;
  }

  String getPlaceName() {
    return placeName;
  }

  LatLng getLatLng() {
    return latLng;
  }

  // getAddress returns a String of the # and street name. eg "1 Main St." 
  String getAddress() {
    return address;
  }

  // getPriceLevel returns an int representing the number of dollar signs (1-4)
  int getPriceLevel() {
    return priceLevel;
  }

  // getRating returns an int representing the number of stars (1-5)
  double getRating() {
    return rating;
  }

  public String toString() {
    return "PlaceData {" + placeName + ": " + latLng + "}"; 
  }
}
