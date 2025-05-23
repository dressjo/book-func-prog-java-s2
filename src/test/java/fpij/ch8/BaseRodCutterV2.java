package fpij.ch8;

import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BaseRodCutterV2 {

    public static void main(String[] args) {
        final List<Integer> priceValues =
                Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);

        final RodCutter rodCutter = new RodCutter(priceValues);


        print(rodCutter.maxProfit(100));
        print(rodCutter.maxProfitNaive(10));
    }

    private static void print(int value) {
        System.out.println("Max profit: %s".formatted(value));
    }

    public static class RodCutter {
        private final List<Integer> prices;

        public RodCutter(final List<Integer> pricesForLengths) {
            prices = pricesForLengths;
        }

        public int maxProfit(final int length) {
            return Memoizer.callMemoized(this::computeMaxProfit, length);
        }

        private int computeMaxProfit(Function<Integer, Integer> memoizedFunction, int length) {
            int priceAtLength = length <= prices.size() ? prices.get(length - 1) : 0;
            return Math.max(priceAtLength, IntStream.range(1, length)
                    .map(i -> memoizedFunction.apply(i) + memoizedFunction.apply(length - i))
                    .max()
                    .orElse(0));
        }

        public int maxProfitNaive(int length) {
            int maxPrice = length <= prices.size() ? prices.get(length - 1) : 0;
            for (int i = 1; i < length; i++) {
                int currentPrice = maxProfitNaive(i) + maxProfitNaive(length - i);
                if(currentPrice > maxPrice) {
                    maxPrice = currentPrice;
                }
            }
            return maxPrice;
        }

    }
    static class Memoizer {

        public static <T, R> R callMemoized(final BiFunction<Function<T,R>, T, R> functionToMemoize, final T input) {
            Function<T, R> memoizedFunction = new Function<T, R>() {
                private final Map<T, R> store = new HashMap<>();
                public R apply(final T input) {
                    if(!store.containsKey(input)) {
                        store.put(input, functionToMemoize.apply(this, input));
                    }
                    return store.get(input);
                }
            };
            return memoizedFunction.apply(input);
        }
    }

}
