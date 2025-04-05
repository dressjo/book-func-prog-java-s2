package fpij.ch7;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class DelayedInitialization {

  @Test
  public void holderNaive() {
    final Holder holderNaive = new Holder();
    System.out.println(holderNaive.getHeavy());
    System.out.println(holderNaive.getHeavy());
  }

}

/*
  * The Heavy class is a heavy object that we want to create lazily. We don't want to create it until we need it.
 */
class Heavy {
  public Heavy() { System.out.println("Heavy created"); }
  public String toString() { return "Quite heavy"; }
}

class Holder {

  private Supplier<Heavy> heavy = () -> createAndCacheHeavy();

  public Holder() {
    System.out.println("Holder created");
  }

  public Heavy getHeavy() {
    return heavy.get();
  }

  private synchronized Heavy createAndCacheHeavy() {
    class HeavyFactory implements Supplier<Heavy> {
      private final Heavy heavyInstance = new Heavy();
      public Heavy get() {
        return heavyInstance;
      }
    }
    if(!HeavyFactory.class.isInstance(heavy)) {
      heavy = new HeavyFactory();
    }
    return heavy.get();
  }

}