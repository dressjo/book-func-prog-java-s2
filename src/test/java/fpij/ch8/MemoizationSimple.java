package fpij.ch8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class MemoizationSimple {

  @Test
  void calculateOptimalCuts() {
    final List<Integer> priceValues =
        Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
    final RodCutter rodCutter = new RodCutter(priceValues);
    int length = 22;
    //System.out.println("Max profit: [%s]".formatted(rodCutter.maxProfit(new HashMap<>(), length)));
    System.out.println("Max profit: [%s]".formatted(rodCutter.maxProfit(new Memoizer(), length)));
    //System.out.println("Max profit: [%s]".formatted(rodCutter.maxProfitForLoop(length)));
  }

}

class RodCutter {
  
  private final List<Integer> prices;

  public RodCutter(final List<Integer> pricesForLengths) {
    prices = pricesForLengths;
  }

  public int maxProfit(Memoizer memoizer, final int length) {
    int priceAtLength = length <= prices.size() ? prices.get(length - 1) : 0;
    return Math.max(priceAtLength,
        IntStream.range(1, length)
            .map(i -> {
              /*if(!cache.containsKey(i)) {
                cache.put(i, maxProfit(cache, i));
              }
              if(!cache.containsKey(length - i)) {
                cache.put(length - 1, maxProfit(cache, length - i));
              }
              return cache.get(i) + cache.get(length - i);*/
              return memoizer.getOrCalculate(i, key -> maxProfit(memoizer, key)) + memoizer.getOrCalculate(length - i, key -> maxProfit(memoizer, key));
            })
            .max()
            .orElse(0));
  }

  public int maxProfitForLoop(final int length) {
    int priceAtLength = length <= prices.size() ? prices.get(length - 1) : 0;
    int subPriceAtLength = 0;
    for(int i = 1; i < length; i++) {
       int price = maxProfitForLoop(i) + maxProfitForLoop(length - i);
        subPriceAtLength = Math.max(subPriceAtLength, price);
    }
    return Math.max(priceAtLength, subPriceAtLength);
  }

}

class Memoizer {
  HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

  int getOrCalculate(int key, Function<Integer, Integer> value) {
    if (!cache.containsKey(key)) {
      cache.put(key, value.apply(key));
    }
    return cache.get(key);
  }
}
