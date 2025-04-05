package fpij.ch7;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class LazyEvaluations {

  @Test
  public void notLazy() {
    System.out.println(Evaluation.evaluate(101));
  }

  @Test
  public void eagerCombined() {
    final Instant now = Instant.now();
    Evaluation.eagerEvaluator(Evaluation.evaluate(10), Evaluation.evaluate(101));
    System.out.println("Elapsed time: " + Duration.between(now, Instant.now()).toSeconds() + "s");
  }

  @Test
  public void lazyCombined() {
    final Instant now = Instant.now();
    Evaluation.lazyEvaluator(() -> Evaluation.evaluate(10), () -> Evaluation.evaluate(102));
    System.out.println("Elapsed time: " + Duration.between(now, Instant.now()).toSeconds() + "s");
  }

  public class Evaluation {
    public static boolean evaluate(final int value) {
      System.out.println("evaluating ..." + value);
      simulateTimeConsumingOp(2000);
      return value > 100;
    }

    public static void eagerEvaluator(boolean input1, boolean input2) {
      System.out.println("eagerEvaluator called...");
      System.out.println("accept?: " + (input1 && input2));
    }

    public static void lazyEvaluator(Supplier<Boolean> input1, Supplier<Boolean> input2) {
      System.out.println("eagerEvaluator called...");
      System.out.println("accept?: " + (input1.get() && input2.get()));
    }

    public static void simulateTimeConsumingOp(int time) {
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}
