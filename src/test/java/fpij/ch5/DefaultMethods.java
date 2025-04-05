package fpij.ch5;

import org.junit.jupiter.api.Test;

public class DefaultMethods {

  @Test
  public void cruiseWithSeaPlane() {
    SeaPlane seaPlane = new SeaPlane(1);
    seaPlane.cruise();

    seaPlane = new SeaPlane();
    seaPlane.cruise();
    seaPlane.takeOff();
    seaPlane.turn();
    seaPlane.land();
  }

}

class SeaPlane extends Vehicle implements FastFly, Sail {
  private int altitude;

  public SeaPlane(int altitude) {
    this.altitude = altitude;
  }

  public SeaPlane() {
  }

  public void cruise() {
    System.out.println("SeaPlane::cruise currently cruise like: ");
    if(altitude > 0)
      FastFly.super.cruise();
    else
      Sail.super.cruise();
  }

}

class Vehicle {
  public void turn() { System.out.println("Vehicle::turn"); }
}

interface Fly {
  default void takeOff() { System.out.println("Fly::takeOff"); }
  default void land() { System.out.println("Fly::land"); }
  default void turn() { System.out.println("Fly::turn"); }
  default void cruise() { System.out.println("Fly::cruise"); }
}

interface FastFly extends Fly {
  default void takeOff() { System.out.println("FastFly::takeOff"); }
}

interface Sail {
  default void cruise() { System.out.println("Sail::cruise"); }
  default void turn() { System.out.println("Sail::turn"); }
}


