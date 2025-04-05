package org.example;

public class ShapeModeler {
  public static void main(String[] args) {

    final Shape circle = new Circle(10, 10, 20);
    switch(circle) {
      case Circle c -> System.out.println("Circle at (" + c.x() + ", " + c.y() + ") with radius " + c.radius());
      case Square s -> System.out.println("Square at (" + s.x() + ", " + s.y() + ") with side " + s.side());
    }
  }

  sealed interface  Shape permits Circle, Square {
  }

  record Circle(int x, int y, int radius) implements Shape { }
  record Square(int x, int y, int side) implements Shape { }

}
