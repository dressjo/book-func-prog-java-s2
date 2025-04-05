package fpij.ch8;

import java.util.Arrays;
import java.util.List;

public class BaseRodCutter {

  private List<Integer> values;

  public BaseRodCutter(List<Integer> values) {
    this.values = values;
  }

  public static void main(String[] args) {
    final List<Integer> priceValues =
        Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
    final BaseRodCutter rodCutter = new BaseRodCutter(priceValues);
    System.out.println(rodCutter.maxProfit(22));

  }

  private int maxProfit(int length) {
    int priceAtLength = length <= values.size() ? values.get(length - 1) : 0;
    if(length == 1) {
      return priceAtLength;
    }
    int maxProfit = 0;
    for(int i = 1; i < length; i++) {
         maxProfit = Math.max(priceAtLength, maxProfit(i) + maxProfit(length - i));
    }
    return maxProfit;
  }


}
