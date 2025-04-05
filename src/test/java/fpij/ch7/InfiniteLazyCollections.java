package fpij.ch7;

import static fpij.ch7.Primes.primes;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class InfiniteLazyCollections {

  @Test
  public void testPrime() {
    printString(primes(0, 10)
        .stream());

    System.out.println("====================================");

    printString(primes(100, 5)
        .stream());

  }

  private void printString(Stream<Integer> integerStream) {
    System.out.print("Prime Numbers: ");
    System.out.println(
        integerStream
        .map(String::valueOf)
        .collect(Collectors.joining(", ")));
  }

}

class Primes {

  public static List<Integer> primes(final int fromNumber, final int count) {
    return Stream.iterate(primeAfter(fromNumber - 1), Primes::primeAfter)
        .limit(count)
        .collect(toList());
  }

  private static int primeAfter(final int number) {
    if (isPrime(number + 1)) {
      return number + 1;
    }
    return primeAfter(number + 1);
  }

  public static boolean isPrime(final int number) {
    return number > 1 &&
        IntStream.rangeClosed(2, (int) Math.sqrt(number))
            .noneMatch(divisor -> number % divisor == 0);
  }

}
