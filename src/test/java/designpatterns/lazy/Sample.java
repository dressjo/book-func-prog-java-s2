package designpatterns.lazy;

import java.util.function.Supplier;

public class Sample {

  public static int compute(int number) {
    System.out.println("slow operation");
    return number * 100;
  }

  public static void operateNormalOrder(Supplier<Integer> compute) {
    if(Math.random() > 0.5) {
      System.out.println("use the value " + compute.get());
    } else {
      System.out.println("continue without using the value");
    }
  }

  public static void main(String[] args) {
    operateNormalOrder(() -> compute(20));
  }

}
