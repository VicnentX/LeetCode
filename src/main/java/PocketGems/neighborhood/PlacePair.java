package PocketGems.neighborhood;

/**
 * A data object that holds the placeName and its latlng information.
 */
final class PlacePair {
  private String placeName;
  private LatLng latLng;

  PlacePair(String placeName, LatLng latLng) {
    this.placeName = placeName;
    this.latLng = latLng;
  }

  String getPlaceName() {
    return placeName;
  }

  LatLng getLatLng() {
    return latLng;
  }

  public boolean equals(Object o) {
    if (!(o instanceof PlacePair)) return false;

    PlacePair obj = (PlacePair) o;
    return placeName.equals(obj.getPlaceName()) && latLng.equals(obj.getLatLng());
  }

  public int hashCode() {
    return placeName.hashCode() + latLng.hashCode();
  }

  public String toString() {
    return "PlacePair<" + placeName + "," + latLng + ">";
  }
}