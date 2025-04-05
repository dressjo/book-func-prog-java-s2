package designpatterns.strategy;

import java.util.List;
import java.util.function.Predicate;

public class Sample {

  public static int getTotalValue(List<Integer> integers, Predicate<Integer> filter) {
    return integers.stream().filter(filter).mapToInt(Integer::intValue).sum();
  }

  public static void main(String[] args) {
    var values = List.of(1, 2, 3, 44, 7, 33);
    System.out.println(getTotalValue(values, (i) -> true));
    System.out.println(getTotalValue(values, (i) -> i % 2 == 0));
    System.out.println(getTotalValue(values, (i) -> i % 2 != 0));
    System.out.println(getTotalValue(values, Sample::isEven));
    System.out.println(getTotalValue(values, Sample::isOdd));
  }

  /* Strategy 1 */
  public static boolean isEven(Integer i) {
    return i % 2 == 0;
  }

  /* Strategy 2 */
  public static boolean isOdd(Integer i) {
    return i % 2 != 0;
  }

}
