package Mathworks.MathworksOOD;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * clarifying questions:
 *      i want to have a complete picture of this business...
 *      1.  do you want me come up with system design
 *          or do you want me come up with class hierarchy
 *          or do you need to get into a certain specific question and then write some methods
 *      2.  can we discuss for more details?
 *          what does a parking lot look like?
 *          is it a building or an open space?
 *          is the parking lots are free? or free at some timeslot?
 *          same size of spot?
 *          how many spots are we talking about?
 *          does this park lots has multiple levels?
 *          does this park lots has more than one entrance? concurrency?
 *          does these slots has different size?
 *          how about its pricing module? premium and regular?
 *
 *      for example
 *          4 size = small , medium, large, xlarge
 *          which car to put into which spot (could put small car into larger spot)
 *
 *          $$$$ START with Vehicle
 *
 *      what kind of goal we have with this? because we want to retrieve the information of car and spot
 *      what kind of data structure we need .....in reality we use DATABASE
 *
 */

public class ParkingLot {
    int zipCode;
    private Map<Vehicle, Spot> vehicleSpotMap = new HashMap<>();
    private Stack<Spot> smallSpots = new Stack<>();
    private Stack<Spot> mediumSpots = new Stack<>();
    private Stack<Spot> largeSpots = new Stack<>();
    private Stack<Spot> xLargeSpots = new Stack<>();

    public Spot placeVehicle(Vehicle vehicle) {
        Stack<Spot> selected = null;
        switch (vehicle.size) {
            case s:
                selected = smallSpots;
                break;
            case m:
                selected = mediumSpots;
                break;
            case l:
                selected = largeSpots;
                break;
            case xl:
                selected = xLargeSpots;
                break;
            default:
        }

        if (selected.isEmpty()) {
            System.out.println("no spot is avaiable for this vechile");
            return new Spot(-1, null);
        }
        //update map
        vehicleSpotMap.put(vehicle, selected.peek());
        //return spot
        return selected.pop();
    }

    public void reclaimSpot(Vehicle vehicle) {

    }

    public void init() {

    }
}

abstract class Vehicle {
    public String licensePlate;
    public Color color;
    public Size size;

    public Vehicle(String licensePlate, Color color) {
        this.licensePlate = licensePlate;
        this.color = color;
    }
}

class MotorCycle extends Vehicle {

    public MotorCycle(String licensePlate, Color color) {
        super(licensePlate, color);
        size = Size.s;
    }
}

class Car extends Vehicle {

    public Car(String licensePlate, Color color) {
        super(licensePlate, color);
        size = Size.m;
    }
}

class Truck extends Vehicle {

    public Truck(String licensePlate, Color color) {
        super(licensePlate, color);
        size = Size.l;
    }
}

class Bus extends Vehicle {

    public Bus(String licensePlate, Color color) {
        super(licensePlate, color);
        size = Size.xl;
    }
}

class Spot {
    int id;
    Size size;

    public Size getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public Spot(int id, Size size) {
        this.id = id;
        this.size = size;
    }
}

enum Size {
    s, m, l, xl
}

enum Color {
    red, blue, white, silver, black, navy
}
