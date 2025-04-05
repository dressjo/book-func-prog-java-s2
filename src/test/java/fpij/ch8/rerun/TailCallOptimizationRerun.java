package fpij.ch8.rerun;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static fpij.ch8.rerun.Factorial.factorialTailRec;

public class TailCallOptimizationRerun {

    @Test
    void textBookFactorial() {
        System.out.println("Textbook factorial %s: ".formatted(textBookFactorial(20000)));
    }

    private static int textBookFactorial(int start) {
        if(start == 1) {
            return start;
        }
        return start * (textBookFactorial(start - 1));
    }

    @Test
    void testFactorial() {
        BigInteger factorialResult = factorialTailRec(BigFactorial.FIVE).invoke();
        System.out.println("Factorial value %s".formatted(factorialResult));
        factorialResult = factorialTailRec(BigFactorial.TWENTY_K).invoke();
        System.out.println("Factorial value %s".formatted(factorialResult));
    }

}


