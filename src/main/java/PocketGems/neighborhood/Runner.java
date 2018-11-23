package PocketGems.neighborhood;

class Runner {

  public static void main(String[] args) throws Exception {
    // Initialize PDS and IS instances.
    PlaceDataServer pds = new PlaceDataServer();
    pds.initializeServer("place_data.csv");

    ImageServer is = new ImageServer();
    is.initializeServer("image_data.csv"); 

    Neighborhood n = new Neighborhood(pds, is);

    while (true) {
      System.out.println("~~~~~~~~~~~A new Server is running.!!");
      System.out.println("***: " + pds.getPlaceData("Starbucks"));
      System.out.println("***: " + pds.getPlaceData("Starbucks", new LatLng(100, -200)));
      System.out.println("***: " + is.getImageForPlace("Starbucks", new LatLng(100, -200)));
      System.out.println("*** getNearbyPlaces: " + pds.getNearbyPlaces(new LatLng(100, -200), 2));
      System.out.println("*** getPlaces: " + n.getPlaces(new LatLng(100, -200), 1));

     // Feel free to add more printlns here in order to test your code and see
     // how things are working.

      try {
        Thread.sleep(11000);
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("~~~~~~~~~~~Server stops.");
      }
    }
  }
}
