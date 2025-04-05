package designpatterns.dataoriented;

interface Vehicle {}
class Car implements Vehicle {}
class Truck implements Vehicle {}

/* Our code */

public class Sample {

  public static String processCar(Car car) {
    return "Processing car";
  }

  private static String processTruck(Truck truck) {
    return "Processing truck";
  }

  public static String process(Vehicle vehicle) {
    return switch(vehicle) {
      case Car car -> processCar(car);
      case Truck truck -> processTruck(truck);
      default -> "Unknown vehicle";
    };
  }

  public static void main(String[] args) {
    System.out.println(process(new Car()));
  }

}
