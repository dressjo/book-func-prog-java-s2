package fpij.ch8.rerun;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Memoizer {

    public static void main(String[] args) {
        System.out.println(new Memoizer().maxProfit(5));
    }

    private List<Integer> prices =
            List.of(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);

    private int callMemoized(BiFunction<Function<Integer, Integer>, Integer, Integer> memoized, int length) {
         return computeMaxProfit(createMemoziedFunction(), length);
    }

    public int maxProfit(final int length) {
        return new Memoizer().callMemoized(this::computeMaxProfit, length);
    }

    private Function<Integer, Integer> createMemoziedFunction() {
        var savedValues = new HashMap<Integer, Integer>();
        return (val) -> {
            if(savedValues.containsKey(val)) {
                return savedValues.get(val);
            }
            return computeMaxProfit(this, val);
        };
    }

    private int computeMaxProfit(Function<Integer, Integer> memoizedFunction, int length) {
        int priceAtLength = length <= prices.size() ? prices.get(length - 1) : 0;
        return Math.max(priceAtLength, IntStream.range(1, length)
                .map(i -> memoizedFunction.apply(i) + memoizedFunction.apply(length - i))
                .max()
                .orElse(0));
    }

}
