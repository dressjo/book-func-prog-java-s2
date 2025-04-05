package fpij.ch8;

import fpij.ch8.TCOFactorial.BigFactorial;
import fpij.ch8.TCOFactorial.TailCall;
import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class TailCallOptimization {

  @Test
  void textBookFactorial() {
    System.out.println("Textbook factorial %s: ".formatted(myFactorial(20000)));
  }

  @Test
  void myFactorial() {
    try {
      System.out.println(myFactorial(10));
    } catch(StackOverflowError soe) {
      System.out.println(soe);
    }
  }

  @Test
  void factorial() {
    System.out.println(basicFactorial(10));
  }



  private static int myFactorial(int n) {
    int result = n;
    for (int i = n; i > 1 ; i--) {
      result *= i - 1;
    }
    return result;
  }

  private static int basicFactorial(int n) {
    if(n == 1) {
      System.out.println("n1: %s".formatted(n));
      return 1;
    }
    final int factorial = n * basicFactorial(n - 1);
    System.out.println("n: %s".formatted(factorial));
    return factorial;
  }

  @Test
  void tailCallOptimization() {
    final BigInteger factorialResult = TCOFactorial.factorial(BigFactorial.FIVE);
    System.out.println("TCO Factorial result: %s".formatted(factorialResult));
  }

}

class TCOFactorial {

  public static BigInteger factorial(BigInteger number) {
    return factorialTailRec(BigInteger.ONE, number).invoke();
  }

  public static TailCall<BigInteger> factorialTailRec(
      final BigInteger factorial, final BigInteger number) {
    if (number.equals(BigInteger.ONE)) {
      return TailCalls.call(() -> TailCalls.done(factorial));
    }
    return TailCalls.call(() -> factorialTailRec(BigFactorial.multiply(number, factorial), BigFactorial.decrement(number)));
  }

  @FunctionalInterface
  interface TailCall<T> {
    TailCall<T> apply();
    default boolean isComplete() { return false; }
    default T result() { throw new Error("not implemented"); }
    default T invoke() {
      return Stream.iterate(this, TailCall::apply)
          .filter(TailCall::isComplete)
          .findFirst()
          .get()
          .result();
    }
  }

  public class TailCalls {
    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
      return nextCall;
    }
    public static <T> TailCall<T> done(final T value) {
      return new TailCall<T>() {
        @Override public boolean isComplete() { return true; }
        @Override public T result() { return value; }
        @Override public TailCall<T> apply() {
          throw new Error("not implemented");
        }
      };
    }
  }

  public class BigFactorial {
    public static BigInteger decrement(final BigInteger number) {
      return number.subtract(java.math.BigInteger.ONE);
    }
    public static BigInteger multiply(
        final BigInteger first, final BigInteger second) {
      return first.multiply(second);
    }
    public static final BigInteger ONE = java.math.BigInteger.ONE;
    public static final BigInteger FIVE = new BigInteger("5");
    public static final BigInteger TWENTYK = new BigInteger("20000");
  }

}
