import java.util.Scanner;

// Custom Exception for Invalid Ride Type
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract class Ride
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    // Encapsulation (getters)
    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method
    public abstract double calculateFare();
}

// BikeRide subclass
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10; // ₹10 per km
    }
}

// CarRide subclass
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20; // ₹20 per km
    }
}

// Main Class
public class RideSharingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = sc.nextDouble();

            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be greater than 0.");
            }

            Ride ride;

            // Create object based on ride type
            switch (rideType) {
                case "bike":
                    ride = new BikeRide("Ramesh", "BIKE123", distance);
                    break;
                case "car":
                    ride = new CarRide("Suresh", "CAR456", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            // Output
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}