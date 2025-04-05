package fpij.ch8.rerun;

import java.math.BigInteger;

import static fpij.ch8.rerun.BigFactorial.decrement;
import static fpij.ch8.rerun.BigFactorial.multiply;

public class Factorial {

    public static TailCall<BigInteger> factorialTailRec(final BigInteger number) {
        return factorialTailRec(BigInteger.ONE, number);
    }

    private static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final BigInteger number) {
        if (number.equals(BigInteger.ONE)) {
            return TailCalls.done(factorial);
        }
        return TailCalls.call(() -> factorialTailRec(multiply(factorial, number), decrement(number)));
    }

}
