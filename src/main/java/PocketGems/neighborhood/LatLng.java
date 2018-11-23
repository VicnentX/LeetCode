package PocketGems.neighborhood;

/**
 * LatLng object.
 * 
 * Note that this design is not accurate to what latitude and
 * longitude definitionally mean, but can serve as a stand-in for a correct
 * implementation.
 */
final class LatLng {

  private final double latitude;
  private final double longitude;

  LatLng(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  double getLatitude() {
    return latitude;
  }

  double getLongitude() {
    return longitude;
  }

  // Computes the euclidean distance between two "LatLng" pairs. This should be an ok approximation
  // for LatLngs that are close enough together.
  static double getDistance(LatLng latLng1, LatLng latLng2) {
    double latDist = Math.abs(latLng2.getLatitude() - latLng1.getLatitude());
    double lngDist = Math.abs(latLng2.getLongitude() - latLng1.getLongitude());
    return Math.sqrt(Math.pow(latDist, 2) + Math.pow(lngDist, 2));
  }

  public String toString() {
    return "(" + latitude + "," + longitude + ")"; 
  }

  public boolean equals(Object o) {
    if (!(o instanceof LatLng)) return false;

    LatLng obj = (LatLng) o;
      return (latitude == obj.getLatitude() && longitude == obj.getLongitude());
  }

  public int hashCode() {
    return (int) (latitude + longitude);
  }
}