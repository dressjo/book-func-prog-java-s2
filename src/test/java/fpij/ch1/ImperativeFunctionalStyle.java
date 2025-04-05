package fpij.ch1;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ImperativeFunctionalStyle {


  @Test
  public void imperative() {
    final List<String> cities = List.of("Chicago", "Stockholm", "Uppsala", "Luleå");
    boolean found = false;
    for (String city : cities) {
      if (city.equals("Chicago")) {
        found = true;
        break;
      }
    }
    System.out.println("Found chicago?: " + found);
  }

  @Test
  public void discountImperative() {
    final List<Integer> prices = Arrays.asList(10, 30, 17, 20, 18, 45, 12);
    double totalOfDiscountedPrices = 0.0;
    for (int price : prices) {
      if (price > 20) {
        totalOfDiscountedPrices += price * 0.9;
      }
    }
    System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
  }

  @Test
  public void discountFunctional() {
    final List<Integer> prices = Arrays.asList(10, 30, 17, 20, 18, 45, 12);
    final Double totalOfDiscountedPrices = prices.stream().
        filter(integer -> integer > 20).
        mapToDouble(integer -> integer * 0.9).
        sum();
    System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
  }

  @Test
  public void simplified() {
    final List<String> cities = List.of("Chicago", "Stockholm", "Uppsala", "Luleå");
    System.out.println("Found chicago?: " + cities.contains("Chicago"));
  }


}
