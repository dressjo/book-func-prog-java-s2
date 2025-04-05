package org.example;

import org.example.Expr.ConstExpr;
import org.example.Expr.ProdExpr;

public class Main4 {

  public interface Shape {

  }

  public static void main(String[] args) {

    record Point(int x, int y) {};
    record Circle (Point center, int radius) implements Shape {};

    Shape shape = new Circle(new Point(10, 20), 30);

    if(shape instanceof Circle(Point(int x, int y), int radius)) {
      System.out.println("Circle with center at (" + x + ", " + y + ") and radius " + radius);
    }

    System.out.println(Expr.eval(new ProdExpr(new ConstExpr(2), new ConstExpr(3))));

  }

}
