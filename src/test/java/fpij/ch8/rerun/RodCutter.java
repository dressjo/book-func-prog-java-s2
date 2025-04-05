package fpij.ch8.rerun;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RodCutter {

    private final List<Integer> prices;

    public RodCutter(final List<Integer> pricesForLengths) {
        prices = pricesForLengths;
    }

    public static void main(String[] args) {
        var priceValues =
                List.of(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
        RodCutter rodCutter = new RodCutter(priceValues);

        Consumer<Integer> printPrice = i -> System.out.println("PriceAtLength: %s".formatted(i));

        printPrice.accept(rodCutter.basicLoopRecursion(5));
        printPrice.accept(rodCutter.basicLoopRecursion(22));

        printPrice.accept(rodCutter.basicLoopRecursionStream(5));
        printPrice.accept(rodCutter.basicLoopRecursionStream(22));
    }

    private int basicLoopRecursion(int length) {
        int priceAtLength = length <= prices.size() ? prices.get(length - 1) : 0;
         for(int i = 1; i < length; i++) {
             priceAtLength = Math.max(priceAtLength, basicLoopRecursion(i) + basicLoopRecursion(length - i));
         }
         return priceAtLength;
    }

    private int basicLoopRecursionStream(int length) {
        int priceAtLength = length <= prices.size() ? prices.get(length - 1) : 0;
        return IntStream.range(1, length)
                .map(val -> Math.max(priceAtLength, basicLoopRecursion(val) + basicLoopRecursion(length - val)))
                .max()
                .orElse(0);
    }




}
